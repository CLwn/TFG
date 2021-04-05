/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.auth.webserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;
import java.sql.*;
import sockets.MainServer;

/**
 *
 * @author clwn1
 */
public class MyLoginModule implements LoginModule {
    
    //Callback handler per guardar entre la inicialització i la autenticació
    private CallbackHandler handler;
    private Subject subject;
    private String username;
    private String password;

    

    @Override
    public void initialize(Subject sbjct, CallbackHandler cbh, Map sharedState, Map options) {
       
       handler = cbh;
       subject = sbjct;
       
    }

    private static String SHA512(String password){
        MessageDigest md;
        try{
            md = MessageDigest.getInstance("SHA-512");
            
            md.update(password.getBytes());
            byte[] mb = md.digest();
            String out = "";
            for(int i = 0; i < mb.length; i++){
                byte temp = mb[i];
                String s = Integer.toHexString(temp); 
                while(s.length() < 2){
                    s = "0" + s;
                }
                s = s.substring(s.length() - 2);
                out += s;
            }
            return out;
        }catch(NoSuchAlgorithmException e){
            System.out.println("Error: "+e.getMessage());
            
        }
        
        return "error";
    }
    
    @Override
    public boolean commit() throws LoginException {
        
        try{
            DB_access conn = new DB_access();
            conn.access();
            subject.getPrincipals().add(new UserPrincipal(username));
            subject.getPrincipals().add(new RolePrincipal(conn.searchRole(username)));
            
            return true;
        }catch(Exception e){
            throw new LoginException(e.getMessage());
        }
    }

    /**
     * Aquesta implementació sempre retorna false.
     * @return false
     * @throws LoginException 
     */
    @Override
    public boolean abort() throws LoginException {
        return false;
    }

    @Override
    public boolean logout() throws LoginException {
        
        try{
           
            DB_access conn = new DB_access();
            conn.access();
            subject.getPrincipals().remove(new UserPrincipal(username));
            subject.getPrincipals().remove(new RolePrincipal(conn.searchRole(username)));
                 
            return true;
        }catch(Exception e){
            throw new LoginException(e.getMessage());
        }
    
    }

    @Override
    public boolean login() throws LoginException {
        Callback[] callbacks = new Callback[1];
        callbacks[0] = new NameCallback("user");
        //callbacks[1] = new PasswordCallback("password", true);
        try {
            //Recuperarmos el id user por parametro
            handler.handle(callbacks);
            NameCallback nameCallback = (NameCallback)callbacks[0];
            username = nameCallback.getName();    
            System.out.println(username);
        } catch (IOException ex) {
            Logger.getLogger(MyLoginModule.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedCallbackException ex) {
            Logger.getLogger(MyLoginModule.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return true;
            
       
        //System.out.println(date);
        //Tengo que abrir aqui un puerto o esperar la respuesta por http
//        System.out.println("hola que ase estic dintre del module");
//        
//        try {
//            ServerSocket servidor = new ServerSocket(400);
//            System.out.println("esperando respuesta...");
//            Boolean ok = false;
//            while(true){
//                Socket socket = servidor.accept();
//                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//                //PrintWriter salida = new PrintWriter(new OutputStreamWriter(usuario.getOutputStream()));
//                String data = in.readLine();
//                String[] parts = data.split(";");
//                String nonce = parts[0];
//                String userQR = parts[1];
//                String dateString = parts[2];
//                String url = parts[3];
//                String userAndroid = parts[4];
//                for (int i = 0; i< parts.length; i++){
//                    System.out.println(parts[i]);
//                }
//                if(userQR.equalsIgnoreCase(userAndroid)) return true;
//                socket.close();
//            }
            
            
//        } catch (IOException ex) {
//            Logger.getLogger(MainServer.class.getName()).log(Level.SEVERE, null, ex);
//        }
//



        //try{
            
            //handler.handle(callbacks);
            //agafem lo que l'usuari ens proporciona per el formulari
            //username = ((NameCallback) callbacks[0]).getName();
            //password = String.valueOf(((PasswordCallback)callbacks[1]).getPassword());
            
            //DB_access conn = new DB_access();
            //conn.access();
            //aqui debemos hacer la comprobacion de datos que nos da el qr y lo que nos da el user
            //if(conn.searchValues(username,SHA512(password))){
              //  return true;
            //}
            

        //}catch(IOException | UnsupportedCallbackException e){
         //   throw new LoginException(e.getMessage());
        //} catch (SQLException ex) {
          //  Logger.getLogger(MyLoginModule.class.getName()).log(Level.SEVERE, null, ex);
        //}
        //return false;
    }
    
}

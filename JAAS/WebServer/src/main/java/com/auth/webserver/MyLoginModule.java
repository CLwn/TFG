/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.auth.webserver;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

/**
 *
 * @author clwn1
 */
public class MyLoginModule implements LoginModule {
    
    //Callback handler per guardar entre la inicialització i la autenticació
    private CallbackHandler handler;
    private Subject subject;
    private String login;
    private ReadUserPasswd readUserPasswd = null;

    

    @Override
    public void initialize(Subject sbjct, CallbackHandler cbh, Map sharedState, Map options) {
       
       handler = cbh;
       subject = sbjct;
       
       //recupera l'informació dels fitxers de passwd
       readUserPasswd = new ReadUserPasswd();
       
    }

    private static String SHA512(String hash, String pepper, String salt){
        MessageDigest md;
        String message = hash + pepper + salt;
        try{
            md = MessageDigest.getInstance("SHA-512");
            
            md.update(message.getBytes());
            byte[] mb = md.digest();
            String out = "";
            for(int i = 0; i < mb.length; i++){
                byte temp = mb[i];
                String s = Integer.toHexString(temp); // (new Byte(temp))
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
    public boolean login() throws LoginException {
        Callback[] callbacks = new Callback[2];
        callbacks[0] = new NameCallback("login");
        callbacks[1] = new PasswordCallback("password", true);
        
        try{
            
            handler.handle(callbacks);
            //agafem lo que l'usuari ens proporciona per el formulari
            String name = ((NameCallback) callbacks[0]).getName();
            String password = String.valueOf(((PasswordCallback)callbacks[1]).getPassword());
            
            if (!readUserPasswd.getUsersPasswd().containsKey(name)){
                throw new LoginException("Authentication failed");
            }else{
                int pepper = 0;
                boolean found = false;
                UserInfo user = readUserPasswd.getUsersPasswd().get(name);
                while (pepper < 1000 && !found){
                    
                    String sha512 = SHA512(password, String.valueOf(pepper), user.getSalt());
                    
                    if(sha512.equals(user.getHash())){
                        login = name;
                        found = true;
                    }
                    pepper++;
                }
                if (!found){
                    throw new LoginException("Authentication failed");
                }
                return true;
            }
        }catch(IOException e){
            throw new LoginException(e.getMessage());
        }catch(UnsupportedCallbackException e){
            throw new LoginException(e.getMessage());
        }
    }

    @Override
    public boolean commit() throws LoginException {
        
        try{
            
            UserInfo userActual = readUserPasswd.getUsersRoles().get(login);
            String user = userActual.getUsername();
            
            UserPrincipal userPrin = new UserPrincipal(user);
            subject.getPrincipals().add(userPrin);
            
            String rolActual = userActual.getRole();
            //fem aixo per el cas de que tingui més d'un rol
            String[] parts =rolActual.split(",");
            
            for(String rolAct: parts){
                RolePrincipal rol = new RolePrincipal(rolAct);
                subject.getPrincipals().add(rol);
            }
            
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
            
            UserInfo userActual = readUserPasswd.getUsersRoles().get(login);
            String user = userActual.getUsername();
            
            UserPrincipal userPrin = new UserPrincipal(user);
            subject.getPrincipals().remove(userPrin);
            
            String rolActual = userActual.getRole();
            //fem aixo per el cas de que tingui més d'un rol
            String[] parts =rolActual.split(",");
            
            for(String rolAct: parts){
                RolePrincipal rol = new RolePrincipal(rolAct);
                subject.getPrincipals().remove(rol);
            }
            
            return true;
        }catch(Exception e){
            throw new LoginException(e.getMessage());
        }
    
    }
    
}

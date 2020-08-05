/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.auth.webserver;

import data_treatment.ReadUserPasswd;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import javax.security.auth.Subject;
import javax.security.auth.callback.CallbackHandler;
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
    public void initialize(Subject sbjct, CallbackHandler cbh, Map aSharedState, Map aOptions) {
       
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean commit() throws LoginException {
        
        try{
            //UserInfo userActual = readUserPasswd.getUsersPasswd()
        }catch(Exception e){
            
        }
        return false;
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

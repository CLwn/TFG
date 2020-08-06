/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.auth.webserver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 *
 * @author clwn1
 */
public class ReadUserPasswd {
    
    File fitxer = null;
    FileReader lector = null;
    BufferedReader buffer = null;
   
    File fitxer2 = null;
    FileReader lector2 = null;
    BufferedReader buffer2 = null;
    
    HashMap<String, UserInfo> usersPasswd = null;
    HashMap<String, UserInfo> usersRoles = null;
    
    public ReadUserPasswd(){
        
        try{
            //obertura de fitxers i creació del buffer per poder
            //fer una lectura comode
            fitxer = new File("/home/clwn1/apache-tomcat-9.0.37/users/passwd.txt");
            lector = new FileReader(fitxer);
            buffer = new BufferedReader(lector);
            usersPasswd = new HashMap<String, UserInfo>();
            
            //lectura del fitxer
            String linia;
            while((linia = buffer.readLine()) != null){
                String[] Parts = linia.split(":");
                String login = Parts[0];
                String hash = Parts[1];
                String salt = Parts[2];
                UserInfo user = new UserInfo(login,hash,salt);
                
                usersPasswd.put(login, user);
                
            }
            
            
        }catch(IOException e){
            e.getMessage();
        }finally{
            //aqui tanquem el fitxer, per asegurar-nos que es tanca
            // tant si va tot bé com si salta un exception
            try{
                if(lector != null){
                    lector.close();
                }
            }catch(IOException e2){
                e2.getMessage();
            }
        }
        
        
        try{
            //obertura de fitxers i creació del buffer per poder
            //fer una lectura comode
            fitxer2 = new File("/home/clwn1/apache-tomcat-9.0.37/users/users_info.txt");
            lector2 = new FileReader(fitxer2);
            buffer2 = new BufferedReader(lector2);
            usersRoles = new HashMap<String, UserInfo>();
            
            //lectura del fitxer
            String linia;
            while((linia = buffer2.readLine()) != null){
                String[] Parts = linia.split(":");
                String user = Parts[0];
                String login = Parts[1];
                String rol = Parts[2];
                UserInfo user2 = new UserInfo(user, login, rol, ".");
                
                usersRoles.put(login, user2);
                
            }
            
            
        }catch(IOException e){
            e.getMessage();
        }finally{
            //aqui tanquem el fitxer, per asegurar-nos que es tanca
            // tant si va tot bé com si salta un exception
            try{
                if(lector2 != null){
                    lector2.close();
                }
            }catch(IOException e2){
                e2.getMessage();
            }
        }
    }
    
    public HashMap<String, UserInfo> getUsersPasswd(){
        return usersPasswd;
    }
    
    //posiblement hagi de fer un per als rols? idk bro
    public HashMap<String, UserInfo> getUsersRoles() {
        return usersRoles;
    }
    
}

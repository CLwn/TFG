/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_treatment;

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
    
    HashMap<String, UserInfo> usersPasswd = null;
    
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
    }
    
    public HashMap<String, UserInfo> getUsersPasswd(){
        return usersPasswd;
    }
    
    //posiblement hagi de fer un per als rols? idk bro
}

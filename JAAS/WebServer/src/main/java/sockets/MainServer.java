/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author CLwn1
 */
public class MainServer {
    public static void main(String[] args) {
        
        try {
            ServerSocket servidor = new ServerSocket(200);
            System.out.println("esperando respuesta...");
            while(true){
                Socket user = servidor.accept();
                BufferedReader in = new BufferedReader(new InputStreamReader(user.getInputStream()));
                //PrintWriter salida = new PrintWriter(new OutputStreamWriter(usuario.getOutputStream()));
                String data = in.readLine();
                String[] parts = data.split(";");
                for (int i = 0; i< parts.length; i++) System.out.println(parts[i]);
                user.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(MainServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sockets;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author CLwn1
 */
public class Connection {
    private final int PORT = 200; //port per la connexió
    private final String HOST = "localhost"; //host per la connexió
    protected String serverMessage; //missatges entrants al servidor
    protected ServerSocket serverSocket; //socket del servidor
    protected Socket clientSocket; // socket del client
    protected DataOutputStream outServer, outClient; //fluxe de dades de sortida
    
   
    public Connection(String type) throws IOException{
        if(type.equalsIgnoreCase("server")){
            serverSocket = new ServerSocket(PORT);
            clientSocket = new Socket();
        }else clientSocket = new Socket(HOST, PORT);
    }
}

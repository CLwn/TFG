/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Threads;

import com.auth.webserver.DB_access;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;

/**
 *
 * @author CLwn1
 */
public class MyThread extends Thread{
    private HttpSession session;
    private String user;

    public MyThread(HttpSession session) {
        this.session = session;
    }
    
    
    public void run(){
        user = session.getAttribute("user").toString();
        System.out.println("usuari pasat per context: "+user);
        System.out.println("hola que ase estic dintre del module");
        
    }
}
        //response.sendRedirect("error.jsp");
//        try {
//            ServerSocket servidor = new ServerSocket(400);
//            System.out.println("esperando respuesta...");
//            Boolean ok = false;
//            while(!ok){
//                Socket socket = servidor.accept();
//                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//                //PrintWriter salida = new PrintWriter(new OutputStreamWriter(usuario.getOutputStream()));
//                String data = in.readLine();
//                String[] parts = data.split(";");
//                String userQR = parts[1];
//                //String userAndroid = parts[4];
//                for (int i = 0; i< parts.length; i++){
//                    System.out.println(parts[i]);
//                }
//                if(userQR.equalsIgnoreCase(user)){
//                    try {
//                        DB_access conn = new DB_access();
//                        conn.access();
//                        conn.updateQRvalue(userQR);
//                        ok = true;
//                    } catch (SQLException ex) {
//                        Logger.getLogger(MyThread.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                }
//                socket.close();
//            }
//        } catch (IOException ex) {
//            Logger.getLogger(MyThread.class.getName()).log(Level.SEVERE, null, ex);
//        }
//            
//        
//        System.out.println("se sale");
//    }       

//    @Override
//    public Boolean call() throws Exception {
//        MyLoginModule loginModule = new MyLoginModule();
//        try {
//            if(loginModule.login()){
//                loginModule.commit();
//                return true;
//            }
//            System.out.println("Sortint del thread");
//
//        } catch (LoginException ex) {
//            Logger.getLogger(MyThread.class.getName()).log(Level.SEVERE, null, ex);
//        }

//        System.out.println("hola que ase estic dintre del module");
//        //response.sendRedirect("error.jsp");
//        try {
//            ServerSocket servidor = new ServerSocket(200);
//            System.out.println("esperando respuesta...");
//            Boolean ok = false;
//            while(true){
//                Socket socket = servidor.accept();
//                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//                //PrintWriter salida = new PrintWriter(new OutputStreamWriter(usuario.getOutputStream()));
//                String data = in.readLine();
//                String[] parts = data.split(";");
//                String userQR = parts[1];
//                String userAndroid = parts[4];
//                for (int i = 0; i< parts.length; i++){
//                    System.out.println(parts[i]);
//                }
//                if(userQR.equalsIgnoreCase(userAndroid)) return true;
//                socket.close();
//            }
//            
//            
//        } catch (IOException ex) {
//            Logger.getLogger(MainServer.class.getName()).log(Level.SEVERE, null, ex);
//        }
      //  return false;
 //  }


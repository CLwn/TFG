/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Threads.MyThread;
import com.auth.webserver.MyCallbackHandler;
import com.auth.webserver.MyLoginModule;
import com.auth.webserver.QRGenerate;
import java.awt.image.RenderedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.security.cert.X509CRL;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.bouncycastle.crypto.prng.drbg.SP80090DRBG;
import org.cryptacular.generator.Nonce;
import org.cryptacular.generator.sp80038a.RBGNonce;
import org.cryptacular.util.NonceUtil;
import sockets.MainServer;



/**
 *
 * @author CLwn1
 */

public class QRcodeServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
              
//        try {
            
        QRGenerate qr = new QRGenerate();
        //Random rand = new Random();
        //long value = rand.nextLong();
        //String result = Long.toHexString(value);
        //System.out.println(result);
        //URL domain = new URL("http://192.168.1.114:8080/QRresponseServlet/");
        //URL dynamicUrl = new URL(domain+result);
        //System.out.println(dynamicUrl);
        ServletContext context = getServletConfig().getServletContext(); //nose si se tendrá que usar
        String user = request.getParameter("user");
        File f = new File("c:\\TFG\\JAAS\\WebServer\\src\\main\\webapp"
                + "\\img\\"+user+".jpg");
        Date date = new Date();
        String url = "http://192.168.1.114:8080/WebServer/QRcodeServlet";
        Nonce nonce = new RBGNonce(16);
        byte[] nonceUtil = NonceUtil.randomNonce(16);
        //Nonce nonce1 = new org.cryptacular.generator.sp80038d.RBGNonce();
        byte[] array = nonce.generate();
        System.out.println(array);
        String text = null;
        for (int i = 0; i<array.length; i++) text = text + array[i];
        text = text +";"+user+";"+date+";"+url;
        System.out.println("\n"+user);

            //String text1 = user+";"+"public key"+";"+";"+date;
        //Generacion QR
        try {
            qr.generateQR(f, text, 300, 300);
        } catch (Exception ex) {
            Logger.getLogger(QRcodeServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //impresion de QR en pantalla
//        response.setContentType("text/html");
//        PrintWriter out = response.getWriter();
//        out.println("<html>");
//        out.println("<body>");
//        out.print("<img src='" + request.getContextPath() + "/img/"+user+".jpg' alt='image' />");
//        out.println("</body>");
//        out.println("</html>");
        
       
        //autenticacion por QR     
//        MyThread thread = new MyThread(user, date);
//        thread.start();
        //ExecutorService service = Executors.newFixedThreadPool(1);
        //Future<Boolean> result = service.submit(new MyThread(user, date));
        
//            if(result.get())response.sendRedirect("QRresponseServlet");//request.getRequestDispatcher("QRresponseServlet").forward(request, response);
//            else response.sendRedirect("error.jsp");
            
                
        


        response.setContentType("image/jpeg");
        ServletOutputStream out = response.getOutputStream();
//        ImageIO.write((RenderedImage) f, "jpg", out);
        FileInputStream fin = new FileInputStream("c:\\TFG\\JAAS\\WebServer"
                + "\\src\\main\\webapp\\img\\"+user+".jpg");
        BufferedInputStream bin = new BufferedInputStream(fin);
        BufferedOutputStream bout = new BufferedOutputStream(out);
        int ch =0;
        while((ch=bin.read())!=-1){
            bout.write(ch);
        }

        


        bin.close();
        fin.close();
        bout.close();
        out.close();



//        try {
//            LoginContext loginContext = new LoginContext("WebServer", new MyCallbackHandler(user,date));
//            loginContext.login();
//            MyLoginModule loginModule = new MyLoginModule();
//            System.out.println(loginModule.login());
//            //if(loginModule.login(user, date)) request.getRequestDispatcher("QRresponseServlet").forward(request, response);
//            //else response.sendRedirect("error.jsp");
//        } catch (LoginException ex) {
//            Logger.getLogger(QRcodeServlet.class.getName()).log(Level.SEVERE, null, ex);
//        }

//FakeLoginModule loginModule = new FakeLoginModule();

//if(loginModule.login(user, date)) request.getRequestDispatcher("QRresponseServlet").forward(request, response);
//else response.sendRedirect("error.jsp");


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
//                String userAndroid = parts[4];
//                for (int i = 0; i< parts.length; i++){
//                    System.out.println(parts[i]);
//                }
//                if(user.equalsIgnoreCase(userAndroid)) request.getRequestDispatcher("QRresponseServlet").forward(request, response);
//                socket.close();
//            }
//
//            
//        } catch (IOException ex) {
//            Logger.getLogger(MainServer.class.getName()).log(Level.SEVERE, null, ex);
//        }

//request.getRequestDispatcher("QRresponseServlet").forward(request, response);
//response.sendRedirect("error.jsp");
        
                
                
                
                

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
            

       
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

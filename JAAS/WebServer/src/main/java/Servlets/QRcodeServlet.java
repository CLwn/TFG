/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import com.auth.webserver.MyLoginModule;
import com.auth.webserver.QRGenerate;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.security.cert.X509CRL;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.security.auth.login.LoginException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.bouncycastle.crypto.prng.drbg.SP80090DRBG;
import org.cryptacular.generator.Nonce;
import org.cryptacular.generator.sp80038a.RBGNonce;
import org.cryptacular.util.NonceUtil;



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
       
        QRGenerate qr = new QRGenerate();
        Random rand = new Random();
        long value = rand.nextLong();
        String result = Long.toHexString(value);
        System.out.println(result);
        URL domain = new URL("http://192.168.1.114:8080/QRresponseServlet/");
        //URL dynamicUrl = new URL(domain+result);
        //System.out.println(dynamicUrl);
        String user = request.getParameter("user");
        File f = new File("c:\\TFG\\JAAS\\WebServer\\src\\main\\webapp"
                + "\\img\\"+user+".jpg");
        Date date = new Date();
        String url = "http://192.168.1.114:8080/WebServer/QRcodeServlet";
        Nonce nonce = new RBGNonce(16);
        byte[] nonceUtil = NonceUtil.randomNonce(16);
        Nonce nonce1 = new org.cryptacular.generator.sp80038d.RBGNonce();
        byte[] array = nonce.generate();
        System.out.println(array);
        String text = null;
        for (int i = 0; i<array.length; i++) text = text + array[i];
        text = text +";"+user+";"+date+";"+url;
        System.out.println("\n"+user);
        
        //String text1 = user+";"+"public key"+";"+";"+date;
        try {
            qr.generateQR(f, text, 300, 300);
        } catch (Exception ex) {
            Logger.getLogger(QRcodeServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        response.setContentType("image/jpeg");
        ServletOutputStream out;
        out = response.getOutputStream();
        FileInputStream fin = new FileInputStream("c:\\TFG\\JAAS\\WebServer"
                + "\\src\\main\\webapp\\img\\"+user+".jpg");
        BufferedInputStream bin = new BufferedInputStream(fin);
        BufferedOutputStream bout = new BufferedOutputStream(out);
        int ch =0;
        while((ch=bin.read())!=-1){
            bout.write(ch);
        }
        
        MyLoginModule loginModule = new MyLoginModule();
       
            //loginModule.login();
        

        bin.close();
        fin.close();
        bout.close();
        out.close();
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

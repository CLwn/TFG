/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Threads.MyThread;
import com.auth.webserver.DB_access;
import com.auth.webserver.QRGenerate;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.cryptacular.generator.Nonce;
import org.cryptacular.generator.sp80038a.RBGNonce;

/**
 *
 * @author CLwn1
 */
public class LoginServlet extends HttpServlet{
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
        DB_access conn = new DB_access();
        conn.access();
        String user = request.getParameter("user");
        request.getSession().setAttribute("user", user);
        MyThread thread = new MyThread(request.getSession());
        thread.start();
        //HttpSession session = request.getSession();
        //només em de comprovar el nom de user estigui a la BD i ja esta la comprovació de contrasenya es fa més endavant.
        try {
            if(conn.searchIdUser(user)){
                QRGenerate qr = new QRGenerate();
                File f = new File("c:\\TFG\\JAAS\\WebServer\\src\\main\\webapp"
                + "\\img\\"+user+".jpg");
                Date date = new Date();
                Nonce nonce = new RBGNonce(16);
                byte[] array = nonce.generate();
                String text = null;
                for (int i = 0; i<array.length; i++) text = text + array[i];
                text = text+";"+user+";"+date;
                qr.generateQR(f, text, 300, 300);
                //request.getRequestDispatcher("admin/admin.jsp")
                  //  .forward(request, response);
                response.sendRedirect("admin/admin.jsp");
            }else response.sendRedirect("error.jsp");
            
        } catch (SQLException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
           
        //No em de fer la comprovació del login aqui sino que simplement redirigirem cap a la pag principal on ens saltarà el login.jsp per fer la comprovació
        
        //Per tant aqui hauriem d'activar o ja tindre activar, escoltant la resposta del QR
        
        
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

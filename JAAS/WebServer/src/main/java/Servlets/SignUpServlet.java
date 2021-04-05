/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import com.auth.webserver.DB_access;
import com.auth.webserver.UserPrincipal;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author CLwn1
 */
public class SignUpServlet extends HttpServlet{
    
  public void doPost(HttpServletRequest request, HttpServletResponse response) 
  throws ServletException, IOException {
      
//    PrintWriter out = response.getWriter();
//    out.println("<HTML>");
//    out.println("<HEAD>");
//    out.println("<TITLE>Servlet Testing</TITLE>");
//    out.println("</HEAD>");
//    out.println("<BODY>");
//    out.println("Welcome to the Servlet Testing Center");
//    out.println("</BODY>");
//    out.println("</HTML>");
    /**
    String user = request.getParameter("user");
    String password = request.getParameter("password");
    
    UserPrincipal userPrincipal = new UserPrincipal();
    userPrincipal.setUserName(user);
    userPrincipal.setPassword(password);
    
    DB_access conn = new DB_access();
    conn.access();
      try {
          conn.InsertValue(userPrincipal); 
      } catch (SQLException ex) {
          Logger.getLogger(SignUpServlet.class.getName()).log(Level.SEVERE, null, ex);
      }
  */
  
 }
  
//  private byte[] getQRCodeImage(String text, int width, int height){
//      try {
//          QRCodeWriter qRCodeWriter = new QRCodeWriter();
//          BitMatrix bitMatrix = qRCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
//          ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//       
//      } catch (Exception e) {
//      }
//  }
//    
}

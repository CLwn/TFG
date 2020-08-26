/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tfg.web;
import java.io.IOException;
import java.net.URI;
import java.net.http.*;
import java.util.HashMap;
/**
 *
 * @author marc_
 */
public class CreateUser {
    public static void main(String[] args) throws IOException, InterruptedException {
    String postURL = "http://localhost:8080/web/sign_up.jsp";
    HttpClient client = HttpClient.newHttpClient();
    
    HashMap<String, String> values = new HashMap<String, String>();
    values.put("username", "Marc");
    values.put("passwd", "pepe");
    
    
    /**String requestBody = 
    
    HttpRequest reques = HttpRequest.newBuilder()
            .uri(URI.create(postURL))
            .POST(HttpRequest.BodyPublisher.ofString(values))
            .
            
    */}
}

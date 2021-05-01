/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rest;

import com.auth.webserver.DB_access;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author CLwn1
 */
@Path("/auth")
public class AuthQR {
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String doGet() { 
        System.out.println("hola");
        try {
            DB_access conn = new DB_access();
            conn.access();
            conn.updateQRvalue("admin");
        } catch (SQLException ex) {
            Logger.getLogger(AuthQR.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "hola";
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public void doPost(@PathParam("name") String username){
        System.out.println("adeu "+username);
        try {
            DB_access conn = new DB_access();
            conn.access();
            conn.updateQRvalue(username);
        } catch (SQLException ex) {
            Logger.getLogger(AuthQR.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

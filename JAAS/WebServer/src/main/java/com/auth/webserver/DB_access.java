/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.auth.webserver;

import java.sql.*;

/**
 *
 * @author clwn1
 */
public class DB_access {
    
    private Connection connect = null;
    
    public Connection access(){
        try{
            String URL = "jdbc:mysql://localhost:3306/webserver";
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(URL,
                    "root", "root");
        }catch(ClassNotFoundException | SQLException e){
            System.err.print("Error access");
        }
        return connect;
    }
    
    
    public boolean SearchValues(String name, String pass) throws SQLException{
        
        Statement statement = connect.createStatement();
        String query = "SELECT username, password FROM users";
        
        ResultSet resultSet = statement.executeQuery(query);
            
        while(resultSet.next()){
            String dbUsername = resultSet.getString("username");
            String dbPassword = resultSet.getString("password");
                
            if(dbUsername.equals(name) && dbPassword.equals(pass)) return true;
         
            }
        return false;
    }

    public String SearchRole(String name) throws SQLException {
        String query = "SELECT role FROM users_roles WHERE username="+"'"+name+"'";
        Statement statement = connect.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
       
        while(resultSet.next()){
            String role = resultSet.getString("role");
            return role;
        }
        return "";
    }
    
}

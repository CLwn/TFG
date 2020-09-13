/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.auth.webserver;

/**
 *
 * @author clwn1
 */
public class UserInfo {
    private String username, hash, salt, role;
    
     public UserInfo( String username,String hash, String salt) {
        this.hash = hash;
        this.salt = salt;
        this.username = username;
    }

    public UserInfo(String user, String rol) {
       this.username = user;
       this.role = rol;
       
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

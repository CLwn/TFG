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
    private String user, hash, salt, login, rol, city;
    
     public UserInfo( String login,String hash, String salt) {
        this.hash = hash;
        this.salt = salt;
        this.login = login;
    }

    public UserInfo(String user, String login, String rol, String city) {
       this.user = user;
       this.login = login;
       this.rol = rol;
       this.city = null;
       
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}

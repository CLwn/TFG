/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_treatment;

/**
 *
 * @author clwn1
 */
class UserInfo {
    private String user, hash, salt, login;

    public UserInfo(String user, String hash, String salt, String login) {
        this.user = user;
        this.hash = hash;
        this.salt = salt;
        this.login = login;
    }
    
     public UserInfo( String login,String hash, String salt) {
        this.hash = hash;
        this.salt = salt;
        this.login = login;
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
    
    
    
}

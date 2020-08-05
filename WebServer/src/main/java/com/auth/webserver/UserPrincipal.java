/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.auth.webserver;

import java.security.Principal;
import java.util.Objects;
import javax.security.auth.Subject;

/**
 *
 * @author clwn1
 */
public class UserPrincipal implements Principal {

    String userName;

    public UserPrincipal(String username) {
        this.userName = username;
    }


    @Override
    public String getName() {
        return userName;
    }

    @Override
    public String toString() {
        return "UserPrincipal{" + "username=" + userName + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UserPrincipal other = (UserPrincipal) obj;
        if (!Objects.equals(this.userName, other.userName)) {
            return false;
        }
        return true;
    }

    
    public int hashCode(){
        return userName.hashCode();
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.auth.webserver;

import java.security.Principal;
import java.util.Objects;

/**
 *
 * @author clwn1
 */
public class RolePrincipal implements Principal {
    
    String roleName;

    public RolePrincipal(String roleName) {
        this.roleName = roleName;
    }
    
    @Override
    public String getName() {
        return roleName;
    }

    @Override
    public int hashCode() {
        return roleName.hashCode();
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
        final RolePrincipal other = (RolePrincipal) obj;
        if (!Objects.equals(this.roleName, other.roleName)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "RolePrincipal{" + "roleName=" + roleName + '}';
    }
    
    
    
    
    
}

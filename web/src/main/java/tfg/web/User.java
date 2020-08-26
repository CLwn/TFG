/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tfg.web;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author marc_
 */

@Entity
@Table (name = "users")
@XmlRootElement

public class User implements Serializable{
    @Id @GeneratedValue (strategy=GenerationType.AUTO)
    @NotNull
    @Column(name = "user_id")
    private int idUser;
    @Size(max = 11)
    @Column (name = "username")
    private String username;
    @Column (name = "passwd")
    private String passwd;

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public int getIdUser() {
        return idUser;
    }

    public String getUsername() {
        return username;
    }

    public String getPasswd() {
        return passwd;
    }
    
}

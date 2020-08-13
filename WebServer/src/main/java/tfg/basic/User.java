/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tfg.basic;

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
    
    // faltaria tot lo del jpa hauré de llegirme demà com anava tot
    
}

package com.chaos.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by marwen on 20/12/15.
 */

@Entity
@Table(name = "USERS")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;
    @Column(name = "email", nullable = false, unique = true, length = 50)
    private String email;
    @Column(name = "password", nullable = false, length = 50)
    private String password;
    @Column(name = "name", nullable = false, length = 50)
    private String name;
    private String role;

    public User()
    {
    }

    public User(Integer id)
    {
        this.id = id;
    }

    public User(Integer id, String email, String password, String name, String role)
    {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

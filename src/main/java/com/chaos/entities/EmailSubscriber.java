package com.chaos.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by marwen on 20/12/15.
 */

@Entity
@Table(name = "EMAIL_SUBSCRIBERS")
public class EmailSubscriber implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "email", nullable = false, unique = true, length = 50)
    private String email;
    @Column(name = "name", length = 50)
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}

package com.chaos.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by marwen on 20/12/15.
 */

@Entity
@Table(name = "TAGS")
public class Tag implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private Integer id;
    @Column(name = "name", nullable = false, unique = true, length = 50)
    private String name;
    @Column(length = 500)
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}

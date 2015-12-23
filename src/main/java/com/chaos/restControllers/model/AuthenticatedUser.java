package com.chaos.restControllers.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by marwen on 21/12/15.
 */

@XmlRootElement
public class AuthenticatedUser {

    private String email;
    private List<String> roles=new ArrayList<String>();

    public AuthenticatedUser(String email, List<String> roles) {
        this.email = email;
        this.roles = roles;

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}

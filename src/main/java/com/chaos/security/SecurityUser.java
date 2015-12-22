package com.chaos.security;

import com.chaos.entities.User;

/**
 * Created by marwen on 21/12/15.
 */
public class SecurityUser {
    private String username;
    private User domainUser;

    public String getUsername() {
        return username;
    }

    public User getDomainUser() {
        return domainUser;
    }
}

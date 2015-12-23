package com.chaos.security;

import com.chaos.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * Created by marwen on 21/12/15.
 */
public class SecurityUser implements UserDetails {

    private static final long serialVersionUID = 1L;

    private User domainUser;

    public SecurityUser(User domainUser) {
        this.domainUser = domainUser;
    }

    public void setDomainUser(User domainUser) {
        this.domainUser = domainUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return domainUser.getPassword();
    }

    @Override
    public String getUsername() {
        return domainUser.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public User getDomainUser() {
        return domainUser;
    }
}

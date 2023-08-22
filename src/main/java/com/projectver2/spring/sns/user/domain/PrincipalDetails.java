package com.projectver2.spring.sns.user.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class PrincipalDetails implements UserDetails {
    private User user;

    @Override
    public boolean isEnabled() {
        return true;
    }


    public PrincipalDetails(User user){
        this.user = user;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        // TODO: Implement authority logic
        return null;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserid();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;  // assume that Account isn't expired
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;  // assume that Account isn't locked
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;  // assume that Account isn't credential not expired
    }

}

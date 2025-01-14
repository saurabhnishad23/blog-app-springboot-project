package com.sunny.blog.services.impl;

import com.sunny.blog.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserInfoDetails implements UserDetails {

    private String username;

    private String password;

    private List<GrantedAuthority> authorities;

    public UserInfoDetails(User user){
        this.username = user.getName();
        this.password = user.getPassword();
        this.authorities = List.of(user.getRoles()
                        .split(","))
                        .stream()
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());
    }

//    public static UserDetails buildUserDetails(User user) {
//
//    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
}

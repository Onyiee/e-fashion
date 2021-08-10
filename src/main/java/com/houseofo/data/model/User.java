package com.houseofo.data.model;

import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Data
@Document

public class User implements UserDetails {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String userName;
    private Role role;
    private List<Dress> dresses = new ArrayList<>();
    private Size size;
    @DBRef
    private List<Order> orders = new ArrayList<>();
    private List<Address> addresses = new ArrayList<>();
    private String designerBrand;
    private final String password;
    private final Set<? extends GrantedAuthority> grantedAuthorities;
    private final boolean isAccountNonExpired;
    private final boolean isAccountNonLocked;
    private final boolean isCredentialsNonExpired;
    private final boolean isEnabled;

    public User(String id, String firstName, String lastName, String userName, Role role,
                List<Dress> dresses, Size size, List<Order> orders, List<Address> addresses,
                String designerBrand, String password,
                Set<? extends GrantedAuthority> grantedAuthorities, boolean isAccountNonExpired,
                boolean isAccountNonLocked, boolean isCredentialsNonExpired, boolean isEnabled) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.role = role;
        this.dresses = dresses;
        this.size = size;
        this.orders = orders;
        this.addresses = addresses;
        this.designerBrand = designerBrand;
        this.password = password;
        this.grantedAuthorities = grantedAuthorities;
        this.isAccountNonExpired = isAccountNonExpired;
        this.isAccountNonLocked = isAccountNonLocked;
        this.isCredentialsNonExpired = isCredentialsNonExpired;
        this.isEnabled = isEnabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

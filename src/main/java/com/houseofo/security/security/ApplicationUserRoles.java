package com.houseofo.security.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.houseofo.security.security.ApplicationUserPermissions.*;

public enum ApplicationUserRoles {
    ADMIN(Sets.newHashSet(USER_WRITE,USER_READ,DRESS_WRITE, DRESS_READ,ORDER_WRITE,ORDER_READ)),
    DESIGNER(Sets.newHashSet(USER_READ,DRESS_READ,DRESS_WRITE,ORDER_WRITE,ORDER_READ)),
    CLIENT(Sets.newHashSet(DRESS_READ,USER_READ,ORDER_WRITE,ORDER_READ));

    private final Set<ApplicationUserPermissions> permissions;

    ApplicationUserRoles(Set<ApplicationUserPermissions> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermissions> getPermissions(){
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities(){
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
              .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
              .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}
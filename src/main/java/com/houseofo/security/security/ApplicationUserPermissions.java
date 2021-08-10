package com.houseofo.security.security;

public enum ApplicationUserPermissions {
    USER_WRITE("user:write"),
    USER_READ("user:read"),
    DRESS_READ("dress:read"),
    DRESS_WRITE("dress:write");

    private final String permission;

    ApplicationUserPermissions(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}

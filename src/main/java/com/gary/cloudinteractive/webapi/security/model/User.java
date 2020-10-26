package com.gary.cloudinteractive.webapi.security.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class User {
    private String username;
    private String password;
    private Boolean enabled;
    private List<Authority> authorities;

    public User(String username, String password, List<Authority> authorities) {
        this.username = username;
        this.password = password;
        this.enabled = true;
        this.authorities = authorities;
    }
}
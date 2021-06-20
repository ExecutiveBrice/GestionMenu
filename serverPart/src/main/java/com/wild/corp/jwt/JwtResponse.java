package com.wild.corp.jwt;

import java.util.ArrayList;
import java.util.List;

public class JwtResponse {
    private String token;
    private String username;
    private Integer id;

    private List<String> authorities;
    private String type = "Bearer";

    public JwtResponse(String accessToken) {
        this.token = accessToken;
        this.authorities = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<String> authorities) {
        this.authorities = authorities;
    }

    public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }
}
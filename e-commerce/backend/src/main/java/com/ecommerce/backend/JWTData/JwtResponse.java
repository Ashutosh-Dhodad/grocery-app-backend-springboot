package com.ecommerce.backend.JWTData;

public class JwtResponse {
    private final String jwt;
    private final String username;
    private final String role;
    private final Long userId;

    public JwtResponse(String jwt, String username, String role, Long userId) {
        this.jwt = jwt;
        this.username = username;
        this.role = role;
        this.userId = userId;
    }

    public String getJwt() {
        return jwt;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }

    public Long getUserId() {
        return userId;
    }

    


}

package com.example.SpringTest.Dto;

public class AuthRequest {
    // AuthRequest.java
        private String username;
        private String password;

    // ✅ FIXED Getters
    public String getUsername() {
        return username; // return the actual value
    }

    public String getPassword() {
        return password; // return the actual value
    }

    // ✅ Add Setters (important for @RequestBody to populate data)
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    }


package com.enigma.simpletodo.auth.model;

public class UserResponse {

    private String email;

    public UserResponse(String email) {
        this.email = email;
    }

    public UserResponse() {
    }

    public String getEmail() {
        return email;
    }
}

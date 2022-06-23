package com.enigma.simpletodo.auth.model;

public class SignInResponse {

    private String email;

    private String accessToken;

    public SignInResponse(String email, String accessToken) {
        this.email = email;
        this.accessToken = accessToken;
    }

    public SignInResponse() {
    }

    public String getEmail() {
        return email;
    }

    public String getAccessToken() {
        return accessToken;
    }
}

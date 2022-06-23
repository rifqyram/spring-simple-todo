package com.enigma.simpletodo.auth.model;

import com.enigma.simpletodo.auth.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UserRequest {

    @NotBlank(message = "Email is required")
    @Email(message = "Email is invalid")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

    public UserRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public UserRequest() {
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public User toCreateUser(PasswordEncoder passwordEncoder) {
        return new User(email, passwordEncoder.encode(password));
    }
}

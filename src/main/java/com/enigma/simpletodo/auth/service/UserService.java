package com.enigma.simpletodo.auth.service;

import com.enigma.simpletodo.auth.entity.User;
import com.enigma.simpletodo.auth.model.UserResponse;
import org.springframework.security.core.Authentication;

public interface UserService {

    UserResponse get(String id);
    User findById(String id);
    UserResponse getWithAuth(Authentication authentication);

}

package com.enigma.simpletodo.auth.service;

import com.enigma.simpletodo.auth.model.SignInResponse;
import com.enigma.simpletodo.auth.model.UserRequest;
import com.enigma.simpletodo.auth.model.UserResponse;
import org.springframework.security.core.Authentication;

public interface AuthService {

    UserResponse signUp(UserRequest request);

    SignInResponse signIn(UserRequest request);

}

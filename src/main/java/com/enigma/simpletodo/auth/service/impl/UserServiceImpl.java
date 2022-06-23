package com.enigma.simpletodo.auth.service.impl;

import com.enigma.simpletodo.auth.entity.User;
import com.enigma.simpletodo.auth.entity.UserDetailImpl;
import com.enigma.simpletodo.auth.model.UserResponse;
import com.enigma.simpletodo.auth.repository.UserRepository;
import com.enigma.simpletodo.auth.service.UserService;
import com.enigma.simpletodo.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponse get(String id) {
        return findByIdOrThrowNotFound(id).toUserResponse();
    }

    @Override
    public User findById(String id) {
        return findByIdOrThrowNotFound(id);
    }

    @Override
    public UserResponse getWithAuth(Authentication authentication) {
        UserDetailImpl userDetails = (UserDetailImpl) authentication.getPrincipal();
        return findByIdOrThrowNotFound(userDetails.getId()).toUserResponse();
    }

    private User findByIdOrThrowNotFound(String id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
    }

}

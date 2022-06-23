package com.enigma.simpletodo.auth.service.impl;

import com.enigma.simpletodo.auth.entity.User;
import com.enigma.simpletodo.auth.entity.UserDetailImpl;
import com.enigma.simpletodo.auth.model.SignInResponse;
import com.enigma.simpletodo.auth.model.UserRequest;
import com.enigma.simpletodo.auth.model.UserResponse;
import com.enigma.simpletodo.auth.repository.UserRepository;
import com.enigma.simpletodo.auth.service.AuthService;
import com.enigma.simpletodo.exception.DataExistException;
import com.enigma.simpletodo.security.jwt.JwtUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.PersistenceException;
import java.util.Date;

@Service
public class AuthServiceImpl implements AuthService {

    private final Logger log = LoggerFactory.getLogger(AuthServiceImpl.class);

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, JwtUtils jwtUtils) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public UserResponse signUp(UserRequest request) {
        try {
            User save = userRepository.save(request.toCreateUser(passwordEncoder));
            return save.toUserResponse();
        } catch (PersistenceException | DataIntegrityViolationException e) {
            throw new DataExistException(User.class.getSimpleName());
        }
    }

    @Override
    public SignInResponse signIn(UserRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailImpl userDetail = (UserDetailImpl) authentication.getPrincipal();
        String jwtToken = jwtUtils.generateJwtToken(userDetail);

        log.info("User {} signed in at {}", userDetail.getUsername(), new Date());

        return new SignInResponse(
                userDetail.getUsername(),
                jwtToken
        );
    }

}

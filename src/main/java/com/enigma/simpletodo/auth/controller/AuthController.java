package com.enigma.simpletodo.auth.controller;

import com.enigma.simpletodo.auth.model.SignInResponse;
import com.enigma.simpletodo.auth.model.UserRequest;
import com.enigma.simpletodo.auth.model.UserResponse;
import com.enigma.simpletodo.auth.service.AuthService;
import com.enigma.simpletodo.utils.ValidationUtil;
import com.enigma.simpletodo.utils.WebResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:4200"})
@Tag(name = "Auth")
public class AuthController {

    private final AuthService authService;
    private final ValidationUtil validationUtil;

    @Autowired
    public AuthController(AuthService authService, ValidationUtil validationUtil) {
        this.authService = authService;
        this.validationUtil = validationUtil;
    }

    @PostMapping(value = "/register", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> signUp(@RequestBody UserRequest request) {
        validationUtil.validate(request);
        UserResponse userResponse = authService.signUp(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new WebResponse<>(
                                HttpStatus.CREATED.value(),
                                HttpStatus.CREATED,
                                "User created successfully",
                                userResponse
                        )
                );
    }

    @PostMapping(value = "/login", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> signIn(@RequestBody UserRequest request) {
        validationUtil.validate(request);
        SignInResponse signInResponse = authService.signIn(request);
        return ResponseEntity.ok(
                new WebResponse<>(
                        HttpStatus.OK.value(),
                        HttpStatus.OK,
                        "User signed in successfully",
                        signInResponse
                )
        );
    }

}

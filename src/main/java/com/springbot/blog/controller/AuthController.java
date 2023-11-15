package com.springbot.blog.controller;

import com.springbot.blog.payload.JwtAuthResponse;
import com.springbot.blog.payload.LoginDto;
import com.springbot.blog.payload.RegisterDto;
import com.springbot.blog.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication APIs")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // Build the login API here

    @Operation(summary = "Login REST API", description = "Login REST API is used to authenticate a user and generate a JWT token.")
    @ApiResponse(responseCode = "200", description = "Authenticated successfully")
    @PostMapping(value = { "/login", "/signin" })
    public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginDto loginDto) {
        String token = authService.login(loginDto);
        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setAccessToken(token);

        return ResponseEntity.ok(jwtAuthResponse);
    }

    // Build the register API here

    @Operation(summary = "Register REST API", description = "Register REST API is used to register a new user.")
    @ApiResponse(responseCode = "201", description = "Registered successfully")
    @PostMapping(value = { "/register", "/signup" })
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
        String response = authService.register(registerDto);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}

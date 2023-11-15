package com.springbot.blog.controller;


import com.springbot.blog.payload.LoginDto;
import com.springbot.blog.payload.RegisterDto;
import com.springbot.blog.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

     // Build the login API here

    @PostMapping(value={"/login","/signin"})
    public ResponseEntity<String> login( @RequestBody LoginDto loginDto) {
        String response = authService.login(loginDto);

        return ResponseEntity.ok(response);
    }

    // Build the register API here

    @PostMapping(value={"/register","/signup"})
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
        String response = authService.register(registerDto);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}

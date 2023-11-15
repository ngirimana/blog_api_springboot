package com.springbot.blog.service.impl;

import com.springbot.blog.entity.Role;
import com.springbot.blog.entity.User;
import com.springbot.blog.exception.BlogAPIException;
import com.springbot.blog.exception.ResourceConflictException;
import com.springbot.blog.payload.LoginDto;
import com.springbot.blog.payload.RegisterDto;
import com.springbot.blog.repository.RoleRepository;
import com.springbot.blog.repository.UserRepository;
import com.springbot.blog.security.JwtTokenProvider;
import com.springbot.blog.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private JwtTokenProvider jwtTokenProvider;

    public AuthServiceImpl(AuthenticationManager authenticationManager, UserRepository userRepository,
            RoleRepository roleRepository, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;

    }

    @Override
    public String login(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return jwtTokenProvider.generateToken(authentication);
    }

    @Override
    public String register(RegisterDto registerDto) {

        // add check for existing user
        if(userRepository.existsByUsername(registerDto.getUsername())) {
            throw new ResourceConflictException("User",  "username", registerDto.getUsername());
        }

        // add check for existing email
        if(userRepository.existsByEmail(registerDto.getEmail())) {
            throw new ResourceConflictException("User",  "username", registerDto.getEmail());
        }

        User user= new User();
        user.setName(registerDto.getName());
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Set<Role> roles= new HashSet<>();
        Role userRole= roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new BlogAPIException(HttpStatus.NOT_FOUND, "User Role not set."));
        roles.add(userRole);
        user.setRoles(roles);

        userRepository.save(user);

        return "User Registered Successfully!.";
    }
}

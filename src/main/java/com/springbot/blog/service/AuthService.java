package com.springbot.blog.service;

import com.springbot.blog.payload.LoginDto;
import com.springbot.blog.payload.RegisterDto;

public interface AuthService {
    String login(LoginDto loginDto  );

    String register(RegisterDto registerDto);
}

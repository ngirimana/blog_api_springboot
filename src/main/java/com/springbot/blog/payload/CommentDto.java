package com.springbot.blog.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CommentDto {
    private Long id;

    @NotEmpty(message = "Name is required or can't be empty")
    @Size(min = 2, message = "Name should be at least 2 characters")
    private String name;

    @NotEmpty(message = "Email is required or can't be empty")
    @Email(message = "Email should be valid")
    private String email;

    @NotEmpty(message = "Body is required or can't be empty")
    @Size(min = 10, message = "Body should be at least 10 characters")
    private String body;
}

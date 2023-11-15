package com.springbot.blog.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "Comment Model Information")
public class CommentDto {
    private Long id;
    @Schema(description = "Name of the comment", example = "Schadrack")
    @NotEmpty(message = "Name is required or can't be empty")
    @Size(min = 2, message = "Name should be at least 2 characters")
    private String name;
    @Schema(description = "Email of the comment", example = "safari@gamil.com")
    @NotEmpty(message = "Email is required or can't be empty")
    @Email(message = "Email should be valid")
    private String email;
    @Schema(description = "Body of the comment", example = "Spring Boot is a framework that helps to build spring based applications easily")
    @NotEmpty(message = "Body is required or can't be empty")
    @Size(min = 10, message = "Body should be at least 10 characters")
    private String body;
}

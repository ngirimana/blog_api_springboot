package com.springbot.blog.payload;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
public class PostDto {
    private Long id;
    @NotEmpty(message = "Title cannot be empty")
    @Size(min = 2, message = "Title should have at least 2 characters")
    private String title;
    @NotEmpty(message = "Description cannot be empty")
    @Size(min = 10, message = "Description should have at least 10 characters")
    private String description;
    @NotEmpty(message = "Content cannot be empty")
    private String content;
    private Set<CommentDto> comments;
}

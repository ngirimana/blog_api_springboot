package com.springbot.blog.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
@Schema(description = "Post Model Information")
public class PostDto {

    private Long id;
    @Schema(description = "Title of the post", example = "Spring Boot")
    @NotEmpty(message = "Title cannot be empty")
    @Size(min = 2, message = "Title should have at least 2 characters")
    private String title;
    @Schema(description = "Description of the post", example = "Spring Boot is a framework")
    @NotEmpty(message = "Description cannot be empty")
    @Size(min = 10, message = "Description should have at least 10 characters")
    private String description;
    @Schema(description = "Content of the post", example = "Spring Boot is a framework that helps to build spring based applications easily")
    @NotEmpty(message = "Content cannot be empty")
    private String content;
    private Set<CommentDto> comments;

    @Schema(description = "Category of the post", example = "Spring Boot")
    private Long categoryId;
}

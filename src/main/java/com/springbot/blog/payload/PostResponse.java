package com.springbot.blog.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Post Response Model Information")
public class PostResponse {
    @Schema(description = "List of posts")
    private List<PostDto> content;
    @Schema(description = "Page number", example = "10")
    private int pageNo;
    @Schema(description = "Page size", example = "10")
    private int pageSize;
    @Schema(description = "Total number of pages", example = "10")
    private long totalElements;
    @Schema(description = "Total number of elements", example = "10")
    private int totalPages;
    @Schema(description = "Is first page", example = "true")
    private boolean last;
}

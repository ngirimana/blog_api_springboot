package com.springbot.blog.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Category Model Information")
public class CategoryDto {
    private Long id;
    @Schema(description = "Name of the category", example = "Technology")
    private String name;
    @Schema(description = "Description of the category", example = "Technology is the sum of techniques, skills, methods, and processes used in the production of goods or services or in the accomplishment of objectives, such as scientific investigation.")
    private String description;
}

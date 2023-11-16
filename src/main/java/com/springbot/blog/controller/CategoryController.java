package com.springbot.blog.controller;

import com.springbot.blog.payload.CategoryDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.springbot.blog.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@Tag(name = "CRUD REST APIs for Category Resource")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // add the addCategory API here
    @Operation(summary = "Create Category REST API", description = "Create a new category REST API is used to create a new category and save it in the database.")
    @ApiResponse(responseCode = "201", description = "Category created successfully")
    @SecurityRequirement(name = "bearer Authentication")
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryDto> addCategory(@RequestBody CategoryDto categoryDto) {
        CategoryDto savedCategory = categoryService.addCategory(categoryDto);
        return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
    }

    // add the getCategory API here
    @Operation(summary = "Get Category By Id REST API", description = "Get category by id REST API is used to retrieve a category by its id from the database.")
    @ApiResponse(responseCode = "200", description = "Retrieved successfully")
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable(name = "id") Long id) {
        CategoryDto categoryDto = categoryService.getCategory(id);
        return new ResponseEntity<>(categoryDto, HttpStatus.OK);
    }

    // add the getAllCategories API here
    @Operation(summary = "Get All Categories REST API", description = "Get all categories REST API is used to retrieve all categories from the database.")
    @ApiResponse(responseCode = "200", description = "Retrieved successfully")
    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        List<CategoryDto> categories = categoryService.getALlCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    // add the updateCategory API here
    @Operation(summary = "Update Category REST API", description = "Update category REST API is used to update a category by its id in the database.")
    @ApiResponse(responseCode = "200", description = "Updated successfully")
    @SecurityRequirement(name = "bearer Authentication")
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto,
                                                      @PathVariable(name = "id") Long id) {
        CategoryDto updatedCategory = categoryService.updateCategory(categoryDto, id);
        return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
    }

    // add the deleteCategory API here
    @Operation(summary = "Delete Category REST API", description = "Delete category REST API is used to delete a category by its id from the database.")
    @ApiResponse(responseCode = "200", description = "Deleted successfully")
    @SecurityRequirement(name = "bearer Authentication")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteCategory(@PathVariable(name = "id") Long id) {
        categoryService.deleteCategory(id);
        return new ResponseEntity<>("Category deleted successfully", HttpStatus.OK);
    }
}

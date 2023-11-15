package com.springbot.blog.controller;

import com.springbot.blog.payload.PostDto;
import com.springbot.blog.payload.PostResponse;
import com.springbot.blog.service.PostService;
import com.springbot.blog.utils.AppConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/posts")
@Tag(name = "CRUD REST APIs for Post Resource")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    /*
     * create post rest api
     */

    @Operation(summary = "Create Post REST API", description = "Create a new post REST API is used to create a new post and save it in the database.")
    @ApiResponse(responseCode = "201", description = "Post created successfully")
    @SecurityRequirement(name = "bearer Authentication")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto) {
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    /*
     * get all posts
     */
    @Operation(summary = "Get All Posts REST API", description = "Get all posts REST API is used to retrieve all posts from the database.")
    @ApiResponse(responseCode = "200", description = "Retrieved successfully")
    @GetMapping()
    public PostResponse getAllPosts(
            @RequestParam(name = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int pageNo,
            @RequestParam(name = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int pageSize,
            @RequestParam(name = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(name = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir) {
        return postService.getAllPosts(pageNo, pageSize, sortBy, sortDir);
    }

    /*
     * get post by id
     */
    @Operation(summary = "Get Post By Id REST API", description = "Get post by id REST API is used to retrieve a post by its id from the database.")
    @ApiResponse(responseCode = "200", description = "Retrieved successfully")
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(postService.getPostById(id), HttpStatus.OK);
    }

    /*
     * update post rest api
     */

    @Operation(summary = "Update Post REST API", description = "Update post REST API is used to update a post and save it in the database.")
    @ApiResponse(responseCode = "200", description = "Updated successfully")
    @SecurityRequirement(name = "bearer Authentication")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto, @PathVariable(name = "id") Long id) {
        PostDto postResponse = postService.updatePost(postDto, id);
        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }

    /*
     * delete post rest api
     */
    @Operation(summary = "Delete Post REST API", description = "Delete post REST API is used to delete a post from the database.")
    @ApiResponse(responseCode = "200", description = "Deleted successfully")
    @SecurityRequirement(name = "bearer Authentication")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@PathVariable(name = "id") Long id) {
        postService.deletePostById(id);
        return new ResponseEntity<>("Post entity deleted successfully", HttpStatus.OK);
    }

    /*
     * get all posts by category id
     *
     */
    @Operation(summary = "Get All Posts By Category Id REST API", description = "Get all posts by category id REST API is used to retrieve all posts by category id from the database.")
    @ApiResponse(responseCode = "200", description = "Retrieved successfully")
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<PostDto>> getPostsByCategoryId(
            @PathVariable(name = "categoryId") Long categoryId) {
        List<PostDto> postResponse = postService.getPostsByCategoryId(categoryId);
        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }
}

package com.springbot.blog.controller;

import com.springbot.blog.payload.CommentDto;
import com.springbot.blog.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Tag(name = "CRUD REST APIs for Comment Resource")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }
@Operation(summary = "Create Comment REST API", description = "Create a new comment REST API is used to create a new comment and save it in the database.")
    @ApiResponse(responseCode = "201", description = "Comment created successfully")
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable(name = "postId") Long postId, @Valid @RequestBody CommentDto commentDto) {
        return new ResponseEntity<>(commentService.createComment(postId, commentDto), HttpStatus.CREATED);
    }

    @Operation(summary = "Get All Comments REST API", description = "Get all comments REST API is used to retrieve all comments from the database.")
    @ApiResponse(responseCode = "200", description = "Retrieved successfully")
    @GetMapping("/posts/{postId}/comments")
    public List<CommentDto> getCommentsByPostId(@PathVariable(name = "postId") Long postId) {
        return commentService.getCommentsByPostId(postId);
    }

    @Operation(summary = "Get Comment By Id REST API", description = "Get comment by id REST API is used to retrieve a comment by its id from the database.")
    @ApiResponse(responseCode = "200", description = "Retrieved successfully")
    @GetMapping("/posts/{postId}/comments/{commentId}")
    public CommentDto getCommentById(@PathVariable(name = "postId") Long postId, @PathVariable(name = "commentId") Long commentId) {
        return commentService.getCommentById(postId, commentId);
    }

    @Operation(summary = "Update Comment REST API", description = "Update comment REST API is used to update a comment by its id in the database.")
    @ApiResponse(responseCode = "200", description = "Updated successfully")
    @PutMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable(name = "postId") Long postId, @PathVariable(name = "commentId") Long commentId, @Valid @RequestBody CommentDto commentDto) {
        return new ResponseEntity<>(commentService.updateComment(postId, commentId, commentDto), HttpStatus.OK);
    }

    @Operation(summary = "Delete Comment By Id REST API", description = "Delete comment by id REST API is used to delete a comment by its id from the database.")
    @ApiResponse(responseCode = "200", description = "Deleted successfully")
    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<?> deleteCommentById(@PathVariable(name = "postId") Long postId, @PathVariable(name = "commentId") Long commentId) {
        commentService.deleteCommentById(postId, commentId);
        return new ResponseEntity<>("Comment deleted successfully", HttpStatus.OK);
    }
}

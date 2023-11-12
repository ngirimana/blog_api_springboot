package com.springbot.blog.controller;

import com.springbot.blog.payload.PostDto;
import com.springbot.blog.payload.PostResponse;
import com.springbot.blog.service.PostService;
import com.springbot.blog.utils.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    /*
     * create post rest api
     */

    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    /*
     * get all posts
     */
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
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(postService.getPostById(id), HttpStatus.OK);
    }

    /*
     * update post rest api
     */
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable(name = "id") Long id) {
        PostDto postResponse = postService.updatePost(postDto, id);
        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }

    /*
     * delete post rest api
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@PathVariable(name = "id") Long id) {
        postService.deletePostById(id);
        return new ResponseEntity<>("Post entity deleted successfully", HttpStatus.OK);
    }
}

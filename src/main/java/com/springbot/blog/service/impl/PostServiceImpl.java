package com.springbot.blog.service.impl;

import com.springbot.blog.entity.Post;
import com.springbot.blog.payload.PostDto;
import com.springbot.blog.repository.PostRepository;
import com.springbot.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {
    private PostRepository postRepository;

//    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto) {

        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        System.out.println(postDto.getTitle());
        System.out.println(postDto.getDescription());
        System.out.println(postDto.getContent());
        Post newPost = postRepository.save(post);

        PostDto postResponse = new PostDto();
        postResponse.setId(newPost.getId());
        postResponse.setTitle(newPost.getTitle());
        postResponse.setDescription(newPost.getDescription());
        postResponse.setContent(newPost.getContent());

        return postResponse;
    }
}

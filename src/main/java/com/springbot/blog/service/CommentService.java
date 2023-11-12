package com.springbot.blog.service;

import com.springbot.blog.payload.CommentDto;

public interface CommentService {
    CommentDto createComment(Long postId,CommentDto commentDto);
}

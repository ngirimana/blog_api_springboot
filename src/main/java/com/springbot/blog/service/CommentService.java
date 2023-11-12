package com.springbot.blog.service;

import com.springbot.blog.payload.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(Long postId, CommentDto commentDto);

    List<CommentDto> getCommentsByPostId(Long postId);

    CommentDto getCommentById(Long postId,Long commentId);

    CommentDto updateComment(Long postId,Long commentId,CommentDto commentDto);

    void deleteCommentById(Long postId,Long commentId);
}

package com.springbot.blog.service.impl;

import com.springbot.blog.entity.Comment;
import com.springbot.blog.entity.Post;
import com.springbot.blog.exception.BlogAPIException;
import com.springbot.blog.exception.ResourceNotFoundException;
import com.springbot.blog.payload.CommentDto;
import com.springbot.blog.repository.CommentRepository;
import com.springbot.blog.repository.PostRepository;
import com.springbot.blog.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    private ModelMapper mapper;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository, ModelMapper mapper) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.mapper = mapper;
    }

    @Override
    public CommentDto createComment(Long postId, CommentDto commentDto) {
        Comment comment = mapToEntity(commentDto);

        // retrieve post entity by id
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", postId));

        // set post to comment entity
        comment.setPost(post);
        // comment entity to DB
        Comment newComment = commentRepository.save(comment);

        return mapToDTO(newComment);
    }

    @Override
    public List<CommentDto> getCommentsByPostId(Long postId) {
        // retrieve comments by post id
        List<Comment> comments = commentRepository.findByPostId(postId);

        // convert comment entity to commentDto
        return comments.stream().map(this::mapToDTO).collect(java.util.stream.Collectors.toList());
    }

    @Override
    public CommentDto getCommentById(Long postId, Long commentId) {
        Comment comment = GetCommentFromPost(postId, commentId);
        return mapToDTO(comment);

    }

    @Override
    public CommentDto updateComment(Long postId, Long commentId, CommentDto commentDto) {
        Comment comment = GetCommentFromPost(postId, commentId);

        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());
        Comment updatedComment = commentRepository.save(comment);

        return mapToDTO(updatedComment);
    }

    @Override
    public void deleteCommentById(Long postId, Long commentId) {
        Comment comment = GetCommentFromPost(postId, commentId);
        commentRepository.delete(comment);
    }

    /*
     * This method will retrieve the comment entity from post entity
     */

    private Comment GetCommentFromPost(Long postId, Long commentId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", postId));
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new ResourceNotFoundException("Comment", "id", commentId));

        if (!comment.getPost().getId().equals(post.getId())) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
        }
        return comment;
    }

    /*
     * This method will map the Comment entity to CommentDto
     */
    private CommentDto mapToDTO(Comment comment) {

        return mapper.map(comment, CommentDto.class);
    }

    /*
     * This method will map the CommentDto to Comment entity
     */

    private Comment mapToEntity(CommentDto commentDto) {

        return mapper.map(commentDto, Comment.class);
    }
}

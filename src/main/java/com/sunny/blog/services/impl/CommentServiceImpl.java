package com.sunny.blog.services.impl;

import com.sunny.blog.entities.Comment;
import com.sunny.blog.entities.Post;
import com.sunny.blog.exception.ResourceNotFoundException;
import com.sunny.blog.payloads.CommentDto;
import com.sunny.blog.payloads.PostDto;
import com.sunny.blog.repository.CommentRepo;
import com.sunny.blog.repository.PostRepo;
import com.sunny.blog.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private CommentRepo commentRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDto createComment(CommentDto commentDto, int postId) {
        Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));
        Comment comment = modelMapper.map(commentDto, Comment.class);
        comment.setPost(post);
        Comment savedComment = commentRepo.save(comment);
        return modelMapper.map(savedComment, CommentDto.class);
    }

    @Override
    public void deleteComment(int commentId) {
        Comment comment = commentRepo.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "commentId", commentId));
        commentRepo.delete(comment);
    }

    @Override
    public CommentDto getCommentById(int commentId) {
       Comment comment= commentRepo.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "CommentId", commentId));
       CommentDto commentDto = modelMapper.map(comment, CommentDto.class);
        return commentDto;
    }
}

package com.sunny.blog.services;

import com.sunny.blog.payloads.CommentDto;

public interface CommentService {

    CommentDto createComment(CommentDto commentDto, int postId);

    void deleteComment(int commentId);

    CommentDto getCommentById(int commentId);
}

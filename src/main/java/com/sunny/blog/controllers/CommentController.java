package com.sunny.blog.controllers;

import com.sunny.blog.payloads.ApiResponse;
import com.sunny.blog.payloads.CommentDto;
import com.sunny.blog.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/post/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto, @PathVariable int postId){
        CommentDto comment = commentService.createComment(commentDto, postId);
        return new ResponseEntity<CommentDto>(comment, HttpStatus.CREATED);
    }

    @DeleteMapping("/comments/{commentId}")
    public ApiResponse deleteComment(@PathVariable int commentId){
        commentService.deleteComment(commentId);
        return new ApiResponse("Comment Deleted Successfully", true);
    }

    @GetMapping("/comments/{commentId}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable int commentId){
        CommentDto commentDto = commentService.getCommentById(commentId);
        return new ResponseEntity<CommentDto>(commentDto, HttpStatus.OK);
    }
}

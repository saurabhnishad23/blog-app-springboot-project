package com.sunny.blog.controllers;

import com.sunny.blog.config.AppConstants;
import com.sunny.blog.entities.Post;
import com.sunny.blog.payloads.ApiResponse;
import com.sunny.blog.payloads.PostDto;
import com.sunny.blog.payloads.PostResponse;
import com.sunny.blog.services.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth/")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto, @PathVariable int userId, @PathVariable int categoryId){
       PostDto createPost = postService.createPost(postDto, userId, categoryId );
        return new ResponseEntity<PostDto>(createPost, HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable int userId){
        List<PostDto> posts =postService.getPostByUser(userId);
        return new  ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
    }
    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable int categoryId){
        List<PostDto> posts = postService.getPostByCategory(categoryId);
        return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
    }
    @GetMapping("/post/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable int postId){
       PostDto postDto = postService.getPostById(postId);
       return new ResponseEntity<PostDto>(postDto, HttpStatus.OK);
    }


    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAllPosts(
            @RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) int pageSize){
            PostResponse allPost =  postService.getAllPost(pageNumber, pageSize);
            return new ResponseEntity<PostResponse>(allPost, HttpStatus.OK);

    }

    @DeleteMapping("/posts/{postId}")
    public ApiResponse deletePost(@PathVariable int postId){
        postService.deletePost(postId);
        return new ApiResponse("Post successfully deleted !!", true);
    }
    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable int postId){
        PostDto updatePost = postService.updatePost(postDto, postId);
        return new ResponseEntity<PostDto>(updatePost, HttpStatus.OK);
    }

//    @GetMapping("/posts/search/{keywords}")
//    public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable("keywords") String keywords){
//       List<PostDto> result = postService.searchPosts(keywords);
//       return new ResponseEntity<List<PostDto>>(result, HttpStatus.OK);
//    }

}

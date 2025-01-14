package com.sunny.blog.services;

import com.sunny.blog.entities.Post;
import com.sunny.blog.payloads.PostDto;
import com.sunny.blog.payloads.PostResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto, int userId, int categoryId);

    PostDto updatePost(PostDto postDto, int postId);

    void deletePost(int postId);

    PostDto getPostById(int postId);

   PostResponse getAllPost(int pageNumber, int pageSize);


    List<PostDto> getPostByCategory(int categoryId);

    List<PostDto> getPostByUser(int userId);

//    List<PostDto> searchPosts(String keyword);

}

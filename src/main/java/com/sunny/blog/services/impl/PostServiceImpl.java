package com.sunny.blog.services.impl;

import com.sunny.blog.entities.Category;
import com.sunny.blog.entities.Post;
import com.sunny.blog.entities.User;
import com.sunny.blog.exception.ResourceNotFoundException;
import com.sunny.blog.payloads.PostResponse;
import com.sunny.blog.repository.CategoryRepo;
import com.sunny.blog.payloads.PostDto;
import com.sunny.blog.repository.PostRepo;
import com.sunny.blog.repository.UserRepo;
import com.sunny.blog.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public PostDto createPost(PostDto postDto, int userId, int categoryId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user", "userId", userId));
        Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));

        Post post  = modelMapper.map(postDto, Post.class);
        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);
        Post newPost = postRepo.save(post);
        return modelMapper.map(newPost, PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, int postId) {
        Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));
        post.setPostTitle(postDto.getPostTitle());
        post.setImageName(postDto.getImageName());
        post.setContent(postDto.getContent());
        Post updatedPost = postRepo.save(post);
        return modelMapper.map(updatedPost, PostDto.class);
    }

    @Override
    public void deletePost(int postId) {
        Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));
        postRepo.delete(post);
    }

    @Override
    public PostDto getPostById(int postId) {
        Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "PostId", postId));

        return modelMapper.map(post, PostDto.class);
    }

    @Override
    public PostResponse getAllPost(int pageNumber, int pageSize) {

        Pageable p = PageRequest.of(pageNumber,pageSize);

        Page<Post> pagePost = postRepo.findAll(p);

        List<Post> allPosts = pagePost.getContent();

        List<PostDto> postDtos = allPosts.stream().map(post -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();

        postResponse.setContent(postDtos);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalElements(pagePost.getNumberOfElements());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setLastPage(pagePost.isLast());

        return postResponse;
    }



    @Override
    public List<PostDto> getPostByCategory(int categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));

        List<Post> posts = postRepo.findByCategory(category);

        List<PostDto> postDtos = posts.stream().map((post) -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());

        return postDtos;
    }

    @Override
    public List<PostDto> getPostByUser(int userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User","userId", userId));
        List<Post> posts = postRepo.findByUser(user);

        List<PostDto> postDtos = posts.stream().map((post) -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());

        return postDtos;
    }

//    @Override
//    public List<PostDto> searchPosts(String keyword) {
//        List<Post> posts = postRepo.findByTitleContaining(keyword);
//        List<PostDto> postDtos = posts.stream().map(post -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
//        return postDtos;
//    }
}

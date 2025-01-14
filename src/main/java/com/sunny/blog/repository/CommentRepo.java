package com.sunny.blog.repository;

import com.sunny.blog.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment, Integer> {
}

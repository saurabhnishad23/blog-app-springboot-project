package com.sunny.blog.repository;

import com.sunny.blog.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}

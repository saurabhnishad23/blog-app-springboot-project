package com.sunny.blog.services;

import com.sunny.blog.payloads.CategoryDto;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CategoryService {

    CategoryDto createCategory(CategoryDto categoryDto);

    CategoryDto updateCategory(CategoryDto categoryDto, int categoryId);

    public void deleteCategory(int categoryId);

    CategoryDto getCategory(int categoryId);

    List<CategoryDto> getCategories();
}

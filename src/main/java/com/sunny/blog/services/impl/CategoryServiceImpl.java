package com.sunny.blog.services.impl;

import com.sunny.blog.entities.Category;
import com.sunny.blog.exception.ResourceNotFoundException;
import com.sunny.blog.payloads.CategoryDto;
import com.sunny.blog.repository.CategoryRepo;
import com.sunny.blog.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category newCategory = modelMapper.map(categoryDto, Category.class);
        Category savedCategory = categoryRepo.save(newCategory);

        return modelMapper.map(savedCategory, CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, int categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "Category id", categoryId));
        category.setCategoryTitle(categoryDto.getCategoryTitle());
        category.setCategoryDescription(categoryDto.getCategoryDescription());
        Category updatedCategory = categoryRepo.save(category);
        return modelMapper.map(updatedCategory, CategoryDto.class);
    }

    @Override
    public void deleteCategory(int categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));
        categoryRepo.delete(category);
    }

    @Override
    public CategoryDto getCategory(int categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "Cateory Id", categoryId));
        return modelMapper.map(category, CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getCategories() {
        List<Category> categories = categoryRepo.findAll();
        List<CategoryDto> categoryDtos = categories.stream().map(category -> modelMapper.map(category, CategoryDto.class)).collect(Collectors.toList());
        return categoryDtos;
    }
}

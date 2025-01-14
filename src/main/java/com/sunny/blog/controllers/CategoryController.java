package com.sunny.blog.controllers;

import com.sunny.blog.payloads.ApiResponse;
import com.sunny.blog.payloads.CategoryDto;
import com.sunny.blog.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryDto categoryDto;

    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
       CategoryDto createCategory = categoryService.createCategory(categoryDto);
        return new ResponseEntity<CategoryDto>(createCategory, HttpStatus.CREATED);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable int categoryId){
        CategoryDto updatedCategory = categoryService.updateCategory(categoryDto, categoryId);
        return new ResponseEntity<CategoryDto>(updatedCategory, HttpStatus.OK);
    }
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable int categoryId){
        categoryService.deleteCategory(categoryId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Category is deleted successfully", false), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getCategories(){
       List<CategoryDto> categories = categoryService.getCategories();
       return ResponseEntity.ok(categories);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable int categoryId){
       CategoryDto categoryDto = categoryService.getCategory(categoryId);
       return new ResponseEntity<CategoryDto>(categoryDto, HttpStatus.OK);
    }
}

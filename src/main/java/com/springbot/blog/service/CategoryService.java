package com.springbot.blog.service;

import com.springbot.blog.payload.CategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto addCategory(CategoryDto categoryDto);
    CategoryDto getCategory(Long id);
    List<CategoryDto> getALlCategories();

    CategoryDto updateCategory(CategoryDto categoryDto, Long id);

    void deleteCategory(Long id);
}

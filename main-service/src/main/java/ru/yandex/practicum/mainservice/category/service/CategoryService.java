package ru.yandex.practicum.mainservice.category.service;

import ru.yandex.practicum.mainservice.category.dto.CategoryDto;

import java.util.List;

public interface CategoryService {

    CategoryDto createCategory(CategoryDto categoryDto);

    CategoryDto updateCategory(CategoryDto categoryDto);

    CategoryDto findCategory(Long categoryId);

    List<CategoryDto> findCategories(int from, int size);

    void deleteCategory(Long catId);
}

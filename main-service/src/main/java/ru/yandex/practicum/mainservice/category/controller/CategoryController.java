package ru.yandex.practicum.mainservice.category.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.mainservice.category.service.CategoryService;
import ru.yandex.practicum.mainservice.category.dto.CategoryDto;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/{catId}")
    public CategoryDto findCategory(@PathVariable("catId") Long catId) {
        return categoryService.findCategory(catId);
    }

    @GetMapping
    public List<CategoryDto> findCategories(@RequestParam(value = "from", defaultValue = "0") int from,
                                            @RequestParam(value = "size", defaultValue = "10") int size) {
        return categoryService.findCategories(from, size);
    }

}

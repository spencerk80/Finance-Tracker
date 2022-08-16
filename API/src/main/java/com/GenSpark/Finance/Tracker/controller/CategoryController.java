package com.GenSpark.Finance.Tracker.controller;

import com.GenSpark.Finance.Tracker.entity.Category;
import com.GenSpark.Finance.Tracker.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories/{categoryName}")
    public Category getCategory(@PathVariable String categoryName) {
        return this.categoryService.getCategoryByName(categoryName);
    }

    @PostMapping("/categories")
    public Category saveCategory(@RequestBody Category category) {
        return this.categoryService.saveCategory(category);
    }

    @PutMapping("/categories")
    public Category updateCategory(@RequestBody Category category) {
        return this.categoryService.updateCategory(category);
    }

    @DeleteMapping("/categories/{categoryName}")
    public Category deleteCategory(@PathVariable String categoryName) {
        return this.categoryService.deleteCategoryByName(categoryName);
    }
}

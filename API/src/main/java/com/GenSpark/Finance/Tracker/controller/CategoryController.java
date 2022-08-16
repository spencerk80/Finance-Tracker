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
    public void saveCategory(@RequestBody Category category) {
        this.categoryService.saveCategory(category);
    }

    @PutMapping("/categories")
    public void updateCategory(@RequestBody Category category) {
        this.categoryService.updateCategory(category);
    }

    @DeleteMapping("/categories/{categoryName}")
    public void deleteCategory(@PathVariable String categoryName) {
        this.categoryService.deleteCategoryByName(categoryName);
    }
}

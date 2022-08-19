package com.GenSpark.Finance.Tracker.controller;

import com.GenSpark.Finance.Tracker.entity.Category;
import com.GenSpark.Finance.Tracker.enums.CategoryType;
import com.GenSpark.Finance.Tracker.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getCategories() {
        return ResponseEntity.ok().body(this.categoryService.getCategories());
    }

    @GetMapping("/categories/name/{categoryName}")
    public ResponseEntity<Category> getCategory(@PathVariable String categoryName) {
        return ResponseEntity.ok().body(this.categoryService.getCategoryByName(categoryName));
    }

    @GetMapping("/categories/type/{categoryType}")
    public ResponseEntity<List<Category>> getCategoriesByType(@PathVariable CategoryType categoryType) {
        return ResponseEntity.ok().body(this.categoryService.getAllByType(categoryType));
    }

    @PostMapping("/categories")
    public ResponseEntity<String> saveCategory(@RequestBody Category category) {
        return ResponseEntity.ok().body(this.categoryService.saveCategory(category));
    }

    @PutMapping("/categories")
    public ResponseEntity<String> updateCategory(@RequestBody Category category) {
        return ResponseEntity.ok().body(this.categoryService.updateCategory(category));
    }

    @DeleteMapping("/categories/name/{categoryName}")
    public ResponseEntity<String> deleteCategory(@PathVariable String categoryName) {
        return ResponseEntity.ok().body(this.categoryService.deleteCategoryByName(categoryName));
    }
}

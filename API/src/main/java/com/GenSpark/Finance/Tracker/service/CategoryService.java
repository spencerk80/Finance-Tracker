package com.GenSpark.Finance.Tracker.service;

import com.GenSpark.Finance.Tracker.entity.Category;

public interface CategoryService {
    void saveCategory(Category category);
    Category getCategoryByName(String name);
    void updateCategory(Category category);
    void deleteCategoryByName(String name);
}

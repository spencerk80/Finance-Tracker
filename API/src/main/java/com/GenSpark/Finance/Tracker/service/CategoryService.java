package com.GenSpark.Finance.Tracker.service;

import com.GenSpark.Finance.Tracker.entity.Category;

public interface CategoryService {
    Category saveCategory(Category category);
    Category getCategoryByName(String name);
    Category updateCategory(Category category);
    Category deleteCategoryByName(String name);
}

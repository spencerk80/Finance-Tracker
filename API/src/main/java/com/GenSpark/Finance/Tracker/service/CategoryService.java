package com.GenSpark.Finance.Tracker.service;

import com.GenSpark.Finance.Tracker.entity.Category;

import java.util.List;

public interface CategoryService {

    List<Category> getAll(int pageNo, int pageSize);
    void saveCategory(Category category);
    Category getCategoryByName(String name);
    void updateCategory(Category category);
    void deleteCategoryByName(String name);
}

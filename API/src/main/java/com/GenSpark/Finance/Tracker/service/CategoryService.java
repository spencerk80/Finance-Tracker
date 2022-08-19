package com.GenSpark.Finance.Tracker.service;

import com.GenSpark.Finance.Tracker.entity.Category;
import com.GenSpark.Finance.Tracker.enums.CategoryType;

import java.util.List;

public interface CategoryService {

    Category getCategoryByName(String name);
    List<Category> getCategories();
    List<Category> getAllByType(CategoryType type);
    String saveCategory(Category category);
    String updateCategory(Category category);
    String deleteCategoryByName(String name);

    //Unused for now
    List<Category> getAll(int pageNo, int pageSize);
}

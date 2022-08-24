package com.GenSpark.Finance.Tracker.service;

import com.GenSpark.Finance.Tracker.dao.CategoryDao;
import com.GenSpark.Finance.Tracker.entity.Category;
import com.GenSpark.Finance.Tracker.enums.CategoryType;
import com.GenSpark.Finance.Tracker.exceptions.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryDao categoryDao;

    public CategoryServiceImpl(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    public List<Category> getCategories() {
        return categoryDao.findAll();
    }

    @Override
    public Category getCategoryByName(String name) {
        return categoryDao.findByName(name).orElseThrow(() -> new ResourceNotFoundException("No Category Found With Name: " + name));
    }

    @Override
    public List<Category> getAllByType(CategoryType type) {
        return categoryDao.findAllByCategoryType(type.toString());
    }

    @Override
    public String saveCategory(Category category) {
        categoryDao.save(category);
        return "Successfully added the category";
    }

    @Override
    public String updateCategory(Category category) {
        categoryDao.save(category);
        return "Successfully updated the category";
    }

    @Override
    @Transactional
    public String deleteCategoryByName(String name) {
        categoryDao.deleteByName(name);
        return "Successfully deleted the category";
    }

    @Override
    public List<Category> getAll(int pageNo, int pageSize) {
        Page<Category> pagedResult = categoryDao.findAll(PageRequest.of(pageNo, pageSize));
        if (pagedResult.hasContent()) return pagedResult.toList();
        else return new ArrayList<>();
    }
}

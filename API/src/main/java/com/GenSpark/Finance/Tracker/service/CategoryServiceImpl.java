package com.GenSpark.Finance.Tracker.service;

import com.GenSpark.Finance.Tracker.dao.CategoryDao;
import com.GenSpark.Finance.Tracker.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryDao categoryDao;

    public CategoryServiceImpl(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    public List<Category> getAll(Integer pageNo, Integer pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<Category> pagedResult = categoryDao.findAll(paging);
        if (pagedResult.hasContent()) return pagedResult.getContent();
        else return new ArrayList<>();
    }

    @Override
    public void saveCategory(Category category) {
        categoryDao.save(category);
    }

    @Override
    public Category getCategoryByName(String name) {
        Optional<Category> category = categoryDao.findByName(name);
        if (category.isPresent()) return category.get();
        else throw new RuntimeException("Category with name: " + name + " not found.");
    }

    @Override
    public void updateCategory(Category category) {
        categoryDao.save(category);
    }

    @Override
    public void deleteCategoryByName(String name) {
        categoryDao.deleteByName(name);
    }
}

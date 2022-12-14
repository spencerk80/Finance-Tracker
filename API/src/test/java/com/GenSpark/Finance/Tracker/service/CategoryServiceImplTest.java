package com.GenSpark.Finance.Tracker.service;

import com.GenSpark.Finance.Tracker.dao.CategoryDao;
import com.GenSpark.Finance.Tracker.entity.Category;
import com.GenSpark.Finance.Tracker.enums.CategoryType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {

    @Mock
    private CategoryDao categoryDao;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    private Category category;

    @Test
    void getAll() {
        Category categoryTest = new Category();
        Page<Category> categoryPage = new PageImpl<>(Collections.singletonList(categoryTest));
        when(categoryDao.findAll(PageRequest.of(0,1))).thenReturn(categoryPage);
        Page<Category> categoryPage1 = categoryDao.findAll(PageRequest.of(0,1));
        assertEquals(categoryPage1.getNumberOfElements(), 1);
    }

    @Test
    void saveCategory() {
        categoryService.saveCategory(category);
        verify(categoryDao, times(1)).save(category);
    }

    @Test
    void getCategoryByName() {
        String name = "Rent";
        final Category category = new Category("Rent", "Housing", CategoryType.WITHDRAWAL);
        given(categoryDao.findByName(name)).willReturn(Optional.of(category));
        Category expected = categoryService.getCategoryByName(name);
        System.out.println(expected);
        assertThat(expected).isNotNull();
        assertEquals(expected.getName(), category.getName());
        assertEquals(expected.getDescription(), category.getDescription());
    }

    @Test
    void updateCategory() {
        Category category = new Category();
        given(categoryDao.save(category)).willReturn(category);
        categoryService.updateCategory(category);
        assertThat(category).isNotNull();
        verify(categoryDao).save(any(Category.class));
    }

    @Test
    void deleteCategoryByName() {
        String name = "Rent";
        categoryService.deleteCategoryByName(name);
        verify(categoryDao, times(1)).deleteByName(name);
    }

    @Test
    void getAllByType() {
        Category category = new Category("Housing", "Rent, Utilities, Etc.", CategoryType.WITHDRAWAL);
        Category category1 = new Category("Transportation", "Car Insurance, Gas, Etc.", CategoryType.WITHDRAWAL);
        Category category2 = new Category("Food", "Groceries, Eating Out, Etc.", CategoryType.WITHDRAWAL);
        given(categoryDao.findAllByCategoryType(CategoryType.WITHDRAWAL.toString())).willReturn(List.of(category, category1, category2));
        List<Category> categoryList = categoryService.getAllByType(CategoryType.WITHDRAWAL);
        System.out.println(categoryList);
        assertThat(categoryList).isNotNull();
        assertEquals(categoryList.size(), 3);
    }
}
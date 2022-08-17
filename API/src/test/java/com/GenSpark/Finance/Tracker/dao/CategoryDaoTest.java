package com.GenSpark.Finance.Tracker.dao;

import com.GenSpark.Finance.Tracker.entity.Category;
import com.GenSpark.Finance.Tracker.enums.CategoryType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CategoryDaoTest {
    @Autowired
    private CategoryDao categoryDao;
    private static Category    testCat;

    @BeforeAll
    static void createCategory() {
        Category category = new Category();

        category.setType(CategoryType.WITHDRAWAL);
        category.setName("Pizza");
        category.setDescription("Pizza is the best");

        testCat = category;
    }

    @Test
    public void saveCategoryTest() {
        categoryDao.save(testCat);

        //ID is set when saved to the db
        assertTrue(testCat.getCategoryID() > 0);
    }

    //This test requires pre-existing data in the db
    @Test
    public void getCategoryTest() {
        final String catName = "rent";
        Optional<Category> retrievedCatOpt = categoryDao.findByName(catName);

        assertTrue(retrievedCatOpt.isPresent());
        assertEquals(catName, retrievedCatOpt.get().getName());
    }

    @Test
    public void getAllCategoriesByType() {
        List<Category> categoryList = categoryDao.findAllByCategoryType(CategoryType.WITHDRAWAL.toString());

        categoryList.forEach(cat -> {
            if(testCat.getType() != CategoryType.WITHDRAWAL) fail();
        });
    }

    @Test
    public void updateCategoryTest() {
        Optional<Category> retrievedCat;

        categoryDao.save(testCat);

        testCat.setName("ice cream");
        categoryDao.save(testCat);

        retrievedCat = categoryDao.findByName(testCat.getName());

        assertTrue(retrievedCat.isPresent());
        assertEquals("ice cream", retrievedCat.get().getName());
    }

    @Test
    public void deleteCategoryTest() {
        Optional<Category> retrievedCat;

        categoryDao.save(testCat);
        categoryDao.deleteByName(testCat.getName());
        retrievedCat = categoryDao.findByName(testCat.getName());

        assertTrue(retrievedCat.isEmpty());
    }
}

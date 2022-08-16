package com.GenSpark.Finance.Tracker.dao;

import com.GenSpark.Finance.Tracker.entity.Category;
import com.GenSpark.Finance.Tracker.enums.CategoryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryDao extends JpaRepository<Category, Integer> {
    Optional<Category> findByName(String name);
    Optional<Category> deleteByName(String name);
    @Query(value = "SELECT * FROM category WHERE type = ?1"y, nativeQuery = true)
    List<Category> findByCategoryType(CategoryType type);
}

package com.GenSpark.Finance.Tracker.entity;

import com.GenSpark.Finance.Tracker.enums.CategoryType;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int categoryID;
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private CategoryType type;

    public Category() {}

    public Category(String name, String description, CategoryType type) {
        this.name = name;
        this.description = description;
        this.type = type;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CategoryType getType() {
        return type;
    }

    public void setType(CategoryType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        Category category;

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        category = (Category) o;

        return categoryID == category.categoryID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryID);
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryID=" + categoryID +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", type=" + type +
                '}';
    }
}

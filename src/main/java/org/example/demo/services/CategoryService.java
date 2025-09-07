package org.example.demo.services;

import org.example.demo.dao.CategoryDAO;
import org.example.demo.models.Category;
import java.util.List;

public class CategoryService {

    private final CategoryDAO categoryDAO;

    public CategoryService() {
        this.categoryDAO = new CategoryDAO();
    }

    // Create a new category
    public Category createCategory(Category category) {
        return categoryDAO.createCategory(category);
    }

    // Retrieve all categories
    public List<Category> getAllCategories() {
        return categoryDAO.findAllCategories();
    }

    // Retrieve a category by ID
    public Category getCategoryById(String categoryId) {
        return categoryDAO.findCategoryById(categoryId);
    }

    // Update a category
    public void updateCategory(Category category) {
        categoryDAO.updateCategory(category);
    }

    // Delete a category
    public void deleteCategory(String categoryId) {
        categoryDAO.deleteCategory(categoryId);
    }
}

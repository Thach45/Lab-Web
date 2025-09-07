package org.example.demo.dao;

import org.example.demo.config.DBUtil;
import org.example.demo.models.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {
    private static final Logger logger = LoggerFactory.getLogger(CategoryDAO.class);

    public List<Category> findAllCategories() {
        List<Category> categories = new ArrayList<>();
        String sql = "SELECT category_id, name, description FROM category";
        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Category category = new Category();
                category.setCategoryId(rs.getString("category_id"));
                category.setName(rs.getString("name"));
                category.setDescription(rs.getString("description"));
                categories.add(category);
            }
        } catch (SQLException e) {
            logger.error("Error fetching all categories", e);
        }
        return categories;
    }

    public Category findCategoryById(String categoryId) {
        String sql = "SELECT category_id, name, description FROM category WHERE category_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, categoryId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Category category = new Category();
                    category.setCategoryId(rs.getString("category_id"));
                    category.setName(rs.getString("name"));
                    category.setDescription(rs.getString("description"));
                    return category;
                }
            }
        } catch (SQLException e) {
            logger.error("Error fetching category by ID", e);
        }
        return null;
    }

    public void deleteCategory(String categoryId) {
        String sql = "DELETE FROM category WHERE category_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, categoryId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error deleting category", e);
        }
    }

    public Category createCategory(Category category) {
        String sql = "INSERT INTO category (category_id, name, description) VALUES (?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, category.getCategoryId());
            pstmt.setString(2, category.getName());
            pstmt.setString(3, category.getDescription());
            pstmt.executeUpdate();
            return category;
        } catch (SQLException e) {
            logger.error("Error creating category", e);
        }
        return null;
    }

    public void updateCategory(Category category) {
        String sql = "UPDATE category SET name = ?, description = ? WHERE category_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, category.getName());
            pstmt.setString(2, category.getDescription());
            pstmt.setString(3, category.getCategoryId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error updating category", e);
        }
    }
}

package org.example.demo.dao;

import org.example.demo.config.DBUtil;
import org.example.demo.models.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    public List<Product> findAllProduct() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT p.id, p.name, p.price, p.size, p.description, p.image_url, p.best_seller, p.stock, c.name as category_name " +
                    "FROM products p " +
                    "LEFT JOIN category c ON p.category_id = c.category_id";
        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Product product = new Product(
                    rs.getString("id"),
                    rs.getString("name"),
                    rs.getDouble("price"),
                    rs.getString("size"),
                    rs.getString("description"),
                    rs.getString("image_url"),
                    rs.getBoolean("best_seller"),
                    rs.getInt("stock"),
                    rs.getString("category_name")
                );
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
    public Product findProductById(String productId) {
        String sql = "SELECT p.id, p.name, p.price, p.size, p.description, p.image_url, p.best_seller, p.stock, c.name as category_name " +
                    "FROM products p " +
                    "LEFT JOIN category c ON p.category_id = c.category_id " +
                    "WHERE p.id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, productId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Product(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getString("size"),
                        rs.getString("description"),
                        rs.getString("image_url"),
                        rs.getBoolean("best_seller"),
                        rs.getInt("stock"),
                        rs.getString("category_name")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void deleteProduct(String productId) {
        String sql = "DELETE FROM products WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, productId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Product createProduct(Product product) {
        String sql = "INSERT INTO products (id, name, price, size, description, image_url, best_seller, stock, category_id) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, (SELECT category_id FROM category WHERE name = ?))";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, product.getId());
            pstmt.setString(2, product.getName());
            pstmt.setDouble(3, product.getPrice());
            pstmt.setString(4, product.getSize());
            pstmt.setString(5, product.getDescription());
            pstmt.setString(6, product.getImage_url());
            pstmt.setBoolean(7, product.getBest_seller());
            pstmt.setInt(8, product.getStock());
            pstmt.setString(9, product.getCategory());
            pstmt.executeUpdate();
            return product;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void updateProduct(Product product) {
        String sql = "UPDATE products SET name = ?, price = ?, size = ?, description = ?, image_url = ?, best_seller = ?, stock = ?, " +
                    "category_id = (SELECT category_id FROM category WHERE category_id = ?) WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, product.getName());
            pstmt.setDouble(2, product.getPrice());
            pstmt.setString(3, product.getSize());
            pstmt.setString(4, product.getDescription());
            pstmt.setString(5, product.getImage_url());
            pstmt.setBoolean(6, product.getBest_seller());
            pstmt.setInt(7, product.getStock());
            pstmt.setString(8, product.getCategory());
            pstmt.setString(9, product.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

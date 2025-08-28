package org.example.demo.dao;

import org.example.demo.config.DBUtil;
import org.example.demo.models.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    public List<Product> findAllProduct() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT id, name, price, size, description, image_url,best_seller, stock FROM products";
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
                        rs.getInt("stock")

                );
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
}

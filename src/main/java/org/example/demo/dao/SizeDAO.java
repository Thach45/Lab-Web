package org.example.demo.dao;

import org.example.demo.config.DBUtil;
import org.example.demo.models.Size;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SizeDAO {
    private Connection connection;

    public SizeDAO() {
        try {
            connection = DBUtil.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Size> getAllSizes() {
        List<Size> sizes = new ArrayList<>();
        String query = "SELECT * FROM sizes";
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                Size size = new Size();
                size.setSizeId(resultSet.getString("size_id"));
                size.setProductId(resultSet.getString("product_id"));
                size.setStock(resultSet.getInt("stock"));
                size.setName(resultSet.getString("name"));
                sizes.add(size);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return sizes;
    }

    public List<Size> getAvailableSizes() {
        List<Size> sizes = new ArrayList<>();
        String query = "SELECT * FROM sizes WHERE product_id IS NULL";
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                Size size = new Size();
                size.setSizeId(resultSet.getString("size_id"));
                size.setProductId(null);
                size.setStock(resultSet.getInt("stock"));
                size.setName(resultSet.getString("name"));
                sizes.add(size);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return sizes;
    }

    public Size getSizeById(String sizeId) {
        String query = "SELECT * FROM sizes WHERE size_id = ?";
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, sizeId);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                Size size = new Size();
                size.setSizeId(resultSet.getString("size_id"));
                size.setProductId(resultSet.getString("product_id"));
                size.setStock(resultSet.getInt("stock"));
                size.setName(resultSet.getString("name"));
                return size;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null;
    }

    public boolean createSize(Size size) {
        String query = "INSERT INTO sizes (size_id, product_id, stock, name) VALUES (?, NULL, ?, ?)";
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            String uuid = UUID.randomUUID().toString();
            preparedStatement.setString(1, uuid);
            preparedStatement.setInt(2, size.getStock());
            preparedStatement.setString(3, size.getName());
            
            int result = preparedStatement.executeUpdate();
            if (result > 0) {
                size.setSizeId(uuid);
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateSize(Size size) {
        String query = "UPDATE sizes SET stock = ?, name = ? WHERE size_id = ?";
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, size.getStock());
            preparedStatement.setString(2, size.getName());
            preparedStatement.setString(3, size.getSizeId());
            
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean assignToProduct(String sizeId, String productId) {
        String query = "UPDATE sizes SET product_id = ? WHERE size_id = ?";
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, productId);
            preparedStatement.setString(2, sizeId);
            
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean unassignFromProduct(String sizeId) {
        String query = "UPDATE sizes SET product_id = NULL WHERE size_id = ?";
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, sizeId);
            
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteSize(String sizeId) {
        String query = "DELETE FROM sizes WHERE size_id = ?";
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, sizeId);
            
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
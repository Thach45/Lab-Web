package org.example.demo.dao;

import org.example.demo.config.DBUtil;
import org.example.demo.models.Topping;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ToppingDAO {

    public List<Topping> findAll() {
        List<Topping> toppings = new ArrayList<>();
        String sql = "SELECT topping_id, name, price FROM toppings";
        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Topping t = new Topping();
                t.setTopping_id(rs.getString("topping_id"));
                t.setName(rs.getString("name"));
                t.setPrice(rs.getDouble("price"));
                toppings.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return toppings;
    }

    public Topping findById(String id) {
        String sql = "SELECT topping_id, name, price FROM toppings WHERE topping_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Topping(
                        rs.getString("topping_id"),
                        rs.getString("name"),
                        rs.getDouble("price")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Topping create(Topping topping) {
        String sql = "INSERT INTO toppings (topping_id, name, price) VALUES (?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, topping.getTopping_id());
            pstmt.setString(2, topping.getName());
            pstmt.setDouble(3, topping.getPrice());
            pstmt.executeUpdate();
            return topping;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(Topping topping) {
        String sql = "UPDATE toppings SET name = ?, price = ? WHERE topping_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, topping.getName());
            pstmt.setDouble(2, topping.getPrice());
            pstmt.setString(3, topping.getTopping_id());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(String id) {
        String sql = "DELETE FROM toppings WHERE topping_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}



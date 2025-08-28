package org.example.demo.services;

import org.example.demo.dao.UserDAO;
import org.example.demo.models.UserModel;

import java.sql.SQLException;

public class AuthenticationService {
    private final UserDAO userDAO;

    public AuthenticationService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
    public UserModel login(String username, String password) throws SQLException {
        UserModel user = userDAO.getUserByUsername(username);
        if (user != null && password.equals(user.getPassword())) {
            System.out.println(user.getUsername());
            return user;
        }
        return null;
    }
    public void register(String username, String password, String email) throws SQLException {
        // Kiểm tra nếu user đã tồn tại
        UserModel existingUser = userDAO.getUserByUsername(username);
        if (existingUser != null) {
            throw new SQLException("User already exists");
        }
        // Tạo user mới
        UserModel newUser = new UserModel(null, username, password, email, "user");
        boolean isCreated = userDAO.createUser(newUser);
        if (!isCreated) {
            throw new SQLException("Failed to create user");
        }

    }
}

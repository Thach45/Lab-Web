package org.example.demo.services;

import org.example.demo.dao.UserDAO;
import org.example.demo.models.UserModel;

import java.sql.SQLException;
import java.util.List;

public class AuthenticationService {
    private final UserDAO userDAO;

    public AuthenticationService() {
        this.userDAO = new UserDAO();
    }

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
        UserModel newUser = new UserModel(null, username, password, email, "USER");
        boolean isCreated = userDAO.createUser(newUser);
        if (!isCreated) {
            throw new SQLException("Failed to create user");
        }
    }

    // Quản lý user cho admin
    public List<UserModel> getAllUsers() throws SQLException {
        return userDAO.getAllUsers();
    }

    public UserModel getUserById(int userId) throws SQLException {
        return userDAO.getUserById(userId);
    }

    public UserModel createUser(UserModel user) throws SQLException {
        // Kiểm tra nếu user đã tồn tại
        UserModel existingUser = userDAO.getUserByUsername(user.getUsername());
        if (existingUser != null) {
            return null; // User đã tồn tại
        }
        
        // Kiểm tra email đã tồn tại
        UserModel existingEmail = userDAO.getUserByEmail(user.getEmail());
        if (existingEmail != null) {
            return null; // Email đã tồn tại
        }
        
        boolean isCreated = userDAO.createUser(user);
        if (isCreated) {
            return userDAO.getUserByUsername(user.getUsername());
        }
        return null;
    }

    public boolean updateUser(UserModel user) throws SQLException {
        return userDAO.updateUser(user);
    }

    public boolean deleteUser(int userId) throws SQLException {
        return userDAO.deleteUser(userId);
    }

    public boolean activateUser(int userId) throws SQLException {
        return userDAO.activateUser(userId);
    }

    public boolean deactivateUser(int userId) throws SQLException {
        return userDAO.deactivateUser(userId);
    }
}

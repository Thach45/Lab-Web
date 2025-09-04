package org.example.demo.service;

import jakarta.servlet.http.HttpSession;
import org.example.demo.entitys.UserModel;
import org.example.demo.repository.UserRepository;

import java.util.UUID;

public class AuthenticationService {
    private final UserRepository userRepository;

    public AuthenticationService() {
        this.userRepository = new UserRepository();
    }

    public boolean login(String username, String password, HttpSession session) {
        UserModel user = userRepository.findByUsername(username);

        if (user != null && user.getPassword().equals(password)) {
            // Lưu thông tin user vào session
            session.setAttribute("user", user);
            session.setAttribute("username", user.getUsername());
            session.setMaxInactiveInterval(30 * 60); // 30 phút
            return true;
        }
        return false;
    }

    public String getUserRole(String username) {
        UserModel user = userRepository.findByUsername(username);
        return user != null ? user.getRole() : null;
    }

    public void logout(HttpSession session) {
        if (session != null) {
            session.invalidate();
        }
    }

    public RegisterResult register(String username, String password, String email) {
        // Kiểm tra username đã tồn tại chưa
        UserModel existingUser = userRepository.findByUsername(username);
        if (existingUser != null) {
            return new RegisterResult(false, "Username already exists");
        }

        // Tạo user mới
        UserModel newUser = new UserModel(
            UUID.randomUUID().toString(),
            username,
            password,
            email,
            "user" // role mặc định là user
        );

        try {
            userRepository.save(newUser);
            return new RegisterResult(true, "Registration successful");
        } catch (Exception e) {
            return new RegisterResult(false, "Registration failed: " + e.getMessage());
        }
    }

    public void close() {
        if (userRepository != null) {
            userRepository.close();
        }
    }

    // Inner class để đóng gói kết quả đăng ký
    public static class RegisterResult {
        private final boolean success;
        private final String message;

        public RegisterResult(boolean success, String message) {
            this.success = success;
            this.message = message;
        }

        public boolean isSuccess() {
            return success;
        }

        public String getMessage() {
            return message;
        }
    }
}

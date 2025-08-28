package org.example.demo.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
@WebServlet("/logout")
public class Logout extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Xóa cookie bằng cách đặt thời gian sống của nó về 0
        jakarta.servlet.http.Cookie cookie = new jakarta.servlet.http.Cookie("username", null);
        cookie.setMaxAge(0); // Đặt thời gian sống của cookie về 0 để xóa nó
        resp.addCookie(cookie);
        // Chuyển hướng người dùng về trang đăng nhập hoặc trang chủ
        resp.sendRedirect(req.getContextPath() + "/login");
    }
}

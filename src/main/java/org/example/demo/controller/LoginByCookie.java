package org.example.demo.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
@WebServlet("/loginCookie")
public class LoginByCookie extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        request.getRequestDispatcher("/view/loginCookie.jsp").forward(request, response);
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("username");
        String password = req.getParameter("password");
        System.out.println("userName: " + userName);
        System.out.println("password: " + password);
        if ("nguyenhoangthach".equals(userName) && "nguyenhoangthach".equals(password)) {
            Cookie cookie = new Cookie("username", userName); //khởi tạo cookie
            cookie.setMaxAge(30);
            resp.addCookie(cookie);


            resp.sendRedirect(req.getContextPath() + "/home");
        } else {
            req.setAttribute("error", "Invalid username or password");
            req.getRequestDispatcher("/view/loginCookie.jsp").forward(req, resp);
        }
    }
}

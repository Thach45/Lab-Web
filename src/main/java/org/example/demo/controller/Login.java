package org.example.demo.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.example.demo.dao.UserDAO;
import org.example.demo.models.UserModel;
import org.example.demo.services.AuthenticationService;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginByCookie extends HttpServlet {
    private AuthenticationService userService;

    @Override
    public void init() throws ServletException {
        userService = new AuthenticationService(new UserDAO());
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        request.getRequestDispatcher("/view/loginCookie.jsp").forward(request, response);
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("username");
        String password = req.getParameter("password");
        UserModel user = null;
        try {
            user = userService.login(userName, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (user != null) {
            HttpSession session = req.getSession();
            session.setAttribute("username", userName); //lưu trữ thông tin người dùng

            session.setMaxInactiveInterval(30 * 60); //thời gian hết hạn của session là 30 phút
            resp.sendRedirect(req.getContextPath() + "/home");
        }

        else {
            req.setAttribute("error", "Invalid username or password");
            req.getRequestDispatcher("/view/loginCookie.jsp").forward(req, resp);
        }
    }
}

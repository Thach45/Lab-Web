package org.example.demo.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.demo.dao.UserDAO;
import org.example.demo.models.UserModel;
import org.example.demo.services.AuthenticationService;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/register")
public class RegisterController extends HttpServlet {
    private AuthenticationService authService;

    @Override
    public void init() throws ServletException {
        authService = new AuthenticationService(new UserDAO());
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        request.getRequestDispatcher("/view/register.jsp").forward(request, response);
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");

        try {
            authService.register(userName, password, email);
            resp.sendRedirect(req.getContextPath() + "/loginCookie");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

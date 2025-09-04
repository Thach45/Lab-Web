package org.example.demo.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.demo.service.AuthenticationService;
import org.example.demo.service.AuthenticationService.RegisterResult;

import java.io.IOException;

@WebServlet("/register")
public class RegisterController extends HttpServlet {
    private AuthenticationService authService;

    @Override
    public void init() throws ServletException {
        authService = new AuthenticationService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/view/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        RegisterResult result = authService.register(username, password, email);

        if (result.isSuccess()) {
            response.sendRedirect(request.getContextPath() + "/loginSession");
        } else {
            request.setAttribute("error", result.getMessage());
            request.getRequestDispatcher("/view/register.jsp").forward(request, response);
        }
    }

    @Override
    public void destroy() {
        if (authService != null) {
            authService.close();
        }
        super.destroy();
    }
}

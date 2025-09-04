package org.example.demo.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.demo.service.AuthenticationService;

import java.io.IOException;

@WebServlet("/logout")
public class Logout extends HttpServlet {
    private AuthenticationService authService;

    @Override
    public void init() throws ServletException {
        authService = new AuthenticationService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        authService.logout(request.getSession());
        response.sendRedirect(request.getContextPath() + "/loginSession");
    }

    @Override
    public void destroy() {
        if (authService != null) {
            authService.close();
        }
        super.destroy();
    }
}

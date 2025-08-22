package org.example.demo.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/home/*")
public class HomeController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String name = request.getParameter("name");
        String pathInfo = request.getPathInfo();
        System.out.println("Path Info: " + pathInfo);
        // lấy tên trong cookie
        String userName = request.getCookies()[0].getName();

        request.setAttribute("message", "Xin chào từ Servlet!");
        request.setAttribute("name", name);
        request.getRequestDispatcher("/view/home.jsp").forward(request, response);
    }

}

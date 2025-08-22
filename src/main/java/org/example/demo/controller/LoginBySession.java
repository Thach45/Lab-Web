package org.example.demo.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
@WebServlet("/loginSession")
public class LoginBySession extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        request.getRequestDispatcher("/view/loginSession.jsp").forward(request, response);
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("username");
        String password = req.getParameter("password");
        System.out.println("userName: " + userName);
        System.out.println("password: " + password);
        if ("nguyenhoangthach".equals(userName) && "nguyenhoangthach".equals(password)) {
            //khởi tạo session
            HttpSession session = req.getSession();
            session.setAttribute("username", userName); //lưu trữ thông tin người dùng

            session.setMaxInactiveInterval(30 * 60); //thời gian hết hạn của session là 30 phút
            System.out.println("Session ID: " + session.getId());
            resp.sendRedirect(req.getContextPath() + "/home");
        } else {
            req.setAttribute("error", "Invalid username or password");
            req.getRequestDispatcher("/view/loginSession.jsp").forward(req, resp);
        }
    }
}

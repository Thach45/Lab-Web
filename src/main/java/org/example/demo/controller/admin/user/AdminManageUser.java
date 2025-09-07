package org.example.demo.controller.admin.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.demo.models.UserModel;
import org.example.demo.services.AuthenticationService;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin/manage-user")
public class AdminManageUser extends HttpServlet {
    private AuthenticationService authService;

    @Override
    public void init() throws ServletException {
        authService = new AuthenticationService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        
        try {
            // Lấy danh sách tất cả users
            List<UserModel> users = authService.getAllUsers();
            if (users == null) {
                users = List.of(); // Tránh null pointer
            }
            req.setAttribute("users", users);
            req.setAttribute("totalUsers", users.size());


            req.getRequestDispatcher("/view/page/admin/user/manageUser.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "Không thể tải danh sách người dùng: " + e.getMessage());
            req.getRequestDispatcher("/view/page/admin/user/manageUser.jsp").forward(req, resp);
        }
    }
}

package org.example.demo.controller.admin.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.demo.services.AuthenticationService;

import java.io.IOException;

@WebServlet("/admin/manage-user/activate/*")
public class ActivateUserController extends HttpServlet {
    private AuthenticationService authService;

    @Override
    public void init() throws ServletException {
        authService = new AuthenticationService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // Lấy userId từ URL path
            String pathInfo = req.getPathInfo();
            if (pathInfo == null || pathInfo.length() <= 1) {
                resp.sendRedirect(req.getContextPath() + "/admin/manage-user");
                return;
            }
            
            String userId = pathInfo.substring(1);
            
            // Kích hoạt user
            boolean activated = authService.activateUser(userId);
            
            if (activated) {
                resp.sendRedirect(req.getContextPath() + "/admin/manage-user?success=User activated successfully");
            } else {
                resp.sendRedirect(req.getContextPath() + "/admin/manage-user?error=Failed to activate user");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect(req.getContextPath() + "/admin/manage-user?error=" + e.getMessage());
        }
    }
}

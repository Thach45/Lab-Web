package org.example.demo.controller.admin.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.demo.models.UserModel;
import org.example.demo.services.AuthenticationService;

import java.io.IOException;

@WebServlet("/admin/manage-user/edit/*")
public class EditUserController extends HttpServlet {
    private AuthenticationService authService;

    @Override
    public void init() throws ServletException {
        authService = new AuthenticationService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        
        try {
            // Lấy userId từ URL path
            String pathInfo = req.getPathInfo();
            if (pathInfo == null || pathInfo.length() <= 1) {
                resp.sendRedirect(req.getContextPath() + "/admin/manage-user");
                return;
            }
            
            int userId = Integer.parseInt(pathInfo.substring(1));
            
            // Lấy thông tin user
            UserModel user = authService.getUserById(userId);
            if (user == null) {
                req.setAttribute("error", "Không tìm thấy người dùng");
                req.getRequestDispatcher("/view/page/admin/user/manageUser.jsp").forward(req, resp);
                return;
            }
            
            req.setAttribute("user", user);
            req.getRequestDispatcher("/view/page/admin/user/editUser.jsp").forward(req, resp);
        } catch (NumberFormatException e) {
            resp.sendRedirect(req.getContextPath() + "/admin/manage-user");
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "Không thể tải thông tin người dùng: " + e.getMessage());
            req.getRequestDispatcher("/view/page/admin/user/manageUser.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        try {
            // Lấy userId từ URL path
            String pathInfo = req.getPathInfo();
            if (pathInfo == null || pathInfo.length() <= 1) {
                resp.sendRedirect(req.getContextPath() + "/admin/manage-user");
                return;
            }
            
            int userId = Integer.parseInt(pathInfo.substring(1));
            
            // Lấy thông tin từ form
            String username = req.getParameter("username");
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            String confirmPassword = req.getParameter("confirmPassword");
            String fullName = req.getParameter("fullName");
            String phone = req.getParameter("phone");
            String role = req.getParameter("role");
            String isActiveStr = req.getParameter("isActive");
            String address = req.getParameter("address");

            // Validation
            if (username == null || username.trim().isEmpty()) {
                req.setAttribute("error", "Tên đăng nhập không được để trống");
                req.getRequestDispatcher("/view/page/admin/user/editUser.jsp").forward(req, resp);
                return;
            }

            if (email == null || email.trim().isEmpty()) {
                req.setAttribute("error", "Email không được để trống");
                req.getRequestDispatcher("/view/page/admin/user/editUser.jsp").forward(req, resp);
                return;
            }

            if (password != null && !password.trim().isEmpty()) {
                if (!password.equals(confirmPassword)) {
                    req.setAttribute("error", "Mật khẩu xác nhận không khớp");
                    req.getRequestDispatcher("/view/page/admin/user/editUser.jsp").forward(req, resp);
                    return;
                }
            }

            if (role == null || role.trim().isEmpty()) {
                req.setAttribute("error", "Vai trò không được để trống");
                req.getRequestDispatcher("/view/page/admin/user/editUser.jsp").forward(req, resp);
                return;
            }

            // Lấy thông tin user hiện tại
            UserModel user = authService.getUserById(userId);
            if (user == null) {
                req.setAttribute("error", "Không tìm thấy người dùng");
                req.getRequestDispatcher("/view/page/admin/user/manageUser.jsp").forward(req, resp);
                return;
            }

            // Cập nhật thông tin
            user.setUsername(username);
            user.setEmail(email);
            if (password != null && !password.trim().isEmpty()) {
                user.setPassword(password);
            }
            user.setFullName(fullName);
            user.setPhone(phone);
            user.setRole(role);
            user.setActive("true".equals(isActiveStr));
            user.setAddress(address);

            // Cập nhật user trong database
            boolean updated = authService.updateUser(user);

            if (updated) {
                resp.sendRedirect(req.getContextPath() + "/admin/manage-user");
            } else {
                req.setAttribute("error", "Không thể cập nhật thông tin người dùng");
                req.getRequestDispatcher("/view/page/admin/user/editUser.jsp").forward(req, resp);
            }
        } catch (NumberFormatException e) {
            resp.sendRedirect(req.getContextPath() + "/admin/manage-user");
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "Đã xảy ra lỗi: " + e.getMessage());
            req.getRequestDispatcher("/view/page/admin/user/editUser.jsp").forward(req, resp);
        }
    }
}

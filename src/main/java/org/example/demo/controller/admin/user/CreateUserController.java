package org.example.demo.controller.admin.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.demo.models.UserModel;
import org.example.demo.services.AuthenticationService;

import java.io.IOException;

@WebServlet("/admin/manage-user/create")
public class CreateUserController extends HttpServlet {
    private AuthenticationService authService;

    @Override
    public void init() throws ServletException {
        authService = new AuthenticationService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        req.getRequestDispatcher("/view/page/admin/user/createUser.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        try {
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
                req.getRequestDispatcher("/view/page/admin/user/createUser.jsp").forward(req, resp);
                return;
            }

            if (email == null || email.trim().isEmpty()) {
                req.setAttribute("error", "Email không được để trống");
                req.getRequestDispatcher("/view/page/admin/user/createUser.jsp").forward(req, resp);
                return;
            }

            if (password == null || password.trim().isEmpty()) {
                req.setAttribute("error", "Mật khẩu không được để trống");
                req.getRequestDispatcher("/view/page/admin/user/createUser.jsp").forward(req, resp);
                return;
            }

            if (!password.equals(confirmPassword)) {
                req.setAttribute("error", "Mật khẩu xác nhận không khớp");
                req.getRequestDispatcher("/view/page/admin/user/createUser.jsp").forward(req, resp);
                return;
            }

            if (role == null || role.trim().isEmpty()) {
                req.setAttribute("error", "Vai trò không được để trống");
                req.getRequestDispatcher("/view/page/admin/user/createUser.jsp").forward(req, resp);
                return;
            }

            // Tạo đối tượng User mới
            UserModel user = new UserModel();
            user.setUsername(username);
            user.setEmail(email);
            user.setPassword(password);
            user.setFullName(fullName);
            user.setPhone(phone);
            user.setRole(role);
            user.setActive("true".equals(isActiveStr));
            user.setAddress(address);

            // Tạo user trong database
            UserModel createdUser = authService.createUser(user);

            if (createdUser != null) {
                resp.sendRedirect(req.getContextPath() + "/admin/manage-user");
            } else {
                req.setAttribute("error", "Không thể tạo người dùng mới. Có thể tên đăng nhập hoặc email đã tồn tại.");
                req.getRequestDispatcher("/view/page/admin/user/createUser.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "Đã xảy ra lỗi: " + e.getMessage());
            req.getRequestDispatcher("/view/page/admin/user/createUser.jsp").forward(req, resp);
        }
    }
}

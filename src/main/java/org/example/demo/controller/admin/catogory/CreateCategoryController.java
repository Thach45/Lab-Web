package org.example.demo.controller.admin.catogory;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.demo.models.Category;
import org.example.demo.services.CategoryService;

import java.io.IOException;

@WebServlet("/admin/manage-category/create")
public class CreateCategoryController extends HttpServlet {
    private CategoryService categoryService;

    @Override
    public void init() throws ServletException {
        categoryService = new CategoryService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        req.getRequestDispatcher("/view/page/admin/category/createCategory.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        try {
            // Lấy thông tin từ form
            String name = req.getParameter("name");
            String description = req.getParameter("description");

            // Tạo đối tượng Category mới
            Category category = new Category();
            category.setName(name);
            category.setDescription(description);

            // Lưu category vào database
            Category createdCategory = categoryService.createCategory(category);

            if (createdCategory != null) {
                resp.sendRedirect(req.getContextPath() + "/admin/manage-category");
            } else {
                req.setAttribute("error", "Không thể tạo danh mục mới");
                req.getRequestDispatcher("/view/page/admin/category/createCategory.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "Đã xảy ra lỗi: " + e.getMessage());
            req.getRequestDispatcher("/view/page/admin/category/createCategory.jsp").forward(req, resp);
        }
    }
}

package org.example.demo.controller.admin.catogory;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.demo.models.Category;
import org.example.demo.services.CategoryService;

import java.io.IOException;

@WebServlet("/admin/manage-category/update/*")
public class EditCategoryController extends HttpServlet {
    private CategoryService categoryService;

    @Override
    public void init() throws ServletException {
        categoryService = new CategoryService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        String categoryId = pathInfo.substring(1); // Removes the leading slash
        System.out.println(categoryId);

        Category category = categoryService.getCategoryById(categoryId);
        System.out.println(category);
        if (category != null) {
            req.setAttribute("category", category);
            req.getRequestDispatcher("/view/page/admin/category/editCategory.jsp").forward(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath() + "/admin/manage-category");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        try {
            String categoryId = req.getParameter("categoryId");
            String name = req.getParameter("name");
            String description = req.getParameter("description");

            Category category = new Category();
            category.setCategoryId(categoryId);
            category.setName(name);
            category.setDescription(description);

            categoryService.updateCategory(category);
            resp.sendRedirect(req.getContextPath() + "/admin/manage-category");

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "Đã xảy ra lỗi: " + e.getMessage());
            req.getRequestDispatcher("/view/page/admin/category/editCategory.jsp").forward(req, resp);
        }
    }
}

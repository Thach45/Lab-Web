package org.example.demo.controller.admin.catogory;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.demo.services.CategoryService;

import java.io.IOException;

@WebServlet("/admin/manage-category/delete/*")
public class DeleteCategoryController extends HttpServlet {
    private CategoryService categoryService;

    @Override
    public void init() throws ServletException {
        categoryService = new CategoryService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String pathInfo = req.getPathInfo();
            String categoryId = pathInfo.substring(1); // Removes the leading slash

            categoryService.deleteCategory(categoryId);
            resp.sendRedirect(req.getContextPath() + "/admin/manage-category");

        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect(req.getContextPath() + "/admin/manage-category?error=delete-failed");
        }
    }
}

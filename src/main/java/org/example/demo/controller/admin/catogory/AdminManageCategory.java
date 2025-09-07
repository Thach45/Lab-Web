package org.example.demo.controller.admin.catogory;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.demo.models.Category;
import org.example.demo.services.CategoryService;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin/manage-category")
public class AdminManageCategory extends HttpServlet {
    private CategoryService categoryService;

    @Override
    public void init() throws ServletException {
        categoryService = new CategoryService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        List<Category> categories = categoryService.getAllCategories();
        req.setAttribute("categories", categories);

        req.getRequestDispatcher("/view/page/admin/category/manageCategory.jsp").forward(req, resp);
    }
}

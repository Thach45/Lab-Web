package org.example.demo.controller.admin.product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.demo.service.ProductService;

import java.io.IOException;

@WebServlet("/admin/manage-product/delete/*")
public class DeleteProductController extends HttpServlet {
    private ProductService productService;

    @Override
    public void init() throws ServletException {
        productService = new ProductService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String id = request.getPathInfo().substring(1);
            productService.deleteProduct(id);
            response.sendRedirect(request.getContextPath() + "/admin/manage-product");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/admin/manage-product");
        }
    }

    @Override
    public void destroy() {
        if (productService != null) {
            productService.close();
        }
        super.destroy();
    }
}

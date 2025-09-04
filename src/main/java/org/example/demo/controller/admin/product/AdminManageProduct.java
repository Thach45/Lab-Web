package org.example.demo.controller.admin.product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.demo.entitys.Product;
import org.example.demo.service.ProductService;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin/manage-product")
public class AdminManageProduct extends HttpServlet {
    private ProductService productService;

    @Override
    public void init() throws ServletException {
        productService = new ProductService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        List<Product> products = productService.getAllProducts();
        req.setAttribute("products", products);

        req.getRequestDispatcher("/view/page/admin/product/manageProduct.jsp").forward(req, resp);
    }

    @Override
    public void destroy() {
        if (productService != null) {
            productService.close();
        }
        super.destroy();
    }
}

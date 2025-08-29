package org.example.demo.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.demo.dao.ProductDAO;
import org.example.demo.models.Product;
import org.example.demo.services.ProductService;

import java.io.IOException;
import java.util.List;

@WebServlet("/home/*")
public class HomeController extends HttpServlet {
    private ProductService productService;

    @Override
    public void init() throws ServletException {
        productService = new ProductService(new ProductDAO());
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
         List<Product> products = productService.getAllProducts();
        String userName = (String) request.getSession().getAttribute("username");

        request.setAttribute("product", products);
        request.setAttribute("userName", userName);
        request.getRequestDispatcher("/view/page/client/home.jsp").forward(request, response);
    }

}

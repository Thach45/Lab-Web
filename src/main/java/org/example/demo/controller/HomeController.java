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
        String userName = "";
        for(Product p : products) {
            System.out.println("1" + p.getName());
        }
        Cookie[] cookies = request.getCookies(); // Lấy tất cả cookie
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals("username")) {
                    userName = cookie.getValue(); // Lấy giá trị của cookie có tên "usernam
                    break;
                }
            }
            }
        request.setAttribute("product", products);
        request.setAttribute("userName", userName);
        request.getRequestDispatcher("/view/page/client/home.jsp").forward(request, response);
    }

}

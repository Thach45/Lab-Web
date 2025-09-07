package org.example.demo.controller;

import io.github.cdimascio.dotenv.Dotenv;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterConfig;
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
    private String SECRET_KEY;
    @Override
    public void init() throws ServletException {
        productService = new ProductService(new ProductDAO());
        Dotenv dotenv = Dotenv.configure().load();
        SECRET_KEY = dotenv.get("SECRET_KEY");
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
         List<Product> products = productService.getAllProducts();
        String token = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if ("token".equals(c.getName())) {
                    token = c.getValue();
                }
            }
        }
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody();

        request.setAttribute("product", products);
        request.setAttribute("userName", claims.getSubject());
        request.getRequestDispatcher("/view/page/client/home.jsp").forward(request, response);
    }

}

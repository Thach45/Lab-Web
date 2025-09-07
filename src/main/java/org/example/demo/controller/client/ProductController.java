package org.example.demo.controller.client;

import io.github.cdimascio.dotenv.Dotenv;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.demo.dao.ProductDAO;
import org.example.demo.models.Product;
import org.example.demo.models.Category;
import org.example.demo.services.ProductService;
import org.example.demo.services.CategoryService;

import java.io.IOException;
import java.util.List;
@WebServlet("/product")
public class ProductController extends HttpServlet {
    private ProductService productService;
    private CategoryService categoryService;
    private String SECRET_KEY;
    
    @Override
    public void init() throws ServletException {
        productService = new ProductService(new ProductDAO());
        categoryService = new CategoryService();
        Dotenv dotenv = Dotenv.configure().load();
        SECRET_KEY = dotenv.get("SECRET_KEY");
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        
        // Lấy tham số từ request
        String category = request.getParameter("category");
        String priceRange = request.getParameter("priceRange");
        String sortBy = request.getParameter("sortBy");
        String pageParam = request.getParameter("page");
        
        // Giá trị mặc định
        if (category == null) category = "all";
        if (priceRange == null) priceRange = "all";
        if (sortBy == null) sortBy = "default";
        if (pageParam == null) pageParam = "1";
        
        int page = 1;
        int pageSize = 9; // 9 sản phẩm mỗi trang
        
        try {
            page = Integer.parseInt(pageParam);
            if (page < 1) page = 1;
        } catch (NumberFormatException e) {
            page = 1;
        }
        
        // Lấy danh sách sản phẩm với filter và phân trang
        List<Product> products = productService.getProductsWithFilter(category, priceRange, sortBy, page, pageSize);
        
        // Lấy tổng số sản phẩm và tính tổng số trang
        int totalProducts = productService.countProductsWithFilter(category, priceRange);
        int totalPages = productService.getTotalPages(category, priceRange, pageSize);
        
        // Lấy danh sách categories để hiển thị trong filter
        List<Category> categories = categoryService.getAllCategories();
        
        // Lấy thông tin user từ token
        String userName = null;
        String token = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if ("token".equals(c.getName())) {
                    token = c.getValue();
                    break;
                }
            }
        }
        
        if (token != null) {
            try {
                Claims claims = Jwts.parser()
                        .setSigningKey(SECRET_KEY.getBytes())
                        .build()
                        .parseClaimsJws(token)
                        .getBody();
                userName = claims.getSubject();
            } catch (Exception e) {
                // Token không hợp lệ, để userName = null
            }
        }
        
        // Set attributes cho JSP
        request.setAttribute("products", products);
        request.setAttribute("categories", categories);
        request.setAttribute("userName", userName);
        request.setAttribute("currentCategory", category);
        request.setAttribute("currentPriceRange", priceRange);
        request.setAttribute("currentSortBy", sortBy);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("totalProducts", totalProducts);
        request.setAttribute("pageSize", pageSize);
        
        request.getRequestDispatcher("/view/page/client/product/displayProduct.jsp").forward(request, response);
    }
}

package org.example.demo.controller.admin.product;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.example.demo.dao.ProductDAO;
import org.example.demo.models.Category;
import org.example.demo.models.Product;
import org.example.demo.models.Size;
import io.github.cdimascio.dotenv.Dotenv;
import org.example.demo.services.CategoryService;
import org.example.demo.services.ProductService;
import org.example.demo.services.SizeService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet("/admin/manage-product/create")
@MultipartConfig
public class CreateProductController extends HttpServlet {

    private Cloudinary cloudinary;
    private ProductService productService;
    private CategoryService categoryService;
    private SizeService sizeService;

    @Override
    public void init() {
        Dotenv dotenv = Dotenv.configure().load();
        cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", dotenv.get("CLOUDINARY_CLOUD_NAME"),
                "api_key", dotenv.get("CLOUDINARY_API_KEY"),
                "api_secret", dotenv.get("CLOUDINARY_API_SECRET"),
                "secure", true
        ));
        productService = new ProductService(new ProductDAO());
        categoryService = new CategoryService();
        sizeService = new SizeService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        List<Category> categories = categoryService.getAllCategories();
        req.setAttribute("categories", categories);
        req.getRequestDispatcher("/view/page/admin/product/createProduct.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        try {
            // Lấy thông tin cơ bản của sản phẩm
            String name = request.getParameter("name");
            String categoryId = request.getParameter("categoryId");
            double price = Double.parseDouble(request.getParameter("price"));
            String description = request.getParameter("description");
            boolean isBestSeller = request.getParameter("best_seller") != null;

            // Xử lý upload ảnh
            Part filePart = request.getPart("productImage");
            String imageUrl = null;
            if (filePart != null && filePart.getSize() > 0) {
                byte[] fileBytes = filePart.getInputStream().readAllBytes();
                Map uploadOptions = ObjectUtils.asMap("folder", "milkteashop");
                Map uploadResult = cloudinary.uploader().upload(fileBytes, uploadOptions);
                imageUrl = (String) uploadResult.get("secure_url");
            } else {
                imageUrl = "https://res.cloudinary.com/drblblupt/image/upload/v1756445107/ssq0tpsl7zx3bhlrx44a.png";
            }

            // Xử lý thông tin size
            List<Size> sizes = new ArrayList<>();
            int totalStock = 0;
            int i = 0;
            while (request.getParameter("sizes[" + i + "].name") != null) {
                String sizeName = request.getParameter("sizes[" + i + "].name");
                int sizeStock = Integer.parseInt(request.getParameter("sizes[" + i + "].stock"));
                
                Size size = new Size();
                size.setName(sizeName);
                size.setStock(sizeStock);
                sizes.add(size);
                
                totalStock += sizeStock;
                i++;
            }

            // Tạo sản phẩm với tổng số lượng từ các size
            Product newProduct = new Product(name, price, "", description, imageUrl, isBestSeller, totalStock, categoryId);
            Product createdProduct = productService.createProduct(newProduct);

            // Tạo các size cho sản phẩm
            for (Size size : sizes) {
                size.setProductId(createdProduct.getId());
                sizeService.createSize(size);
            }

            System.out.println("Thêm sản phẩm thành công!");
            System.out.println("Tên: " + name);
            System.out.println("URL ảnh: " + imageUrl);
            System.out.println("Tổng số lượng: " + totalStock);

            response.sendRedirect("/admin/manage-product");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/admin/manage-product");
        }
    }
}
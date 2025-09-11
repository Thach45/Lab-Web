package org.example.demo.controller.admin.product;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import io.github.cdimascio.dotenv.Dotenv;
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
import org.example.demo.services.CategoryService;
import org.example.demo.services.ProductService;
import org.example.demo.services.SizeService;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@WebServlet("/admin/manage-product/update/*")
@MultipartConfig
public class UpdateProductController extends HttpServlet {
    private ProductService productService;
    private Cloudinary cloudinary;
    private CategoryService categoryService;
    private SizeService sizeService;

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
        String id = req.getPathInfo().substring(1);
        Product product = productService.getProductById(id);
        List<Category> categories = categoryService.getAllCategories();
        List<Size> size = sizeService.getSizesByProductId(id);

        resp.setContentType("text/html");
        req.setAttribute("product", product);
        req.setAttribute("categories", categories);
        req.setAttribute("sizes", size);

        req.getRequestDispatcher("/view/page/admin/product/updateProduct.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        try {
            String name = request.getParameter("name");
            double price = Double.parseDouble(request.getParameter("price"));
            String category = request.getParameter("category");
            String description = request.getParameter("description");
            String id = request.getParameter("id");
            boolean isBestSeller = request.getParameter("best_seller") != null;



            // Xử lý upload ảnh mới nếu có
            Part filePart = request.getPart("productImage");
            String imageUrl;
            if (filePart != null && filePart.getSize() > 0) {
                byte[] fileBytes = filePart.getInputStream().readAllBytes();
                Map uploadOptions = ObjectUtils.asMap("folder", "milkteashop");
                Map uploadResult = cloudinary.uploader().upload(fileBytes, uploadOptions);
                imageUrl = (String) uploadResult.get("secure_url");
            } else {
                imageUrl = request.getParameter("image_url_old");
            }
            List<Size> sizes = sizeService.getSizesByProductId(id);
            for (Size s : sizes) {
                String stockStr = request.getParameter("stocks[" + s.getSizeId() + "]");
                if (stockStr != null) {
                    int stock = Integer.parseInt(stockStr);
                    s.setStock(stock);
                    sizeService.updateSize(s); // viết thêm hàm updateSize trong SizeService/DAO
                }
            }

            // 2️⃣ Tính lại totalStock
            int totalStock = sizeService.totalStockByProductId(id);

            // 3️⃣ Update product
            Product updatedProduct = new Product(
                    id,
                    name,
                    price,
                    description,
                    imageUrl,
                    isBestSeller,
                    totalStock,
                    category
            );
            productService.updateProduct(updatedProduct);



            response.sendRedirect("/admin/manage-product");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/admin/manage-product");
        }
    }
}
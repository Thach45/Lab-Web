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
import io.github.cdimascio.dotenv.Dotenv;
import org.example.demo.services.CategoryService;
import org.example.demo.services.ProductService;

import java.io.IOException;
import java.util.List;
import java.util.Map;

// 1. Khai báo URL pattern cho Servlet
@WebServlet("/admin/manage-product/create")
// 2. Bật tính năng xử lý multipart/form-data (bắt buộc để upload file)
@MultipartConfig
public class CreateProductController extends HttpServlet {

    private Cloudinary cloudinary;
    private ProductService productService;
    private CategoryService categoryService;


    // Khởi tạo Cloudinary khi Servlet được load lần đầu
    @Override
    public void init() {
        // 3. Cấu hình Cloudinary với thông tin tài khoản của bạn
        Dotenv dotenv = Dotenv.configure().load();
        cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", dotenv.get("CLOUDINARY_CLOUD_NAME"),
                "api_key", dotenv.get("CLOUDINARY_API_KEY"),
                "api_secret", dotenv.get("CLOUDINARY_API_SECRET"),
                "secure", true

        ));
        productService = new ProductService(new ProductDAO());
        categoryService = new CategoryService();
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
        // Đảm bảo request được xử lý với encoding UTF-8
        request.setCharacterEncoding("UTF-8");

        try {
            // 4. Lấy các thông tin từ form (text fields)
            String name = request.getParameter("name");
            String categoryId = request.getParameter("categoryId");
            double price = Double.parseDouble(request.getParameter("price"));
            int stock = Integer.parseInt(request.getParameter("stock"));
            String size = request.getParameter("size");
            String description = request.getParameter("description");
            // Dùng getParameter trả về "on" nếu được check, null nếu không.
            boolean isBestSeller = request.getParameter("best_seller") != null;

            // 5. Lấy file ảnh từ request
            Part filePart = request.getPart("productImage"); // "productImage" là name của input type="file"

            String imageUrl = null;
            if (filePart != null && filePart.getSize() > 0) {
                // 6. Upload file lên Cloudinary
                // Chuyển file Part thành byte array để upload
                byte[] fileBytes = filePart.getInputStream().readAllBytes();
                // Tùy chọn upload (nếu cần)
                Map uploadOptions = ObjectUtils.asMap("folder", "milkteashop");
                // Gọi API để upload và nhận kết quả trả về
                Map uploadResult = cloudinary.uploader().upload(fileBytes, uploadOptions);

                // 7. Lấy URL an toàn (https) của ảnh sau khi upload thành công
                imageUrl = (String) uploadResult.get("secure_url");
            } else {
                // Xử lý trường hợp người dùng không upload ảnh (nếu cho phép)
                // Ví dụ: gán một ảnh mặc định
                imageUrl = "https://res.cloudinary.com/drblblupt/image/upload/v1756445107/ssq0tpsl7zx3bhlrx44a.png";
            }

            Product newProduct = new Product(name, price, size, description, imageUrl, isBestSeller,stock, categoryId );


            Product product = productService.createProduct(newProduct);
            System.out.println("Thêm sản phẩm thành công!");
            System.out.println("Tên: " + name);
            System.out.println("URL ảnh: " + imageUrl);

            // 9. Chuyển hướng người dùng về trang danh sách sản phẩm
             response.sendRedirect("/home");

        } catch (Exception e) {
            // Xử lý lỗi (ví dụ: log lỗi, hiển thị trang lỗi)
            e.printStackTrace();
//            request.setAttribute("errorMessage", "Đã xảy ra lỗi trong quá trình thêm sản phẩm.");
//            request.getRequestDispatcher("/views/admin/error.jsp").forward(request, response);
        }
    }
}
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
import org.example.demo.models.Product;
import org.example.demo.services.ProductService;

import java.io.IOException;
import java.util.Map;

@WebServlet("/admin/manage-product/update/*")
@MultipartConfig
public class UpdateProductController extends HttpServlet {
    private ProductService productService;
    private Cloudinary cloudinary;
    public void init() {
        Dotenv dotenv = Dotenv.configure().load();
        cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", dotenv.get("CLOUDINARY_CLOUD_NAME"),
                "api_key", dotenv.get("CLOUDINARY_API_KEY"),
                "api_secret", dotenv.get("CLOUDINARY_API_SECRET"),
                "secure", true

        ));
        productService = new ProductService(new ProductDAO());
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getPathInfo().substring(1);
        Product product = productService.getProductById(id);
        System.out.println(product);

        resp.setContentType("text/html");
        req.setAttribute("product", product);
        req.getRequestDispatcher("/view/page/admin/product/updateProduct.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Đảm bảo request được xử lý với encoding UTF-8
        request.setCharacterEncoding("UTF-8");

        try {
            // 4. Lấy các thông tin từ form (text fields)
            String name = request.getParameter("name");
            double price = Double.parseDouble(request.getParameter("price"));
            int stock = Integer.parseInt(request.getParameter("stock"));
            String size = request.getParameter("size");
            String description = request.getParameter("description");
            String id = request.getParameter("id");
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
                imageUrl = request.getParameter("image_url_old");;
            }

            Product newProduct = new Product(id,name, price, size, description, imageUrl, isBestSeller,stock );


            productService.updateProduct(newProduct);

            System.out.println("Thêm sản phẩm thành công!");
            System.out.println("Tên: " + name);
            System.out.println("URL ảnh: " + imageUrl);

            // 9. Chuyển hướng người dùng về trang danh sách sản phẩm
            response.sendRedirect("/admin/manage-product");

        } catch (Exception e) {
            // Xử lý lỗi (ví dụ: log lỗi, hiển thị trang lỗi)
            e.printStackTrace();
//            request.setAttribute("errorMessage", "Đã xảy ra lỗi trong quá trình thêm sản phẩm.");
//            request.getRequestDispatcher("/views/admin/error.jsp").forward(request, response);
        }
    }
}

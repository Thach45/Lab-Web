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
import org.example.demo.entitys.Product;
import org.example.demo.service.ProductService;

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
        productService = new ProductService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getPathInfo().substring(1);
        Product product = productService.getProductById(id);

        resp.setContentType("text/html");
        req.setAttribute("product", product);
        req.getRequestDispatcher("/view/page/admin/product/updateProduct.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        try {
            String id = request.getParameter("id");
            String name = request.getParameter("name");
            double price = Double.parseDouble(request.getParameter("price"));
            int stock = Integer.parseInt(request.getParameter("stock"));
            String size = request.getParameter("size");
            String description = request.getParameter("description");
            boolean isBestSeller = request.getParameter("best_seller") != null;

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

            Product updatedProduct = new Product(id, name, price, size, description, imageUrl, isBestSeller, stock);
            productService.updateProduct(updatedProduct);

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

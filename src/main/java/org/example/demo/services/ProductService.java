package org.example.demo.services;

import org.example.demo.dao.ProductDAO;
import org.example.demo.models.Product;

import java.util.List;

public class ProductService {
    private final ProductDAO productDAO;

    // inject DAO qua constructor
    public ProductService(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }
    public List<Product> getAllProducts() {
        List<Product> products = productDAO.findAllProduct();

        return products;
    }
    public Product getProductById(String productId) {

        try{
            Product p = productDAO.findProductById(productId);

            return p;
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public Product createProduct(Product product) {

        Product existProduct = productDAO.findProductById(product.getId());
        if (existProduct != null) {
            throw new IllegalArgumentException("Product with ID " + product.getId() + " already exists.");
        }

        return productDAO.createProduct(product);
    }
    public void deleteProduct(String productId) {
        Product existProduct = productDAO.findProductById(productId);
        if (existProduct == null) {
            throw new IllegalArgumentException("Product with ID " + productId + " does not exist.");
        }
        productDAO.deleteProduct(productId);
    }
    public void updateProduct(Product product) {
        Product existProduct = productDAO.findProductById(product.getId());
        if (existProduct == null) {
            throw new IllegalArgumentException("Product with ID " + product.getId() + " does not exist.");
        }
        try{
            productDAO.updateProduct(product);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Lấy sản phẩm với lọc và phân trang
    public List<Product> getProductsWithFilter(String category, String priceRange, String sortBy, int page, int pageSize) {
        return productDAO.findProductsWithFilter(category, priceRange, sortBy, page, pageSize);
    }

    // Đếm tổng số sản phẩm với filter
    public int countProductsWithFilter(String category, String priceRange) {
        return productDAO.countProductsWithFilter(category, priceRange);
    }

    // Tính tổng số trang
    public int getTotalPages(String category, String priceRange, int pageSize) {
        int totalProducts = countProductsWithFilter(category, priceRange);
        return (int) Math.ceil((double) totalProducts / pageSize);
    }
}

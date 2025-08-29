package org.example.demo.services;

import org.example.demo.dao.ProductDAO;
import org.example.demo.models.Product;

import java.text.DecimalFormat;
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
            return productDAO.findProductById(productId);
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
}

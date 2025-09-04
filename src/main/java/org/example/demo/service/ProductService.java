package org.example.demo.service;

import org.example.demo.entitys.Product;
import org.example.demo.repository.ProductRepository;
import java.util.List;

public class ProductService {
    private final ProductRepository productRepository;

    public ProductService() {
        this.productRepository = new ProductRepository();
    }

    public void addProduct(Product product) {
        productRepository.save(product);
    }

    public void updateProduct(Product product) {
        productRepository.update(product);
    }

    public void deleteProduct(String id) {
        productRepository.delete(id);
    }

    public Product getProductById(String id) {
        return productRepository.findById(id);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public void close() {
        productRepository.close();
    }
}

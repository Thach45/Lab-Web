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
}

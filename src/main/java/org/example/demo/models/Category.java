package org.example.demo.models;

import java.util.List;
import java.util.UUID;

public class Category {
    private String categoryId;
    private String name;
    private String description;
    private List<Product> products;

    public Category() {
        this.categoryId = UUID.randomUUID().toString();
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}

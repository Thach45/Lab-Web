package org.example.demo.models;

public class Product {
    private String id;
    private String name;
    private String size;
    private String description;
    private String image_url;
    private double price;
    private Boolean best_seller;
    private int stock;

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Boolean getBest_seller() {
        return best_seller;
    }

    public void setBest_seller(Boolean best_seller) {
        this.best_seller = best_seller;
    }

    public Product(String id, String name, double price, String size, String description, String image_url, Boolean best_seller, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.size = size;
        this.description = description;
        this.image_url = image_url;
        this.best_seller = best_seller;
        this.stock = stock;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';

    }
}

package org.example.demo.models;

public class Size {
    private String sizeId;
    private String productId;
    private int stock;
    private String name;

    public Size() {
    }

    public Size(String sizeId, String productId, int stock, String name) {
        this.sizeId = sizeId;
        this.productId = productId;
        this.stock = stock;
        this.name = name;
    }

    public String getSizeId() {
        return sizeId;
    }

    public void setSizeId(String sizeId) {
        this.sizeId = sizeId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Thêm phương thức để kiểm tra size có sẵn để chọn hay không
    public boolean isAvailable() {
        return productId == null;
    }
}
package org.example.demo.models;

import java.util.UUID;

public class Topping {
    private String topping_id;
    private String name;
    private double price;

    public Topping() {
        this.topping_id = UUID.randomUUID().toString();
    }

    public Topping(String topping_id, String name, double price) {
        this.topping_id = topping_id;
        this.name = name;
        this.price = price;
    }

    public String getTopping_id() {
        return topping_id;
    }

    public void setTopping_id(String topping_id) {
        this.topping_id = topping_id;
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
}



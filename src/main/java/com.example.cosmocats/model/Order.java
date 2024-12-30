package com.example.cosmocats.model;

import java.util.List;

public class Order {
    private Long id;
    private List<Product> products;
    private Double totalPrice;

    // Constructors, getters, setters, etc.
    public Order() {}

    public Order(Long id, List<Product> products, Double totalPrice) {
        this.id = id;
        this.products = products;
        this.totalPrice = totalPrice;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}

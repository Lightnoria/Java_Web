package com.example.cosmocats.dto;

import java.util.List;

public class OrderDTO {
    private Long id;
    private List<ProductDTO> products;
    private Double totalPrice;

    // Constructors
    public OrderDTO() {}

    public OrderDTO(Long id, List<ProductDTO> products, Double totalPrice) {
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

    public List<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDTO> products) {
        this.products = products;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}

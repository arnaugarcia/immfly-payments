package com.immfly.payments.domain.model;

import java.math.BigDecimal;

public class Product {
    private Long id;
    private String name;
    private BigDecimal price;
    private Category category;
    private String image;

    public Product(Long id, String name, BigDecimal price, Category category, String image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.image = image;
    }

    public Long id() { return id; }
    public void id(Long id) { this.id = id; }

    public String name() { return name; }
    public void name(String name) { this.name = name; }

    public BigDecimal price() { return price; }
    public void price(BigDecimal price) { this.price = price; }

    public Category category() { return category; }
    public void category(Category category) { this.category = category; }

    public String image() { return image; }
    public void image(String image) { this.image = image; }
}

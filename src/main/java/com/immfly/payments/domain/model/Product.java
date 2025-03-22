package com.immfly.payments.domain.model;

import java.math.BigDecimal;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Product product = (Product) o;
        return Objects.equals(id, product.id) && Objects.equals(name, product.name)
            && Objects.equals(price, product.price) && Objects.equals(category, product.category)
            && Objects.equals(image, product.image);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(id);
        result = 31 * result + Objects.hashCode(name);
        result = 31 * result + Objects.hashCode(price);
        result = 31 * result + Objects.hashCode(category);
        result = 31 * result + Objects.hashCode(image);
        return result;
    }
}

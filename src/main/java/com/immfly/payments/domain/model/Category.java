package com.immfly.payments.domain.model;

public class Category {
    private final Long id;
    private final String name;
    private Category parent;

    public Category(Long id, String name, Category parent) {
        this.id = id;
        this.name = name;
        this.parent = parent;
    }

    public Long id() { return id;}
    public String name() { return name; }
    public Category parent() { return parent; }

    public void parent(Category parent) { this.parent = parent;}
}

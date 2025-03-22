package com.immfly.payments.domain.model;

import java.util.Objects;

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

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        Category category = (Category) object;
        return Objects.equals(id, category.id) &&
            Objects.equals(name, category.name) &&
            Objects.equals(parent, category.parent);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(id);
        result = 31 * result + Objects.hashCode(name);
        result = 31 * result + Objects.hashCode(parent);
        return result;
    }
}

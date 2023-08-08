package com.instana.shop;

public class Product {

    private String id;

    public Product(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id='" + id + '\'' +
                '}';
    }
}

package com.jumbotail.shipping.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {
    @Id
    private String id;
    private String name;
    private String sellerId;
    private double weightKg;

    public Product() {}
    // ... 
}

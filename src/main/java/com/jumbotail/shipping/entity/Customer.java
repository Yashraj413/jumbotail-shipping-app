package com.jumbotail.shipping.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "customers")
public class Customer {
    @Id
    private String id;
    private String name;
    private String phone;
    @Embedded
    private Location location;

    public Customer() {}
    // ... full getters/setters similar to above
}

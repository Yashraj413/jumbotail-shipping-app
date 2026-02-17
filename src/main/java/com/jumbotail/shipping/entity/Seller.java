package com.jumbotail.shipping.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "sellers")
public class Seller {
    @Id
    private String id;
    private String name;
    @Embedded
    private Location location;

}

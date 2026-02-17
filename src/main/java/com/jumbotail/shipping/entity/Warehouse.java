package com.jumbotail.shipping.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "warehouses")
public class Warehouse {
    @Id
    private String id;
    private String name;
    @Embedded
    private Location location;

}

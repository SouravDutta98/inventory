package com.shopping.inventory.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table
public class InventoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private int quantity;
    private double price;
}

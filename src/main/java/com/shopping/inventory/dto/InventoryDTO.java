package com.shopping.inventory.dto;

import lombok.Data;

@Data
public class InventoryDTO {
    private Long id;
    private String name;
    private String description;
    private int quantity;
    private double price;
}

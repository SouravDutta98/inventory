package com.shopping.inventory.service;

import com.shopping.inventory.dto.InventoryDTO;

import java.util.List;
import java.util.Optional;

public interface InventoryService {
    List<InventoryDTO> getAllProducts();

    Optional<InventoryDTO> getProductById(Long id);

    InventoryDTO addProduct(InventoryDTO product);

    InventoryDTO updateProduct(Long id, InventoryDTO productDetails);

    void deleteProduct(Long id);
}

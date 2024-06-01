package com.shopping.inventory.controller;

import com.shopping.inventory.dto.InventoryDTO;
import com.shopping.inventory.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/inventory")
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;

    @GetMapping
    public List<InventoryDTO> getAllProducts() {
        return inventoryService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventoryDTO> getProductById(@PathVariable Long id) {
        return inventoryService.getProductById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public InventoryDTO addProduct(@RequestBody InventoryDTO productDTO) {
        return inventoryService.addProduct(productDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InventoryDTO> updateProduct(@PathVariable Long id, @RequestBody InventoryDTO productDetails) {
        try {
            InventoryDTO updatedProduct = inventoryService.updateProduct(id, productDetails);
            return ResponseEntity.ok(updatedProduct);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        inventoryService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}

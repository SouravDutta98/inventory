package com.shopping.inventory.serviceImpl;

import com.shopping.inventory.dto.InventoryDTO;
import com.shopping.inventory.entity.InventoryEntity;
import com.shopping.inventory.repository.InventoryRepository;
import com.shopping.inventory.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InventoryServiceImpl implements InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;

    @Override
    public List<InventoryDTO> getAllProducts() {
        List<InventoryEntity> entities = inventoryRepository.findAll();
        return entities.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public Optional<InventoryDTO> getProductById(Long id) {
        return inventoryRepository.findById(id).map(this::convertToDTO);
    }

    @Override
    public InventoryDTO addProduct(InventoryDTO productDTO) {
        InventoryEntity entity = convertToEntity(productDTO);
        InventoryEntity savedEntity = inventoryRepository.save(entity);
        return convertToDTO(savedEntity);
    }

    @Override
    public InventoryDTO updateProduct(Long id, InventoryDTO productDetails) {
        Optional<InventoryEntity> productOptional = inventoryRepository.findById(id);
        if (productOptional.isPresent()) {
            InventoryEntity product = productOptional.get();
            product.setName(productDetails.getName());
            product.setDescription(productDetails.getDescription());
            product.setQuantity(productDetails.getQuantity());
            product.setPrice(productDetails.getPrice());
            InventoryEntity updatedEntity = inventoryRepository.save(product);
            return convertToDTO(updatedEntity);
        } else {
            throw new RuntimeException("Product not found with id " + id);
        }
    }

    @Override
    public void deleteProduct(Long id) {
        inventoryRepository.deleteById(id);
    }

    private InventoryDTO convertToDTO(InventoryEntity entity) {
        InventoryDTO dto = new InventoryDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setQuantity(entity.getQuantity());
        dto.setPrice(entity.getPrice());
        return dto;
    }

    private InventoryEntity convertToEntity(InventoryDTO dto) {
        InventoryEntity entity = new InventoryEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setQuantity(dto.getQuantity());
        entity.setPrice(dto.getPrice());
        return entity;
    }
}

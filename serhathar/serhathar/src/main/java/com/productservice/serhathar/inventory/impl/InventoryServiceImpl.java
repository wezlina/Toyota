package com.productservice.serhathar.inventory.impl;

import com.productservice.serhathar.category.impl.CategoryServiceImpl;
import com.productservice.serhathar.inventory.api.InventoryDto;
import com.productservice.serhathar.inventory.api.InventoryService;
import com.productservice.serhathar.product.api.ProductDto;
import com.productservice.serhathar.product.impl.Product;
import com.productservice.serhathar.product.impl.ProductServiceImpl;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository repository;
    private final CategoryServiceImpl categoryService;
    private final ProductServiceImpl productService;

    @Override
    @Transactional
    public InventoryDto createInventory(InventoryDto inventoryDto) {
        checkInventoryExists(inventoryDto);
        Inventory inventory = toEntity(inventoryDto);
        return toDto(repository.save(inventory));
    }


    @Override
    public InventoryRepository updateInventory(String id, InventoryDto dto) {

        return null;
    }

    @Override
    public int addProductToInventory(String inventoryId, String productId) {
        Inventory inventory = repository.getInventoryById(inventoryId);
        Product product = productService.getProductById(productId);
        inventory.getProductList().add(product);
        repository.save(inventory);
        return inventory.getProductList().size();
    }

    @Override
    public void removeProductFromInventory(String inventoryId, String productId) {
        Inventory inventory = repository.getInventoryById(inventoryId);
        Product product = productService.getProductById(productId);
        inventory.getProductList().remove(product);
        repository.save(inventory);
    }

    private void checkInventoryExists(InventoryDto dto) {
        repository.findByName(dto.getName()).ifPresent(Inventory -> {
            throw new EntityExistsException(
                    String.format("Entity %s already exists", Inventory.getClass().getName())
            );
        });
    }

    private Inventory checkInventoryUpdate(InventoryDto dto, Inventory inventory) {
        inventory.setName(dto.getName() == null ? inventory.getName() : dto.getName());
        inventory.setName(dto.getName() == null ? inventory.getName() : dto.getName());
        return inventory;
    }

    @Override
    public List<InventoryDto> getAllInventories() {

        return repository.findAll().stream().map(this::toDto).toList();
    }

    @Override
    public InventoryDto getById(String id) {

        return toDto(repository.getInventoryById(id));
    }

    private Inventory toEntity(InventoryDto dto) {
        Inventory inventory = new Inventory();
        inventory.setName(dto.getName());
        inventory.setProductList(toProductList(dto.getId()));
        return inventory;
    }

    private InventoryDto toDto(Inventory inventory) {
        return InventoryDto.builder()
                .id(inventory.getId())
                .productList(toProductDtoList(inventory.getId()))
                .name(inventory.getName())
                .build();
    }


    private List<Product> toProductList(String id) {
        List<Product> productList = new ArrayList<>();
        Product product = new Product();
        for (int i = 0; i < repository.getInventoryById(id).getProductList().size(); i++) {
            product.setId(repository.getInventoryById(id).getProductList().get(i).getId());
            product.setName(repository.getInventoryById(id).getProductList().get(i).getName());
            product.setPrice(repository.getInventoryById(id).getProductList().get(i).getPrice());
            product.setCategoryName(repository.getInventoryById(id).getProductList().get(i).getCategoryName());
            product.setCreaDate(repository.getInventoryById(id).getProductList().get(i).getCreaDate());
            product.setStatus(repository.getInventoryById(id).getProductList().get(i).getStatus());
            product.setBrand(repository.getInventoryById(id).getProductList().get(i).getBrand());
            product.setBarcode(repository.getInventoryById(id).getProductList().get(i).getBarcode());
            product.setDescription(repository.getInventoryById(id).getProductList().get(i).getDescription());
            product.setCategory(repository.getInventoryById(id).getProductList().get(i).getCategory());
            productList.add(product);
        }
        return productList;
    }

    private List<ProductDto> toProductDtoList(String id) {
        List<ProductDto> dtos = new ArrayList<>();
        ProductDto dto = new ProductDto();
        for (int i = 0; i < repository.getInventoryById(id).getProductList().size(); i++) {
            dto.setId(repository.getInventoryById(id).getProductList().get(i).getId());
            dto.setName(repository.getInventoryById(id).getProductList().get(i).getName());
            dto.setPrice(repository.getInventoryById(id).getProductList().get(i).getPrice());
            dto.setCategoryName(repository.getInventoryById(id).getProductList().get(i).getCategoryName());
            dto.setCreaDate(repository.getInventoryById(id).getProductList().get(i).getCreaDate());
            dto.setStatus(repository.getInventoryById(id).getProductList().get(i).getStatus());
            dto.setBrand(repository.getInventoryById(id).getProductList().get(i).getBrand());
            dto.setBarcode(repository.getInventoryById(id).getProductList().get(i).getBarcode());
            dto.setDescription(repository.getInventoryById(id).getProductList().get(i).getDescription());
            dto.setCategory(categoryService.toDto(repository.getInventoryById(id).getProductList().get(i).getCategory()));
            dtos.add(dto);
        }
        return dtos;//hatalııı
    }
}

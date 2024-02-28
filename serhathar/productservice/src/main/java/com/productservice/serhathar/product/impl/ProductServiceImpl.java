package com.productservice.serhathar.product.impl;

import com.productservice.serhathar.category.api.CategoryDto;
import com.productservice.serhathar.category.impl.Category;
import com.productservice.serhathar.category.impl.CategoryServiceImpl;
import com.productservice.serhathar.product.api.ProductDto;
import com.productservice.serhathar.product.api.ProductService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository repository;
    private final CategoryServiceImpl categoryService;

    @Override
    @Transactional
    public ProductDto createProduct(ProductDto dto) {
        checkProductExists(dto);
        Product product = toEntity(dto);
        return toDto(repository.save(product));
    }

    @Override
    @Transactional
    public ProductDto updateProduct(String id, ProductDto dto) {
        return repository.findById(id)
                .map(product -> checkProductUpdate(dto, product))
                .map(repository::save)
                .map(this::toDto)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<ProductDto> getAllProducts() {

        return repository.findAll().stream().map(this::toDto).toList();
    }

    @Override
    public List<ProductDto> findProductByStatusIsTrue() {
        return repository.findAllByStatusTrue().stream().map(this::toDto).toList();
    }

    @Override
    public List<ProductDto> getProductByCategory(String categoryName) {
        Category category = categoryService.getByName(categoryName);
        return category.getProductList().stream().map(this::toDto).toList();
    }

    @Override
    public Product getProductById(String id) {

        return repository.getProductById(id);
    }

    @Override
    public List<ProductDto> findAllByStatusAndCategoryStatus(Boolean productStatus, Boolean categoryStatus) {
        return repository.findAllByStatusAndCategoryStatus(productStatus, categoryStatus).stream().map(this::toDto).toList();
    }

    @Override
    public List<ProductDto> findProductById(String id) {
        return repository.findProductById(id).stream().map(this::toDto).toList();
    }

    @Override
    public String deleteProduct(String id) {
        Product product = repository.getProductById(id);
        product.setStatus(false);
        updateProduct(id, toDto(product));
        return "Product successfully deactivated";
    }

    @Override
    public String activateProduct(String id) {
        Product product = repository.getProductById(id);
        product.setStatus(true);
        updateProduct(id, toDto(product));
        return "Product successfully activated";
    }

    private void checkProductExists(ProductDto dto) {
        repository.findByName(dto.getName()).ifPresent(product -> {
            throw new EntityExistsException(
                    String.format("Entity %s already exists", product.getClass().getName())
            );
        });
    }

    private Product checkProductUpdate(ProductDto dto, Product product) {
        product.setName(dto.getName() == null ? product.getName() : dto.getName());
        product.setPrice(dto.getPrice() == null ? product.getPrice() : dto.getPrice());
        product.setBrand(dto.getBrand() == null ? product.getBrand() : dto.getBrand());
        product.setBarcode(dto.getBarcode() == null ? product.getBarcode() : dto.getBarcode());
        product.setDescription(dto.getDescription() == null ? product.getDescription() : dto.getDescription());
        return product;
    }

    private Product toEntity(ProductDto dto) {
        Category category = categoryService.getById(dto.getCategory().getId());
        Product product = new Product();
        product.setName(dto.getName());
        product.setCategoryName(dto.getCategoryName());
        product.setBrand(dto.getBrand());
        product.setBarcode(dto.getBarcode());
        product.setCategory(category);
        product.setPrice(dto.getPrice());
        product.setAmount(dto.getAmount());
        product.setDescription(dto.getDescription());
        return product;
    }

    public ProductDto toDto(Product product) {
        CategoryDto category = categoryService.toDto(product.getCategory());
        return ProductDto.builder()
                .id(product.getId())
                .status(product.getStatus())
                .creaDate(product.getCreaDate())
                .description(product.getDescription())
                .brand(product.getBrand())
                .amount(product.getAmount())
                .name(product.getName())
                .barcode(product.getBarcode())
                .price(product.getPrice())
                .category(category)
                .build();
    }
}
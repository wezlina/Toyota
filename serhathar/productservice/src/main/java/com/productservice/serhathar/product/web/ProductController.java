package com.productservice.serhathar.product.web;

import com.productservice.serhathar.product.api.ProductDto;
import com.productservice.serhathar.product.impl.ProductServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/product-service")
public class ProductController {
    private final ProductServiceImpl service;//impl mimari hatasi

    @PostMapping(path = "/add")
    public ResponseEntity<ProductResponse> createProduct(@Valid @RequestBody ProductRequest request) {
        ProductDto product = service.createProduct(request.toDto());
        return ResponseEntity.ok(ProductResponse.toResponse(product));
    }

    @GetMapping(path = "/get/{categoryName}")
    public ResponseEntity<List<ProductResponse>> getAllProducts(@PathVariable String categoryName) {
        List<ProductResponse> productResponseList = toResponse(service.getProductByCategory(categoryName));
        return ResponseEntity.ok(productResponseList);//bunda bi sıkıntı var
    }

    @GetMapping(path = "/get/{id}")
    public ResponseEntity<List<ProductResponse>> getProductById(@PathVariable String id) {
        List<ProductResponse> productResponseList = toResponse(service.findProductById(id));
        return ResponseEntity.ok(productResponseList);
    }

    @GetMapping(path = "getBy/{id}")
    public ProductDto getProductById1(@PathVariable String id) {
        return service.toDto(service.getProductById(id));
    }

    @GetMapping(path = "/get-all")
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        List<ProductResponse> productResponseList = toResponse(service.getAllProducts());
        return ResponseEntity.ok(productResponseList);
    }

    @GetMapping(path = "/get-active")
    public ResponseEntity<List<ProductResponse>> getActiveProducts() {
        List<ProductResponse> productResponseList = toResponse(service.findProductByStatusIsTrue());
        return ResponseEntity.ok(productResponseList);
    }

    @GetMapping(path = "/get-all-active")
    public ResponseEntity<List<ProductResponse>> getAllActiveProducts() {
        List<ProductResponse> productResponseList = toResponse(service.findAllByStatusAndCategoryStatus(true, true));
        return ResponseEntity.ok(productResponseList);
    }

    @PutMapping(path = "/activate-status/{id}")
    public ResponseEntity<ProductResponse> activateCategory(@PathVariable(value = "id") String id) {
        ProductDto dto = service.activateProduct(id);
        return ResponseEntity.ok(ProductResponse.toResponse(dto));
    }

    @PutMapping(path = "/update/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable(value = "id") String id, @Valid @RequestBody ProductRequest request) {
        ProductDto dto = service.updateProduct(id, request.toDto());
        return ResponseEntity.ok(ProductResponse.toResponse(dto));
    }

    @PutMapping(path = "/delete-status/{id}")
    public ResponseEntity<ProductResponse> delete_statusProduct(@PathVariable(value = "id") String id) {
        ProductDto dto = service.deleteProduct(id);
        return ResponseEntity.ok(ProductResponse.toResponse(dto));
    }

    public List<ProductResponse> toResponse(List<ProductDto> productDtoList) {
        return productDtoList.stream().map(ProductResponse::toResponse).toList();
    }
}
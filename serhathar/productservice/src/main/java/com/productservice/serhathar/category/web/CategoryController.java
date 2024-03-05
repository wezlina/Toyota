package com.productservice.serhathar.category.web;

import com.productservice.serhathar.category.api.CategoryDto;
import com.productservice.serhathar.category.api.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/product-service/category")
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping(path = "/add")
    public ResponseEntity<CategoryResponse> createCategory(@Valid @RequestBody CategoryRequest request) {
        CategoryDto category = categoryService.createCategory(request.toDto());
        return ResponseEntity.ok(CategoryResponse.toResponse(category));
    }

    @GetMapping(path = "/get-all")
    public ResponseEntity<List<CategoryResponse>> getAllCategory() {
        List<CategoryResponse> categoryResponseList = toResponse(categoryService.getAllCategory());
        return ResponseEntity.ok(categoryResponseList);
    }

    @GetMapping(path = "/get-active")
    public ResponseEntity<List<CategoryResponse>> getActiveCategories() {
        List<CategoryResponse> categoryResponseList = toResponse(categoryService.findByStatusTrue());
        return ResponseEntity.ok(categoryResponseList);
    }

    @PutMapping(path = "/get/{id}")
    public ResponseEntity<CategoryResponse> updateCategory(@PathVariable(value = "id") String id, @Valid @RequestBody CategoryDto dto) {
        CategoryDto category = categoryService.updateCategory(id, dto);
        return ResponseEntity.ok(CategoryResponse.toResponse(category));
    }

    @PutMapping(path = "/activate-status/{id}")
    public ResponseEntity<CategoryResponse> activateCategory(@PathVariable(value = "id") String id) {
        CategoryDto dto = categoryService.activateCategory(id);
        return ResponseEntity.ok(CategoryResponse.toResponse(dto));
    }

    @PutMapping(path = "/delete-status/{id}")
    public ResponseEntity<CategoryResponse> delete_statusCategory(@PathVariable(value = "id") String id) {
        CategoryDto dto = categoryService.deleteCategory(id);
        return ResponseEntity.ok(CategoryResponse.toResponse(dto));
    }

    @DeleteMapping(path = "/delete/{id}")
    void delete(@PathVariable String id) {
        //categoryService.deleteCategory(id);//will only open for admin
    }

    public List<CategoryResponse> toResponse(List<CategoryDto> categoryDtoList) {
        return categoryDtoList.stream().map(CategoryResponse::toResponse).toList();
    }
}
package com.swiftylions.SLStore.controller;

import com.swiftylions.SLStore.dto.CreateProductRequestDto;
import com.swiftylions.SLStore.dto.ProductResponseDto;
import com.swiftylions.SLStore.dto.UpdateProductRequestDto;
import com.swiftylions.SLStore.service.IProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/admin/products") // ← تغییر مسیر
@RequiredArgsConstructor
public class AdminProductController { // ← نام جدید

    private final IProductService iProductService;

    @PostMapping
    public ResponseEntity<ProductResponseDto> createProduct(
            @Valid @RequestBody CreateProductRequestDto request) {
        ProductResponseDto response = iProductService.createProduct(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDto> updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody UpdateProductRequestDto request) {
        ProductResponseDto response = iProductService.updateProduct(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        iProductService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}

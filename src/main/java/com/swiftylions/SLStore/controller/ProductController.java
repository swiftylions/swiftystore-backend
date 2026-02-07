package com.swiftylions.SLStore.controller;

import com.swiftylions.SLStore.dto.CreateProductRequestDto;
import com.swiftylions.SLStore.dto.ProductResponseDto;
import com.swiftylions.SLStore.dto.UpdateProductRequestDto;
import com.swiftylions.SLStore.service.IProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final IProductService iProductService;

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getProducts() {
        List<ProductResponseDto> productList = iProductService.getProducts();
        return ResponseEntity.ok(productList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> getProductById(@PathVariable Long id) {
        ProductResponseDto product = iProductService.getProductById(id);
        return ResponseEntity.ok(product);
    }
}

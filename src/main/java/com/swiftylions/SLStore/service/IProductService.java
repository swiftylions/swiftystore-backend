package com.swiftylions.SLStore.service;

import com.swiftylions.SLStore.dto.CreateProductRequestDto;
import com.swiftylions.SLStore.dto.ProductResponseDto;
import com.swiftylions.SLStore.dto.ProfileRequestDto;
import com.swiftylions.SLStore.dto.UpdateProductRequestDto;

import java.util.List;

public interface IProductService {
    List<ProductResponseDto> getProducts();
    ProductResponseDto getProductById(Long id);
    ProductResponseDto createProduct(CreateProductRequestDto request);
    ProductResponseDto updateProduct(Long id, UpdateProductRequestDto request);
    void deleteProduct(Long id);
}

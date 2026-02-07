package com.swiftylions.SLStore.service.impl;

import com.swiftylions.SLStore.dto.CreateProductRequestDto;
import com.swiftylions.SLStore.dto.ProductResponseDto;
import com.swiftylions.SLStore.dto.UpdateProductRequestDto;
import com.swiftylions.SLStore.entity.Product;
import com.swiftylions.SLStore.exception.ResourceNotFoundException;
import com.swiftylions.SLStore.repository.ProductRepository;
import com.swiftylions.SLStore.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService {

    private final ProductRepository productRepository;

    @Override
    @Cacheable(value = "products", key = "'allProducts'")
    public List<ProductResponseDto> getProducts() {
        return productRepository.findAll().stream().map(this::transformToDTO).collect(Collectors.toList());
    }

    @Override
    @Cacheable(value = "products", key = "#id")
    public ProductResponseDto getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Product", "ProductID", id.toString()));
        return transformToDTO(product);
    }

    @Override
    @CacheEvict(value = "products", key = "'allProducts'")
    @Transactional
    public ProductResponseDto createProduct(CreateProductRequestDto request) {
        String currentUser = SecurityContextHolder.getContext()
                .getAuthentication().getName();

        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setImageUrl(request.getImageUrl());
        product.setPopularity(0);
        product.setCreatedBy(currentUser);

        Product saved = productRepository.save(product);
        return transformToDTO(saved);
    }

    @Override
    @CachePut(value = "products", key = "#id")
    @CacheEvict(value = "products", key = "'allProducts'")
    @Transactional
    public ProductResponseDto updateProduct(Long id, UpdateProductRequestDto request) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Product", "ProductID", id.toString()));

        String currentUser = SecurityContextHolder.getContext()
                .getAuthentication().getName();

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setImageUrl(request.getImageUrl());
        product.setUpdatedBy(currentUser);
        // popularity رو دست نمیزنیم - خودکار محاسبه میشه از Order

        Product updated = productRepository.save(product);
        return transformToDTO(updated);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "products", key = "#id"),
            @CacheEvict(value = "products", key = "'allProducts'")
    })
    @Transactional
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product", "ProductID", id.toString()));
        productRepository.delete(product);
    }

    private ProductResponseDto transformToDTO (Product product) {
        ProductResponseDto productDto = new ProductResponseDto();
        BeanUtils.copyProperties(product, productDto);
        productDto.setProductId(product.getId());
        return productDto;
    }
}

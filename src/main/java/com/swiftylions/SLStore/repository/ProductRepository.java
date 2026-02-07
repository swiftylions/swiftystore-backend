package com.swiftylions.SLStore.repository;

import com.swiftylions.SLStore.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
  }
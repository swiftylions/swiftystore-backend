package com.swiftylions.SLStore.dto;

import java.math.BigDecimal;

public record OrderItemDto(Long productId, Integer quantity, BigDecimal price) {
}

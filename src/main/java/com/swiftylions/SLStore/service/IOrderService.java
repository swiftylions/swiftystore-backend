package com.swiftylions.SLStore.service;

import com.swiftylions.SLStore.dto.OrderRequestDto;
import com.swiftylions.SLStore.dto.OrderResponseDto;
import com.swiftylions.SLStore.entity.Order;

import java.util.List;

public interface IOrderService {
    void createOrder(OrderRequestDto orderRequest);

    List<OrderResponseDto> getCustomerOrders();
    List<OrderResponseDto> getAllPendingOrders();

    void updatedOrderStatus(Long orderId,String orderStatus);
}

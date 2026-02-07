package com.swiftylions.SLStore.controller;

import com.stripe.service.climate.OrderService;
import com.swiftylions.SLStore.constants.ApplicationConstants;
import com.swiftylions.SLStore.dto.OrderRequestDto;
import com.swiftylions.SLStore.dto.OrderResponseDto;
import com.swiftylions.SLStore.dto.ResponseDto;
import com.swiftylions.SLStore.entity.Order;
import com.swiftylions.SLStore.service.IOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final IOrderService iOrderService;

    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody OrderRequestDto requestDto) {
        iOrderService.createOrder(requestDto);
        return ResponseEntity.ok("Order Created Successfully");
    }

    @GetMapping
    public ResponseEntity<List<OrderResponseDto>> loadCustomerOrders() {
        return ResponseEntity.ok(iOrderService.getCustomerOrders());
    }
}

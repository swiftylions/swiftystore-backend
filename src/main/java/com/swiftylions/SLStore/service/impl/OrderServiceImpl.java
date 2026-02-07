package com.swiftylions.SLStore.service.impl;

import com.swiftylions.SLStore.constants.ApplicationConstants;
import com.swiftylions.SLStore.dto.OrderItemResponseDto;
import com.swiftylions.SLStore.dto.OrderRequestDto;
import com.swiftylions.SLStore.dto.OrderResponseDto;
import com.swiftylions.SLStore.entity.Customer;
import com.swiftylions.SLStore.entity.Order;
import com.swiftylions.SLStore.entity.OrderItem;
import com.swiftylions.SLStore.entity.Product;
import com.swiftylions.SLStore.exception.ResourceNotFoundException;
import com.swiftylions.SLStore.repository.OrderRepository;
import com.swiftylions.SLStore.repository.ProductRepository;
import com.swiftylions.SLStore.service.IOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements IOrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final ProfileServiceImpl profileService;

    @Override
    public void createOrder(OrderRequestDto orderRequest) {
        //get logged in customer info
        Customer customer = profileService.getAuthenticatedCustomer();

        //create order
        Order order = new Order();
        order.setCustomer(customer);
        BeanUtils.copyProperties(orderRequest, order);
        order.setOrderStatus(ApplicationConstants.ORDER_STATUS_CREATED);
        // map order items
        List<OrderItem> orderItems = orderRequest.items().stream().map(item -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            Product product = productRepository.findById(item.productId()).orElseThrow(() -> new ResourceNotFoundException("Product", "ProductID", item.productId().toString()));

            product.setPopularity(product.getPopularity() + item.quantity());
            productRepository.save(product);

            orderItem.setProduct(product);
            orderItem.setOrder(order);
            orderItem.setQuantity(item.quantity());
            orderItem.setPrice(item.price());
            return orderItem;
        }).collect(Collectors.toList());
        order.setOrderItems(orderItems);
        orderRepository.save(order);
    }

    @Override
    public List<OrderResponseDto> getCustomerOrders() {
        Customer customer = profileService.getAuthenticatedCustomer();
        List<Order> orders = orderRepository.findOrdersByCustomerWithNativeQuery(customer.getCustomerId());
        return orders.stream().map(this::mapToOrderResponseDTO).collect(Collectors.toList());
    }

    @Override
    public List<OrderResponseDto> getAllPendingOrders() {
        List<Order> orders = orderRepository.findOrderByStatusWithNativeQuery(ApplicationConstants.ORDER_STATUS_CREATED);
        return orders.stream().map(this::mapToOrderResponseDTO).collect(Collectors.toList());
    }

    @Override
    public void updatedOrderStatus(Long orderId, String orderStatus) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        orderRepository.updateOrderStatus(orderId, orderStatus, email);
    }

    //map Order entity to OrderResponseDto
    private OrderResponseDto mapToOrderResponseDTO(Order order) {
        List<OrderItemResponseDto> itemDTOs = order.getOrderItems().stream()
                .map(this::mapToOrderItemResponseDTO)
                .collect(Collectors.toList());
        OrderResponseDto orderResponseDto = new OrderResponseDto(order.getOrderId(), order.getOrderStatus()
                , order.getTotalPrice(), order.getCreatedAt().toString(), itemDTOs);
        return orderResponseDto;
    }

    //map OrderItem entity to OrderItemResponseDto

    private OrderItemResponseDto mapToOrderItemResponseDTO(OrderItem orderItem) {
        OrderItemResponseDto itemDTO = new OrderItemResponseDto(
                orderItem.getProduct().getName(), orderItem.getQuantity(),
                orderItem.getPrice(), orderItem.getProduct().getImageUrl());
        return itemDTO;
    }
}

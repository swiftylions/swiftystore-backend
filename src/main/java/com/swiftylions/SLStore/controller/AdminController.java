package com.swiftylions.SLStore.controller;

import com.swiftylions.SLStore.constants.ApplicationConstants;
import com.swiftylions.SLStore.dto.ContactRequestDto;
import com.swiftylions.SLStore.dto.ContactResponseDto;
import com.swiftylions.SLStore.dto.OrderResponseDto;
import com.swiftylions.SLStore.dto.ResponseDto;
import com.swiftylions.SLStore.entity.Order;
import com.swiftylions.SLStore.service.IContactService;
import com.swiftylions.SLStore.service.IOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {
    private final IOrderService iOrderService;
    private final IContactService iContactService;

    @GetMapping("/orders")
    public ResponseEntity<List<OrderResponseDto>> getAllPendingOrders() {
        return ResponseEntity.ok().body(iOrderService.getAllPendingOrders());
    }

    @PatchMapping("/orders/{orderId}/confirm")
    public ResponseEntity<ResponseDto> confirmOrder(@PathVariable Long orderId) {
        iOrderService.updatedOrderStatus(orderId, ApplicationConstants.ORDER_STATUS_CONFIRMED);
        return ResponseEntity.ok(
                new ResponseDto("200", "Order #" + orderId + " has been approved."));
    }

    @PatchMapping("/orders/{orderId}/cancel")
    public ResponseEntity<ResponseDto> cancelOrder(@PathVariable Long orderId) {
        iOrderService.updatedOrderStatus(orderId, ApplicationConstants.ORDER_STATUS_CANCELLED);
        return ResponseEntity.ok(
                new ResponseDto("200", "Order #" + orderId + " has been cancelled."));
    }

    @GetMapping("/messages")
    public ResponseEntity<List<ContactResponseDto>> getAllOpenMessages() {
        return ResponseEntity.ok(iContactService.getAllOpenMessages());
    }

    @PatchMapping("/messages/{contactId}/close")
    public ResponseEntity<ResponseDto> closeMessages(@PathVariable Long contactId) {
        iContactService.updateMessageStatus(contactId, ApplicationConstants.CLOSED_MESSAGE);
        return ResponseEntity.ok(
                new ResponseDto("200", "Contact #" + contactId + " has been closed."));
    }

}

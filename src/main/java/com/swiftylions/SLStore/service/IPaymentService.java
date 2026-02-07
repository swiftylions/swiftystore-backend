package com.swiftylions.SLStore.service;

import com.swiftylions.SLStore.dto.PaymentIntentRequestDto;
import com.swiftylions.SLStore.dto.PaymentIntentResponseDto;

public interface IPaymentService {
    PaymentIntentResponseDto createPaymentIntent(PaymentIntentRequestDto requestDto);
}

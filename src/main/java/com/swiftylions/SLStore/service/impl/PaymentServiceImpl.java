package com.swiftylions.SLStore.service.impl;

import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import com.swiftylions.SLStore.dto.PaymentIntentRequestDto;
import com.swiftylions.SLStore.dto.PaymentIntentResponseDto;
import com.swiftylions.SLStore.service.IPaymentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PaymentServiceImpl implements IPaymentService {
    @Override
    @Transactional
    public PaymentIntentResponseDto createPaymentIntent(PaymentIntentRequestDto requestDto) {
        try {
            PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                    .setAmount(requestDto.amount())
                    .setCurrency(requestDto.currency())
                    .addPaymentMethodType("card")
                    .build();
            PaymentIntent paymentIntent = PaymentIntent.create(params);
            return new PaymentIntentResponseDto(paymentIntent.getClientSecret());
        } catch (StripeException e) {
            System.out.println("Stripe message: " + e.getMessage());

            if (e.getStripeError() != null) {
                System.out.println("Stripe code: " + e.getStripeError().getCode());
                System.out.println("Stripe param: " + e.getStripeError().getParam());
                System.out.println("Stripe type: " + e.getStripeError().getType());
            }

            throw new RuntimeException(e.getMessage(), e);
        }
//        } catch (StripeException e) {
//            throw new RuntimeException("Failed to create payment intent",e);
//        }
    }
}

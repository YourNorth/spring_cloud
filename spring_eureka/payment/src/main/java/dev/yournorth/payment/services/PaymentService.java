package dev.yournorth.payment.services;

import dev.yournorth.payment.common.PaymentRequest;
import dev.yournorth.payment.common.PaymentResponse;
import dev.yournorth.payment.entities.Payment;
import dev.yournorth.payment.repositories.PaymentRep;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
public class PaymentService {

    final PaymentRep paymentRep;

    public PaymentService(PaymentRep paymentRep) {
        this.paymentRep = paymentRep;
    }

    public PaymentResponse createPayment(PaymentRequest paymentRequest) {
        var payment = makePayment(paymentRequest);
        return PaymentResponse.builder()
                .status(payment.getStatus())
                .orderId(paymentRequest.getOrderId())
                .build();
    }

    private Payment makePayment(PaymentRequest paymentRequest) {
        var payment = Payment.builder()
                .status(new Random().nextBoolean() ? "ok" : "oops")
                .orderId(paymentRequest.getOrderId())
                .price(paymentRequest.getPrice())
                .transactionID(UUID.randomUUID().toString())
                .build();
        return paymentRep.save(payment);
    }

}

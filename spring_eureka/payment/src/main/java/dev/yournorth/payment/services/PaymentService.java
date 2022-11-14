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
        paymentRep.save(payment);
        return convertPaymentToResponse(payment);
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

    private PaymentResponse convertPaymentToResponse(Payment payment){
        return PaymentResponse.builder()
                .orderId(payment.getOrderId())
                .status(payment.getStatus())
                .build();
    }

    public PaymentResponse findByOrderId(Long id) {

        var payment = paymentRep.findByOrderId(id);
        return payment.map(this::convertPaymentToResponse).orElse(null);
    }
}

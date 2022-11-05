package dev.yournorth.order.services;

import dev.yournorth.order.common.PaymentRequest;
import dev.yournorth.order.common.PaymentResponse;
import dev.yournorth.order.entities.Order;
import dev.yournorth.order.repositories.OrderRep;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {
    static final String BAD_PAYMENT_MSG = "Oops, smt goes wrong. Try to pay later";
    static final String GOOD_PAYMENT_MSG = "Payment successful!";

    final OrderRep orderRep;
    final RestTemplate restTemplate;

    public OrderService(OrderRep orderRep, RestTemplate restTemplate) {
        this.orderRep = orderRep;
        this.restTemplate = restTemplate;
    }

    public String makeOrder(Order order) {
        var savedOrder = orderRep.save(order);
        PaymentRequest paymentRequest =
                PaymentRequest.builder()
                        .orderId(savedOrder.getId())
                        .price(order.getPrice())
                        .build();
        var response = restTemplate.postForEntity("PAYMENT-SERVICE/payments/payment", paymentRequest, PaymentResponse.class);
        var paymentResponse = response.getBody();
        return checkPayment(paymentResponse);
    }

    private String checkPayment(PaymentResponse paymentResponse){

        if(paymentResponse==null || paymentResponse.getStatus()==null) return BAD_PAYMENT_MSG;
        if (paymentResponse.getStatus().equals("ok")) return GOOD_PAYMENT_MSG;
        return BAD_PAYMENT_MSG;
    }

}

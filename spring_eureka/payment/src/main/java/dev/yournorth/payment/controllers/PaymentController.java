package dev.yournorth.payment.controllers;

import dev.yournorth.payment.common.PaymentRequest;
import dev.yournorth.payment.common.PaymentResponse;
import dev.yournorth.payment.services.PaymentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/payment")
    public PaymentResponse postPayment(@RequestBody PaymentRequest paymentRequest){
        return paymentService.createPayment(paymentRequest);
    }

    @GetMapping("/{orderId}")
    public PaymentResponse findPayment(@PathVariable Long orderId){
        return paymentService.findByOrderId(orderId);
    }
}

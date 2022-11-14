package dev.yournorth.payment.common;

import dev.yournorth.payment.entities.Payment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class PaymentResponse {
    private Long orderId;
    private String status;
}

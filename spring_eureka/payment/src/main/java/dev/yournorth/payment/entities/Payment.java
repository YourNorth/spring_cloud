package dev.yournorth.payment.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "PAYMENT_TB")
public class Payment {

    @Id
    @GeneratedValue
    private Long id;
    private String status;
    private String transactionID;
    private Long orderId;
    private int price;
}

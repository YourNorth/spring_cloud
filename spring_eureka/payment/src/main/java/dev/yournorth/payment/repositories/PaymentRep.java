package dev.yournorth.payment.repositories;

import dev.yournorth.payment.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRep extends JpaRepository<Payment, Long> {
}

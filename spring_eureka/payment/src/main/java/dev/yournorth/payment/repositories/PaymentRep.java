package dev.yournorth.payment.repositories;

import dev.yournorth.payment.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRep extends JpaRepository<Payment, Long> {
    Optional<Payment> findByOrderId(Long id);
}

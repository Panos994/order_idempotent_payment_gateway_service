package e_commerce.order_and_idempotent_payment_gateway_api.repository;

import e_commerce.order_and_idempotent_payment_gateway_api.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, UUID> {
    List<Payment> findByOrderId(UUID orderId);
    List<Payment> findByIdempotencyKey(String idempotencyKey);
}

package e_commerce.order_and_idempotent_payment_gateway_api.repository;

import e_commerce.order_and_idempotent_payment_gateway_api.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {
    List<Order> findByCustomerId(UUID customerId);
}

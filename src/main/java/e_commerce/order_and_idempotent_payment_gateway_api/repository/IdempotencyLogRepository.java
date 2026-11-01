package e_commerce.order_and_idempotent_payment_gateway_api.repository;

import e_commerce.order_and_idempotent_payment_gateway_api.entity.IdempotencyLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IdempotencyLogRepository extends JpaRepository<IdempotencyLog, UUID> {
    Optional<IdempotencyLog> findByIdempotencyKey(String idempotencyKey);
}

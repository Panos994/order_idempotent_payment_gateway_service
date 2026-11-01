package e_commerce.order_and_idempotent_payment_gateway_api.dto;

import e_commerce.order_and_idempotent_payment_gateway_api.entity.User;
import jakarta.persistence.Column;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateOrderRequestDTO {
    private UUID customerId;
    private BigDecimal totalAmount;
}

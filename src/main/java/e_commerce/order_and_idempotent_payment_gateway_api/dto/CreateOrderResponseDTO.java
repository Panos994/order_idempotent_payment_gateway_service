package e_commerce.order_and_idempotent_payment_gateway_api.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateOrderResponseDTO {
    private UUID customerId;
    private BigDecimal totalAmount;
    public LocalDateTime createdAt;
}

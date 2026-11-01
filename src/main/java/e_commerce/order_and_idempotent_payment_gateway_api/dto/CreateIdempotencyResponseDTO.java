package e_commerce.order_and_idempotent_payment_gateway_api.dto;

import jakarta.persistence.Column;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateIdempotencyResponseDTO {
    private String idempotencyKey;
    private String responseBody;
    private Integer httpStatus;
    private LocalDateTime createdAt;
}

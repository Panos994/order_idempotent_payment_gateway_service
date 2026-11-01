package e_commerce.order_and_idempotent_payment_gateway_api.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateIdempotencyRequestDTO {
    private String idempotencyKey;
}

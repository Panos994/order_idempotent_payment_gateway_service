package e_commerce.order_and_idempotent_payment_gateway_api.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreatePaymentResponseDTO {
    private UUID orderId;
    private String currency;
    private String providerTransactionId;
}

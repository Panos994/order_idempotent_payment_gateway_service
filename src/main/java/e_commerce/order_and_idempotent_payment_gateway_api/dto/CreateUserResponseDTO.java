package e_commerce.order_and_idempotent_payment_gateway_api.dto;

import e_commerce.order_and_idempotent_payment_gateway_api.entity.Role;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateUserResponseDTO {
    private UUID userId;
    private String email;
    private Role role;
}

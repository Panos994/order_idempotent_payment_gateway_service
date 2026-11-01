package e_commerce.order_and_idempotent_payment_gateway_api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginRequestDTO {
    @NotNull
    @Email
    private String email;
    @NotNull
    private String password;
}

package e_commerce.order_and_idempotent_payment_gateway_api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateUserRequestDTO {

    @NotBlank(message="email is required")
    @Email(message = "Email should be valid.")
    private String email;

    @NotBlank(message="password is required")
    private String password;
}

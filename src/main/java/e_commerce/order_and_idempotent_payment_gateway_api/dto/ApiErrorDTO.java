package e_commerce.order_and_idempotent_payment_gateway_api.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiErrorDTO {
    private String message;
    private int status;
    private String error;
    private LocalDateTime timestamp;
    private String path;

}

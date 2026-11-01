package e_commerce.order_and_idempotent_payment_gateway_api.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateProductResponseDTO {
    private String name;
    private BigDecimal price;
    private Integer stockQuantity;
}

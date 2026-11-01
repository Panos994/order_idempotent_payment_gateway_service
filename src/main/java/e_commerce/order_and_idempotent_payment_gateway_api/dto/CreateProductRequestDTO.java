package e_commerce.order_and_idempotent_payment_gateway_api.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateProductRequestDTO {
    private String name;
    private String description;
    private BigDecimal price;
    private Integer stockQuantity;
}

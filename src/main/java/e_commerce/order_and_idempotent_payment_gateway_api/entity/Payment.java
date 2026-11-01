package e_commerce.order_and_idempotent_payment_gateway_api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    private Order order;

    @Column
    private String currency;

    @Enumerated(EnumType.STRING)
    @Column
    private PaymentStatus status;

    @Column(unique = true)
    private String idempotencyKey;
    @Column(nullable = false)
    private BigDecimal amount;

    @Column(unique = true)
    private String providerTransactionId;
}

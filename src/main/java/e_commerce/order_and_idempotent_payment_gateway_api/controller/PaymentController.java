package e_commerce.order_and_idempotent_payment_gateway_api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import e_commerce.order_and_idempotent_payment_gateway_api.dto.CreatePaymentRequestDTO;
import e_commerce.order_and_idempotent_payment_gateway_api.dto.CreatePaymentResponseDTO;
import e_commerce.order_and_idempotent_payment_gateway_api.dto.CreateProductRequestDTO;
import e_commerce.order_and_idempotent_payment_gateway_api.service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<CreatePaymentResponseDTO> processPayment(@RequestHeader("X-Idempotency-Key") String idempotencyKey,@RequestBody CreatePaymentRequestDTO dto) throws JsonProcessingException {
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentService.processPayment(idempotencyKey, dto));
    }
}

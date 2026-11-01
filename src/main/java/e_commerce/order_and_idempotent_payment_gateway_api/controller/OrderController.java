package e_commerce.order_and_idempotent_payment_gateway_api.controller;

import e_commerce.order_and_idempotent_payment_gateway_api.dto.CreateOrderRequestDTO;
import e_commerce.order_and_idempotent_payment_gateway_api.dto.CreateOrderResponseDTO;
import e_commerce.order_and_idempotent_payment_gateway_api.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    @PostMapping("{customerId}")
    public ResponseEntity<CreateOrderResponseDTO> createOrder(@PathVariable UUID customerId, @RequestBody CreateOrderRequestDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(dto, customerId));
    }
}



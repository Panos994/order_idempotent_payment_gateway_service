package e_commerce.order_and_idempotent_payment_gateway_api.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import e_commerce.order_and_idempotent_payment_gateway_api.dto.CreatePaymentRequestDTO;
import e_commerce.order_and_idempotent_payment_gateway_api.dto.CreatePaymentResponseDTO;
import e_commerce.order_and_idempotent_payment_gateway_api.entity.*;
import e_commerce.order_and_idempotent_payment_gateway_api.repository.IdempotencyLogRepository;
import e_commerce.order_and_idempotent_payment_gateway_api.repository.OrderRepository;
import e_commerce.order_and_idempotent_payment_gateway_api.repository.PaymentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentService {
    private final IdempotencyLogRepository idempotencyLogRepository;
    private final PaymentRepository paymentRepository;
    private final ObjectMapper objectMapper;
    private final OrderRepository orderRepository;

    public PaymentService(IdempotencyLogRepository idempotencyLogRepository, PaymentRepository paymentRepository, ObjectMapper objectMapper, OrderRepository orderRepository) {
        this.idempotencyLogRepository = idempotencyLogRepository;
        this.paymentRepository = paymentRepository;
        this.objectMapper = objectMapper;
        this.orderRepository = orderRepository;
    }

    @Transactional
    public CreatePaymentResponseDTO processPayment(String idempotencyKey, CreatePaymentRequestDTO dto) throws JsonProcessingException {
        Optional<IdempotencyLog> existingLog = idempotencyLogRepository.findByIdempotencyKey(idempotencyKey);
        if(existingLog.isPresent()){
            try {
                return objectMapper.readValue(existingLog.get().getResponseBody(), CreatePaymentResponseDTO.class);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
        Order order = orderRepository.findById(dto.getOrderId()).orElseThrow(()-> new IllegalArgumentException("Order not found with this id: " + dto.getOrderId()));
        Payment payment = new Payment();
        payment.setCurrency(dto.getCurrency());
        payment.setOrder(order);
        order.setStatus(OrderStatus.PAID);
        orderRepository.save(order);
        paymentRepository.save(payment);

        CreatePaymentResponseDTO response = CreatePaymentResponseDTO.builder()
                .orderId(order.getId())
                .currency(payment.getCurrency())
                .build();

        IdempotencyLog idempotencyLog = new IdempotencyLog();
        idempotencyLog.setIdempotencyKey(idempotencyKey);
        idempotencyLog.setResponseBody(objectMapper.writeValueAsString(response));
        idempotencyLog.setHttpStatus(201);
        idempotencyLogRepository.save(idempotencyLog);

        return response;



    }
}

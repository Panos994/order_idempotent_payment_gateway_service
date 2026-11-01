package e_commerce.order_and_idempotent_payment_gateway_api.service;

import e_commerce.order_and_idempotent_payment_gateway_api.dto.CreateOrderRequestDTO;
import e_commerce.order_and_idempotent_payment_gateway_api.dto.CreateOrderResponseDTO;
import e_commerce.order_and_idempotent_payment_gateway_api.entity.Order;
import e_commerce.order_and_idempotent_payment_gateway_api.entity.OrderStatus;
import e_commerce.order_and_idempotent_payment_gateway_api.entity.User;
import e_commerce.order_and_idempotent_payment_gateway_api.repository.OrderRepository;
import e_commerce.order_and_idempotent_payment_gateway_api.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public OrderService(OrderRepository orderRepository, UserRepository userRepository1) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository1;
    }

    @Transactional
    public CreateOrderResponseDTO createOrder(CreateOrderRequestDTO dto, UUID customerId){
        User customer = userRepository.findById(customerId).orElseThrow(()-> new IllegalArgumentException("Customer not found with this id: " + customerId));
        Order order = new Order();
        order.setTotalAmount(dto.getTotalAmount());
        order.setCustomer(customer);
        order.setStatus(OrderStatus.PENDING);
        orderRepository.save(order);

        return CreateOrderResponseDTO
                .builder()
                .customerId(customer.getId())
                .totalAmount(order.getTotalAmount())
                .createdAt(order.getCreatedAt())
                .build();
    }
}

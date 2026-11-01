package e_commerce.order_and_idempotent_payment_gateway_api.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}

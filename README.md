# Order & Idempotent Payment Gateway API

Spring Boot REST API for a simple e-commerce flow with JWT authentication, orders, and idempotent payment processing.

## Tech Stack
- Java 17
- Spring Boot (Web, Security, JPA, Validation)
- PostgreSQL
- Maven

## Prerequisites
- JDK 17+
- Maven 3.9+ (or use `./mvnw`)
- Docker (optional, for local PostgreSQL)

## Run Locally
1. Start PostgreSQL (example):
   ```bash
   docker run --name my-postgres-order-gateway-ms -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=order_db -p 5409:5432 -d postgres
   ```
2. Start the API:
   ```bash
   ./mvnw spring-boot:run
   ```

Application runs on: `http://localhost:9981`

## Authentication
1. Create a user via `POST /api/users`
2. Login via `POST /api/auth/login` to get a JWT token
3. Use token in protected endpoints:
   `Authorization: Bearer <token>`

## Main Endpoints
- `POST /api/users` — create user
- `GET /api/users?email=...` — get user by email
- `PATCH /api/users/{userId}?newRole=ROLE_ADMIN` — assign role
- `POST /api/auth/login` — login and receive JWT
- `POST /api/products` — create product
- `GET /api/products` — list products
- `GET /api/products/{productId}` — get product by id
- `POST /api/orders/{customerId}` — create order
- `POST /api/payments` — process payment (requires `X-Idempotency-Key` header)

## Idempotency
Payments are idempotent by `X-Idempotency-Key`. Repeating the same key returns the stored response instead of creating a duplicate payment.

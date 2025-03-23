# ✈️ Payments API

A modular, hexagonally-structured Java backend for managing in-flight payments, orders, and product catalogs.

## 🧱 Architecture Overview

This project follows a **Hexagonal Architecture (Ports and Adapters)** style.

### 📂 Project Structure

```
payments/
├── application          # Use cases (business logic orchestration)
├── domain               # Core domain models, enums, interfaces
├── infrastructure
│   ├── adapter          # REST controllers, Payment Gateway adapters, DB adapters
│   └── persistence      # JPA Entities, Repositories
├── resources/db         # Liquibase schema
└── tests                # Unit & Integration tests
```

## 🔁 Flow Example

1. `POST /api/orders` → Controller
2. `CreateOrderUseCase` (application layer)
3. `OrderRepository` (domain port)
4. `JpaOrderRepository` (infrastructure adapter)

## 🔐 Security

All endpoints are secured via **JWT authentication**. Add a Bearer token using your configured secret.

## 💳 Payment Gateways

- Strategy pattern via `PaymentGatewayFactory`
- Supports `MOCK`, `STRIPE`, `AYDEN`

## ✅ Tests

- Unit tests: JUnit 5, Mockito
- Integration tests: Spring Boot + Testcontainers
- `src/test/java/com/immfly/payments/...`

## 🚀 CI/CD with GitLab

Pipeline runs:

- `mvn compile`
- `mvn test` with PostgreSQL via testcontainers

See `.gitlab-ci.yml`

---

## 📦 Tech Stack

- Java 17
- Spring Boot 3
- Maven
- PostgreSQL + Liquibase
- Testcontainers
- JWT Security

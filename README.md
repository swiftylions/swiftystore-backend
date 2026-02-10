# 🦁 SwiftyStore Back-end Application

### Created by Hussein Khanjani (Swifty Lions)
---
[![Java Version](https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=openjdk)](https://openjdk.org/projects/jdk/21/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-brightgreen?style=for-the-badge&logo=springboot)](https://spring.io/projects/spring-boot)
[![License](https://img.shields.io/badge/License-MIT-blue?style=for-the-badge)](LICENSE)

**SwiftyStore Backend** is a high-performance, secure, and scalable e-commerce RESTful API built with **Java 21** and the **Spring Boot** ecosystem. Designed with industry best practices, this project demonstrates a robust implementation of modern backend architecture, focusing on security, data integrity, and optimized performance.

---

## 🚀 Key Features

- **Advanced Security:** Full implementation of Spring Security with JWT (Stateless Authentication).
- **Payment Integration:** Mock payment processing using the **Stripe API**.
- **High Performance:** Multi-level caching using **Caffeine** and Spring Cache abstraction.
- **Robust Data Layer:** Complex querying with **JPQL** and **JPA Driven Queries**.
- **Monitoring & Health:** Real-time application monitoring via **Spring Actuator**.
- **API Documentation:** Fully documented interactive UI using **OpenAPI 3 & Swagger**.

---

## 🛠 Tech Stack

| Category | Technology |
|--- |--- |
| **Core** | Java 21 (LTS), Spring Boot 3 |
| **Security** | Spring Security, JJWT, BCrypt, CORS/CSRF Protection |
| **Database** | MySQL, Spring Data JPA, Hibernate |
| **Caching** | Caffeine Cache, Spring Cache |
| **Payment** | Stripe (Mock Integration) |
| **API Docs** | Swagger UI, OpenAPI 3 |
| **Monitoring** | Spring Boot Actuator |
| **Build Tool** | Maven |

---

## 🔒 Security Implementation

The project follows a "Security-First" approach:
- **Authentication:** Custom JWT-based filter chain.
- **Authorization:** Role-based access control (RBAC).
- **Password Safety:** Utilizing `BCryptPasswordEncoder`.
- **Validation:** Strict input validation using Jakarta Validation (JSR 380).
- **CORS/CSRF:** Configured for secure cross-origin communication.

---

## 🏗 Architecture & Design Patterns

- **Layered Architecture:** Clear separation of concerns (Controller, Service, Repository, DTO).
- **DTO Pattern:** Ensuring internal entities are never exposed to the client.
- **Exception Handling:** Global exception handling mechanism for consistent API responses.
- **Performance Optimization:** - Efficient database indexing.
  - Reduced DB hits using **Caffeine Cache**.
  - Optimized JPQL queries to prevent N+1 problems.

---

## 🚦 Getting Started

### Prerequisites
- JDK 21
- Maven 3.9+
- MySQL 8.0+

### Installation
1. **Clone the repository:**
   ```bash
   git clone https://github.com/swiftylions/swiftystore-backend.git
   ```
2. **Configure Database:**
   Update `src/main/resources/application.properties` with your MySQL credentials.
3. **Build the project:**
   ```bash
   mvn clean install
   ```
4. **Run the application:**
   ```bash
   mvn spring-boot:run
   ```

---

## 📖 API Documentation
Once the server is running, you can access the interactive Swagger documentation at:
`http://localhost:8080/swagger-ui/index.html`

---

## 👨‍💻 Author
**SwiftyLions** - GitHub: [@swiftylions](https://github.com/swiftylions)  

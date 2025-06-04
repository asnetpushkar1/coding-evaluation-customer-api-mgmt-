
# Customer Management API

A Spring Boot RESTful API for managing customer records including creation, retrieval, update, and deletion. 
Supports querying by ID, name, and email. Designed for API evaluations and 
microservice-style development.

---

## Built With

- [Java 17](https://jdk.java.net/17/)
- [Spring Boot 3.5.0](https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-3.5-Release-Notes)
- [Spring Web](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-web)
- [Spring Data JPA](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-data-jpa)
- [Spring Validation](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-validation)
- [Spring Boot Logging](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-logging)
- [Springdoc OpenAPI](https://mvnrepository.com/artifact/org.springdoc/springdoc-openapi-starter-webmvc-ui/2.8.8)
- [JUnit 5](https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter)
- [Mockito](https://mvnrepository.com/artifact/org.mockito/mockito-core)
- [H2 Database](https://mvnrepository.com/artifact/com.h2database/h2)
- [Lombok](https://mvnrepository.com/artifact/org.projectlombok/lombok)

---

## Prerequisites

- Java 17+
- Maven 3.8+
- Git

---

## How to Run the Project

1. Clone the repository:
   ```bash
   git clone https://github.com/asnetpushkar1/coding-evaluation-customer-api-mgmt-.git
   cd coding-evaluation-customer-api-mgmt-
   ```

2. Run using the provided script:
   ```bash
   chmod +x start.sh
   ```
   
   ```bash
   ./start.sh
   ```

3. The app will start on:
   ```
   http://localhost:8080
   ```

4. Access Swagger UI:
   ```
   http://localhost:8080/swagger-ui/index.html
   ```

---

## Running Tests

```bash
./mvnw test
```

---

## Author

**Pushkar Basnet**  
Email: basnetpushkar53@gmail.com  
GitHub: [@yasnetpushkar1](https://github.com/asnetpushkar1)

---

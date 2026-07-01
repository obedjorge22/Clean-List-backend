# ADR 0003: Backend Technology Stack

## Status

Accepted

---

## Context

CleanList is a web-based cleaning schedule management platform designed to manage cleaning lists, members, groups, schedules, and task assignments. The backend must expose a secure RESTful API, support stateless authentication, provide reliable persistence with a relational database, and remain maintainable, scalable, and easy to evolve.

---

## Decision

The CleanList backend is implemented using the following technology stack:

- Spring Boot
- Spring Web (Spring MVC)
- Spring Data JPA
- Hibernate ORM
- JDBC (MySQL Connector/J)
- MySQL
- HTTP/JSON communication
- Maven for dependency management

---

## Rationale

The project follows a traditional synchronous architecture based on Spring MVC and JPA. This approach was selected because it provides:

- Excellent integration with Hibernate and relational databases
- Mature tooling and documentation
- Simplified development for CRUD-based applications
- Robust transaction management
- Strong ecosystem support
- Easier onboarding for contributors
- Long-term maintainability

Since CleanList is primarily a business management application with standard REST operations, a blocking architecture is sufficient to meet the expected workload while reducing implementation complexity.

---

## Consequences

### Positive

- Mature and stable technology stack
- Excellent integration with Hibernate and MySQL
- Simplified persistence using JPA repositories
- Robust transaction management
- Extensive documentation and community support
- Easier debugging and testing
- Lower learning curve for contributors
- Well suited for CRUD-oriented business applications
- Large ecosystem of compatible libraries

### Negative

- Uses a blocking request model
- Lower scalability under extremely high concurrency compared to reactive architectures
- Thread-per-request model consumes more resources under heavy load

---

## Decision Summary

CleanList adopts Spring Boot, Spring MVC, Spring Data JPA, Hibernate, JDBC, and MySQL as its backend technology stack. This combination provides a stable, maintainable, and production-ready foundation that aligns with the project's architecture, development goals, and expected workload.
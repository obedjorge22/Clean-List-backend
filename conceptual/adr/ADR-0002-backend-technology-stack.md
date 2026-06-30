# ADR 0003: Backend Technology Stack

## Status
Accepted

---

## Context

CleanList is a web-based cleaning schedule management platform designed to organize cleaning lists, members, groups, schedules, and task assignments. The backend must expose a RESTful API capable of handling concurrent requests, stateless authentication, and reliable communication with a relational database while remaining maintainable and scalable.

---

## Decision

The CleanList backend is implemented using the following technology stack:

- Spring Boot
- Spring WebFlux
- Spring Data R2DBC
- MySQL
- HTTP/JSON communication
- Maven for dependency management

---

## Consequences

### Positive

- Reactive, non-blocking architecture
- High throughput for concurrent requests
- Excellent scalability
- Strong Spring ecosystem and community support
- Native reactive integration with MySQL through R2DBC
- Easy development and maintenance of RESTful APIs
- Clear separation of concerns using a layered architecture

### Negative

- Higher learning curve compared to Spring MVC
- Reactive programming introduces additional complexity
- Some third-party libraries have limited reactive support

---

## Decision Summary

Spring Boot with WebFlux, Spring Data R2DBC, and MySQL is the standard backend technology stack for CleanList. This stack provides a modern, scalable, and maintainable foundation that meets the project's current requirements while allowing future growth and evolution.
# Reactive CRUD Implementation Guide for CleanList

This guide defines the standard pattern for implementing reactive CRUD operations in the **CleanList** backend. Every new entity should follow this architecture to keep the codebase consistent, maintainable, and scalable.

Replace **`[EntityName]`** with the desired entity (e.g. `Member`, `Group`, `CleaningList`, `Schedule`, `Assignment`).

---

# Project Structure

```
src/main/java/com/example/cleanlist
├── controller
├── dto
│   └── [entity]
├── model
├── repository
├── service
├── exception
└── config
```

---

# 1. Controller

**Path**

```
src/main/java/com/example/cleanlist/controller/[EntityName]Controller.java
```

## Purpose

Expose the REST API endpoints and delegate all business logic to the service layer.

## Responsibilities

- Receive HTTP requests
- Validate request bodies
- Return HTTP responses
- Never contain business logic

## Best Practices

- Annotate with `@RestController`
- Use `@RequestMapping`
- Use reactive types (`Mono` and `Flux`)
- Validate requests using `@Valid`
- Return `ResponseEntity`

Typical endpoints:

- GET /api/[entities]
- GET /api/[entities]/{id}
- POST /api/[entities]
- PUT /api/[entities]/{id}
- DELETE /api/[entities]/{id}
- PATCH /api/[entities]/{id}/restore
- DELETE /api/[entities]/{id}/hard

---

# 2. Service

**Path**

```
src/main/java/com/example/cleanlist/service/[EntityName]Service.java
```

## Purpose

Contains all business rules.

## Responsibilities

- Execute CRUD operations
- Validate business rules
- Convert DTOs
- Communicate with repositories

## Best Practices

- Annotate with `@Service`
- Return `Mono` or `Flux`
- Never expose database entities directly
- Keep controllers thin

---

# 3. Repository

**Path**

```
src/main/java/com/example/cleanlist/repository/[EntityName]Repository.java
```

## Purpose

Database access layer.

## Best Practices

Extend

```java
ReactiveCrudRepository<Entity, Long>
```

Example custom methods

```java
Flux<Entity> findAllByDeletedAtIsNull();

Flux<Entity> findAllByDeletedAtIsNotNull();

Mono<Boolean> existsByName(String name);
```

---

# 4. Entity

**Path**

```
src/main/java/com/example/cleanlist/model/[EntityName].java
```

## Purpose

Represents a database table.

## Best Practices

Use

- @Table
- @Id
- @Column

Include

- createdAt
- updatedAt
- deletedAt

Implement helper methods

```java
softDelete()

restore()
```

---

# 5. DTOs

**Path**

```
src/main/java/com/example/cleanlist/dto/[entity]/
```

## Purpose

Define the API contract.

Recommended DTOs

```
EntityRequest

EntityResponse
```

Use Java Records

Example

```java
public record MemberRequest(
    @NotBlank String firstName,
    @NotBlank String lastName,
    String phone,
    String email
){}
```

Never expose entities directly.

---

# 6. Database Migration

**Path**

```
src/main/resources/db/migration/
```

Example

```
V1__create_users.sql

V2__create_cleaning_lists.sql

V3__create_members.sql

V4__create_groups.sql
```

Each migration should contain

- Primary key
- Foreign keys
- Unique constraints
- Indexes
- Soft delete column

---

# 7. Configuration

**Path**

```
src/main/resources/application.properties
```

Contains

- MySQL connection
- R2DBC configuration
- JWT configuration
- Logging
- Flyway
- Spring properties

Never commit real passwords.

---

# 8. Exception Handling

**Path**

```
src/main/java/com/example/cleanlist/exception/
```

Contains

- EntityNotFoundException
- BusinessException
- ValidationException
- GlobalExceptionHandler

Use Problem Details (RFC 9457) whenever possible.

---

# CRUD Flow

## Create

Controller

↓

Service

↓

Repository

↓

Database

↓

Response DTO

---

## Read

Controller

↓

Service

↓

Repository

↓

Response DTO

---

## Update

Controller

↓

Service

↓

Repository

↓

Database

↓

Response DTO

---

## Soft Delete

Controller

↓

Service

↓

Repository

↓

Update deletedAt

---

## Restore

Controller

↓

Service

↓

Repository

↓

deletedAt = null

---

## Hard Delete

Controller

↓

Service

↓

Repository

↓

Delete record

---

# Standard API Pattern

Every entity should provide

```
GET     /api/[entities]

GET     /api/[entities]/trashed

GET     /api/[entities]/{id}

POST    /api/[entities]

PUT     /api/[entities]/{id}

DELETE  /api/[entities]/{id}

PATCH   /api/[entities]/{id}/restore

DELETE  /api/[entities]/{id}/hard
```

---

# CleanList Entities

The project currently contains the following entities.

```
User

Role

UserRole

Session

CleaningList

Member

Group

GroupMember

Schedule

Assignment

AssignmentHistory
```

Each entity must follow the same CRUD architecture.

---

# General Best Practices

- Keep controllers small.
- Place all business logic in services.
- Use DTOs for every request and response.
- Never expose entities directly.
- Use `Mono` and `Flux` throughout the application.
- Never use `.block()` or `.subscribe()` in production code.
- Implement soft delete whenever applicable.
- Keep entity-to-DTO conversion centralized.
- Validate requests using Bean Validation.
- Use constructor injection.
- Follow RESTful conventions.
- Document endpoints using OpenAPI/Swagger.

---

# Many-to-Many Relationships

If the join table only contains foreign keys:

Use a many-to-many relationship.

If the join table stores additional information:

Create a dedicated entity.

Example

```
GroupMember
```

because it stores

- joinedAt
- createdAt
- deletedAt

Therefore, it should have its own

- Entity
- Repository
- Service
- Controller
- DTOs

---

# Authentication

CleanList uses JWT authentication.

Authentication endpoints

```
POST /api/auth/login

POST /api/auth/refresh

POST /api/auth/logout
```

All protected endpoints require

```
Authorization: Bearer <token>
```

---

# Official Standard

This document defines the official implementation standard for every reactive CRUD in the CleanList backend.

Following these conventions ensures consistency, maintainability, scalability, and clean architecture throughout the project.
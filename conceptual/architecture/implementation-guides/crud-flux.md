# CRUD Implementation Guide for CleanList

This guide defines the standard pattern for implementing CRUD operations in the **CleanList** backend. Every new entity must follow this architecture to keep the codebase consistent, maintainable, and scalable.

Replace **`[EntityName]`** with the desired entity (e.g. `Member`, `Group`, `CleaningList`, `Schedule`, `Assignment`).

---

# Project Structure

```text
src/main/java/com/example/cleanlist
├── config
├── controller
├── dto
│   └── [entity]
├── entity
├── enums
├── exception
├── mapper
├── repository
├── security
├── service
│   ├── impl
└── util
```

---

# 1. Controller

**Path**

```text
src/main/java/com/example/cleanlist/controller/[EntityName]Controller.java
```

## Purpose

Expose REST endpoints and delegate all business logic to the service layer.

## Responsibilities

- Receive HTTP requests
- Validate request bodies
- Return HTTP responses
- Never contain business logic

## Best Practices

- Annotate with `@RestController`
- Use `@RequestMapping`
- Validate requests using `@Valid`
- Return `ResponseEntity`
- Keep controllers thin

Typical endpoints

```text
GET     /api/[entities]

GET     /api/[entities]/trash

GET     /api/[entities]/{id}

POST    /api/[entities]

PUT     /api/[entities]/{id}

DELETE  /api/[entities]/{id}

PATCH   /api/[entities]/{id}/restore

DELETE  /api/[entities]/{id}/permanent
```

---

# 2. Service

**Path**

```text
src/main/java/com/example/cleanlist/service/[EntityName]Service.java
```

## Purpose

Contains all business rules.

## Responsibilities

- Execute CRUD operations
- Validate business rules
- Communicate with repositories
- Convert entities to DTOs
- Manage transactions

## Best Practices

- Create a service interface
- Create a service implementation inside `service/impl`
- Annotate the implementation with `@Service`
- Use `@Transactional` where appropriate
- Never expose entities directly

---

# 3. Repository

**Path**

```text
src/main/java/com/example/cleanlist/repository/[EntityName]Repository.java
```

## Purpose

Database access layer.

## Best Practices

Extend

```java
JpaRepository<Entity, Long>
```

Example custom methods

```java
List<Entity> findAllByDeletedAtIsNull();

List<Entity> findAllByDeletedAtIsNotNull();

boolean existsByName(String name);
```

---

# 4. Entity

**Path**

```text
src/main/java/com/example/cleanlist/entity/[EntityName].java
```

## Purpose

Represents a database table.

## Best Practices

Use

- `@Entity`
- `@Table`
- `@Id`
- `@GeneratedValue`
- `@Column`

Use JPA relationships whenever appropriate

- `@ManyToOne`
- `@OneToMany`
- `@OneToOne`
- `@ManyToMany`

Include whenever applicable

- createdAt
- updatedAt
- deletedAt

Use JPA Auditing or lifecycle callbacks to maintain timestamps.

---

# 5. DTOs

**Path**

```text
src/main/java/com/example/cleanlist/dto/[entity]/
```

## Purpose

Define the API contract.

Recommended DTOs

```text
EntityRequest

EntityResponse
```

Use Java Records whenever possible.

Example

```java
public record MemberRequest(
    @NotBlank String firstName,
    @NotBlank String lastName,
    String phone,
    String email
) {}
```

Never expose entities directly.

---

# 6. Mapper

**Path**

```text
src/main/java/com/example/cleanlist/mapper/
```

## Purpose

Convert between Entities and DTOs.

## Best Practices

Use MapStruct.

Each entity should have its own mapper.

Example

```text
MemberMapper

GroupMapper

ScheduleMapper
```

---

# 7. Database Migration

**Path**

```text
src/main/resources/db/migration/
```

Example

```text
V1__create_users.sql

V2__create_sessions.sql

V3__create_cleaning_lists.sql

V4__create_cleaning_list_members.sql

V5__create_members.sql

V6__create_groups.sql

V7__create_group_members.sql

V8__create_schedules.sql

V9__create_no_cleaning_days.sql

V10__create_assignments.sql

V11__create_assignment_history.sql
```

Each migration should contain

- Primary key
- Foreign keys
- Unique constraints
- Indexes
- Soft delete column (when applicable)

---

# 8. Configuration

**Path**

```text
src/main/resources/application.properties
```

Contains

- MySQL JDBC configuration
- JPA / Hibernate configuration
- Flyway
- JWT
- Spring Security
- Logging

Never commit real passwords or secrets.

---

# 9. Exception Handling

**Path**

```text
src/main/java/com/example/cleanlist/exception/
```

Contains

- EntityNotFoundException
- BusinessException
- ValidationException
- GlobalExceptionHandler

Use Spring Problem Details (RFC 9457) whenever possible.

---

# CRUD Flow

## Create

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
Database
    ↓
Response DTO
```

---

## Read

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
Response DTO
```

---

## Update

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
Database
    ↓
Response DTO
```

---

## Soft Delete

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
Update deletedAt
```

---

## Restore

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
deletedAt = null
```

---

## Permanent Delete

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
Delete record
```

---

# Standard API Pattern

Every entity should provide

```text
GET     /api/[entities]

GET     /api/[entities]/trash

GET     /api/[entities]/{id}

POST    /api/[entities]

PUT     /api/[entities]/{id}

DELETE  /api/[entities]/{id}

PATCH   /api/[entities]/{id}/restore

DELETE  /api/[entities]/{id}/permanent
```

Business-specific endpoints may be added whenever necessary.

Examples

```text
PATCH /api/assignments/{id}/complete

GET /api/groups/{id}/members

GET /api/schedules/{id}/assignments
```

---

# CleanList Entities

The project currently contains the following entities.

```text
User

Session

CleaningList

CleaningListMember

Member

Group

GroupMember

Schedule

NoCleaningDay

Assignment

AssignmentHistory
```

Every entity should follow the same architecture.

---

# Authentication

CleanList uses JWT authentication.

Authentication endpoints

```text
POST /api/auth/login

POST /api/auth/refresh

POST /api/auth/logout

GET  /api/auth/google

GET  /api/auth/google/callback
```

All protected endpoints require

```text
Authorization: Bearer <token>
```

---

# General Best Practices

- Keep controllers small.
- Place all business logic inside services.
- Use DTOs for every request and response.
- Never expose JPA entities directly.
- Use constructor injection.
- Use Bean Validation.
- Prefer MapStruct for entity mapping.
- Use JPA relationships instead of manual joins whenever possible.
- Implement soft delete where applicable.
- Follow RESTful conventions.
- Document endpoints using OpenAPI/Swagger.
- Write unit and integration tests.

---

# Relationship Guidelines

## Many-to-Many without additional attributes

Use `@ManyToMany`.

Example

```text
User ↔ Permission
```

---

## Relationship with additional attributes

Create a dedicated entity.

Examples

```text
CleaningListMember

GroupMember
```

These entities contain their own business data and therefore require their own:

- Entity
- Repository
- Service
- Controller
- DTOs
- Mapper

---

# Official Standard

This document defines the official implementation standard for every CRUD operation in the CleanList backend.

Following these conventions ensures consistency, maintainability, scalability, and a clean architecture throughout the project.
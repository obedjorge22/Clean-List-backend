# Implementation Guide: Authentication and Authorization (JWT + RBAC + Google OAuth2)

**Status:** Proposed for implementation

This document defines the authentication and authorization architecture for **CleanList**, using **JWT**, **Role-Based Access Control (RBAC)**, and **Google OAuth2** authentication.

---

# 1. Overview

CleanList supports two authentication methods:

- **Traditional Login:** Users authenticate using email (or username) and password.
- **Google OAuth2 Login:** Users authenticate using their Google account.

After successful authentication, the backend issues a **JWT** that is used to access protected API endpoints.

Authorization is based on **RBAC**, where each user may have one or more roles.

---

# 2. Authentication Flow

## 2.1 Traditional Login

1. Client sends

```
POST /api/v1/auth/login
```

Request

```json
{
  "email": "john@example.com",
  "password": "password"
}
```

2. Backend

- Finds the user by email.
- Verifies the password using BCrypt.
- Checks whether the account is active.
- Loads user roles.
- Generates a JWT.

3. Response

```json
{
  "accessToken": "...",
  "tokenType": "Bearer",
  "expiresAt": "...",
  "userId": 1,
  "roles": [
    "ADMIN"
  ]
}
```

---

## 2.2 Google OAuth2 Login

### Step 1

The client opens

```
GET /api/v1/auth/google
```

The backend redirects the user to Google's authorization page.

---

### Step 2

The user authenticates using Google.

Google redirects to

```
GET /api/v1/auth/google/callback?code=...
```

---

### Step 3

The backend

- Exchanges the authorization code for an access token.
- Retrieves the user's profile.
- Finds the user by email.

If the user does not exist

- Creates a new User
- Assigns the default role (`MEMBER`)

If the user already exists

- Updates login information if necessary

Finally

- Generates the application's JWT.
- Returns the token to the frontend.

---

# 3. JWT

Each authenticated user receives a signed JWT.

Example claims

```json
{
    "sub": "15",
    "roles": [
        "ADMIN"
    ],
    "iat": 1720000000,
    "exp": 1720003600
}
```

JWT contains

- User ID
- User roles
- Issued date
- Expiration date

---

# 4. Authorization (RBAC)

CleanList uses **Role-Based Access Control**.

Example roles

- ADMIN
- COORDINATOR
- MEMBER

Each role defines what resources can be accessed.

Examples

| Role | Permissions |
|-------|-------------|
| ADMIN | Full system access |
| COORDINATOR | Manage members, groups, schedules and assignments |
| MEMBER | View assigned schedules and cleaning history |

Authorization may be implemented using

```java
@PreAuthorize("hasRole('ADMIN')")
```

or

```java
@PreAuthorize("hasAnyRole('ADMIN','COORDINATOR')")
```

---

# 5. Components

| Component | Responsibility |
|------------|----------------|
| JwtService | Generate and validate JWT tokens |
| AuthController | Login, Google OAuth2, Logout |
| AuthService | Authentication business logic |
| JwtAuthenticationFilter | Validate JWT on every request |
| SecurityConfig | Configure Spring Security |
| UserService | Load authenticated users |
| RoleService | Load user roles |

---

# 6. Entities

Authentication uses the following entities.

## User

Stores

- name
- email
- password
- active

---

## Role

Defines permissions.

Examples

- ADMIN
- COORDINATOR
- MEMBER

---

## UserRole

Many-to-many relationship between users and roles.

---

## Session

Stores

- JWT token
- expiration
- IP address
- last access

This table allows session auditing.

---

# 7. Spring Security Configuration

Public endpoints

```
POST /api/v1/auth/login

GET /api/v1/auth/google

GET /api/v1/auth/google/callback

GET /swagger-ui/**

GET /v3/api-docs/**
```

Protected endpoints

```
/api/v1/**
```

Every protected request must contain

```
Authorization: Bearer <token>
```

---

# 8. Configuration

## JWT

```
jwt.secret=

jwt.expiration-ms=3600000
```

---

## Google OAuth2

```
google.client-id=

google.client-secret=

google.redirect-uri=http://localhost:8080/api/v1/auth/google/callback
```

---

# 9. Error Responses

## 401 Unauthorized

Returned when

- Token is missing
- Token is invalid
- Token is expired

Example

```json
{
    "status":401,
    "title":"Unauthorized",
    "detail":"Invalid or expired token."
}
```

---

## 403 Forbidden

Returned when

- User is authenticated
- User does not have the required role

Example

```json
{
    "status":403,
    "title":"Forbidden",
    "detail":"Insufficient permissions."
}
```

---

# 10. Logout

Endpoint

```
POST /api/v1/auth/logout
```

The backend

- Invalidates the current session (optional)
- Removes the JWT from the Session table
- Client deletes the stored token

---

# 11. Testing

Unit Tests

- JwtService
- AuthService
- Password encryption
- Role loading

Integration Tests

- Traditional login
- Google OAuth2 callback
- JWT validation
- Protected endpoints
- RBAC authorization

Test scenarios

- Invalid password
- Expired token
- Invalid token
- Missing token
- User without permission
- Successful Google login
- Successful traditional login

---

# 12. Authentication Flow

```text
User
    │
    │ Login
    ▼
AuthController
    │
    ▼
AuthService
    │
    ├── Validate credentials
    ├── Load roles
    ├── Generate JWT
    ▼
Response

──────────────

Authenticated Request

Client
    │
Authorization: Bearer JWT
    ▼
JWT Filter
    │
Validate Token
    ▼
Security Context
    ▼
Controller
    ▼
Service
    ▼
Repository
```

---

# Official Standard

This document defines the official authentication and authorization architecture for **CleanList**.

The system uses:

- Spring Security
- JWT Authentication
- Role-Based Access Control (RBAC)
- Google OAuth2 Login
- BCrypt password hashing

This architecture provides a secure, scalable, and stateless authentication mechanism suitable for modern reactive web applications.
# ADR 0002: Authentication with JWT

## Status
Accepted

---

## Context

CleanList requires a secure and stateless authentication mechanism for its REST API. The application must allow authenticated users to access and manage cleaning lists, members, groups, schedules, and assignments without relying on server-side sessions. The solution should be scalable, secure, and suitable for modern web applications.

---

## Decision

Authentication in CleanList is implemented using **JWT (JSON Web Tokens)**.

The authentication flow follows these principles:

- Users authenticate with their credentials.
- The backend generates and signs a JWT after successful authentication.
- The client stores the token and includes it in the `Authorization: Bearer <token>` header for every protected request.
- Every request is authenticated by validating the JWT.
- No server-side session state is maintained.

---

## Consequences

### Positive

- Stateless authentication
- Excellent scalability
- Reduced server memory usage
- Easy integration with web and mobile clients
- Well suited for RESTful APIs
- Simplifies horizontal scaling of the backend

### Negative

- Token revocation requires additional mechanisms (such as blacklists or short-lived access tokens with refresh tokens)
- Expired tokens require renewal
- Care must be taken to securely store tokens on the client

---

## Decision Summary

JWT is the standard authentication mechanism for CleanList. It provides a secure, stateless, and scalable solution that aligns with the project's RESTful architecture and supports future growth without requiring server-side session management.
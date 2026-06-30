# System Flow

## Overview

The following diagram illustrates the high-level architecture and request flow of the CleanList application.

```mermaid
flowchart LR

    U[User / Frontend]

    subgraph Backend["Backend (Spring Boot + WebFlux)"]
        AUTH[JWT Authentication]
        API[REST API]
        SERVICE[Business Services]
    end

    DB[(MySQL)]

    subgraph Scheduler["Scheduling Engine"]
        ROTATION[Rotation Algorithm]
        HISTORY[Assignment History]
    end

    CACHE[(Redis Cache)]

    %% Authentication
    U -->|Login| AUTH
    AUTH -->|JWT Token| U

    %% Requests
    U -->|Authenticated Request| API

    %% Business Flow
    API --> SERVICE

    SERVICE -->|Manage Cleaning Lists| DB
    SERVICE -->|Manage Members| DB
    SERVICE -->|Manage Groups| DB
    SERVICE -->|Manage Schedules| DB

    %% Schedule Generation
    SERVICE -->|Generate Cleaning Schedule| ROTATION
    ROTATION --> DB
    ROTATION --> HISTORY
    HISTORY --> DB

    %% Read Operations
    DB --> SERVICE
    SERVICE --> API
    API --> U

    %% Cache
    SERVICE --> CACHE
    CACHE --> SERVICE
```

## Components

- **Frontend**: Web or mobile client used by administrators and members.
- **JWT Authentication**: Handles user authentication and authorization.
- **REST API**: Exposes application endpoints.
- **Business Services**: Contains the application's business logic.
- **Scheduling Engine**: Generates fair cleaning assignments using the rotation algorithm.
- **MySQL**: Stores application data.
- **Redis**: Improves performance by caching frequently accessed data.
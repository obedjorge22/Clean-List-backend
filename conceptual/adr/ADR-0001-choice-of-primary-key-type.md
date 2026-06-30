# ADR 0001: Choice of Primary Key Type for CleanList Core Entities

## Status
Pending

---

## Context

CleanList is a cleaning schedule management platform built with a Java Spring Boot backend and a PostgreSQL database. The system manages entities such as `CleaningList`, `Member`, `Group`, `Schedule`, `Task`, and `Assignment`, which are interconnected through foreign key relationships.

Choosing the primary key (PK) type is an important architectural decision because it directly affects database performance, indexing efficiency, query speed, referential integrity, and future scalability. Several alternatives were evaluated, including sequential integers (`BIGINT`), globally unique identifiers (`UUID`), lexicographically sortable identifiers (`ULID`), and arbitrary strings.

---

## Decision

After evaluating the available options, CleanList will adopt **`BIGINT AUTO_INCREMENT`** as the primary key type for all core entities.

This choice provides:

- Excellent indexing performance
- Fast joins between related tables
- Native compatibility with Java (`Long`)
- Simpler implementation and maintenance
- Compact storage (8 bytes)
- Readable and predictable identifiers for debugging and testing

Although UUID and ULID provide globally unique identifiers, CleanList currently operates as a centralized application where global uniqueness is unnecessary. Therefore, the simplicity and performance of `BIGINT` outweigh the benefits of the alternatives.

---

## Why not UUID

UUIDs provide globally unique identifiers, making them valuable for distributed systems. However, they introduce several disadvantages for CleanList:

- Larger indexes and increased storage usage
- Slower joins and lookups compared to `BIGINT`
- UUID v4 generates random values, which may cause index fragmentation in PostgreSQL
- Long identifiers reduce readability during debugging and database inspection
- Global uniqueness is unnecessary for the current architecture

---

## Why not ULID

ULID offers global uniqueness while preserving lexicographical ordering.

However:

- It requires additional libraries for ID generation
- It occupies more storage than `BIGINT`
- It produces longer identifiers that are harder to read
- It increases implementation complexity without providing significant benefits for the current system

Since CleanList is not currently a distributed platform, ULID is considered unnecessary.

---

## Why not Strings

Using strings as primary keys was rejected because:

- They consume significantly more storage
- Indexes become larger and slower
- Joins are less efficient
- They require additional validation to guarantee consistency
- They complicate database optimization

While strings may improve readability in some situations, their disadvantages outweigh their benefits for CleanList.

---

## PK Type Comparison

| Type | Example | Sortable | Size | Notes |
|------|---------|----------|------|------|
| BIGINT | `1`, `2`, `3` | Yes | 8 bytes | Sequential, compact, fast, native in Java/SQL |
| UUID v4 | `550e8400-e29b-41d4-a716-446655440000` | No | 16 bytes / 36 chars | Random, globally unique, not sortable |
| UUID v1 | `6ba7b810-9dad-11d1-80b4-00c04fd430c8` | Yes | 16 bytes / 36 chars | Sortable, contains timestamp and hardware information |
| ULID | `01ARZ3NDEKTSV4RRFFQ69G5FAV` | Yes | 16 bytes / 26 chars | Sortable, globally unique, requires external library |

---

## Consequences

All core CleanList entities will use `BIGINT` mapped to Java's `Long` type.

This includes entities such as:

- `CleaningList`
- `Member`
- `Group`
- `Schedule`
- `Task`
- `Assignment`

All foreign keys will also use `BIGINT`, ensuring efficient joins and strong referential integrity across the database.

This decision keeps the system simple, performant, and maintainable while preserving the possibility of migrating to UUID or ULID in the future if CleanList evolves into a distributed or multi-region platform.
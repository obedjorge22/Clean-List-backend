## ERM

**users** (id, firstName, lastName, email, passwordHash, phone, active, createdAt, updatedAt, deletedAt); </br>

**roles** (id, name, description, createdAt, updatedAt, deletedAt); </br>

**userRoles** (userId, roleId, createdAt, deletedAt); </br>

**sessions** (id, userId, token, ipAddress, expiresAt, lastUsed, createdAt, updatedAt, deletedAt); </br>

**cleaningLists** (id, name, description, createdBy, active, createdAt, updatedAt, deletedAt); </br>

**members** (id, cleaningListId, firstName, lastName, phone, email, active, createdAt, updatedAt, deletedAt); </br>

**groups** (id, cleaningListId, name, displayOrder, active, createdAt, updatedAt, deletedAt); </br>

**groupMembers** (groupId, memberId, joinedAt, createdAt, deletedAt); </br>

**schedules** (id, cleaningListId, scheduledDate, status, notes, createdBy, createdAt, updatedAt, deletedAt); </br>

**assignments** (id, scheduleId, groupId, assignedAt, completedAt, status, observations, createdAt, updatedAt, deletedAt); </br>

**assignmentHistory** (id, assignmentId, previousStatus, newStatus, changedBy, changedAt, notes, createdAt); </br>
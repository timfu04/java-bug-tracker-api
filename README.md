# Java Bug Tracker API

## 1. Description
- Java Bug Tracker API is a Java backend project that utilizes REST API to manage a range of entities. This project consists of a total of 8 entities, namely role, user, project, issue, status, severity, priority, and issue comment. It is built using the Spring Boot framework, adhering to the MVC design pattern for effective separation of concerns. It also incorporates custom exceptions with a global exception handler to ensure robust error handling. Besides that, it features custom annotations and validators, including date format validator, date range validator, and uppercase validator to enhance the data integrity and validation capabilities of the application.

## 2. Database Business Rules
1. A role has more than one user, a user belongs to only one role (roles to users, 1 to M)
2. A user (admin or manager) can create more than one project, a project can be created by only one user (users to projects, 1 to M)
3. A user can involve in more than one project, a project can be involved by more than one user (users to projects, M to M)
4. A project has more than one issue, an issue belongs to only one project (projects to issues, 1 to M)
5. A status can be in more than one issue, an issue can only have one status (statuses to issues, 1 to M)
6. A severity can be in more than one issue, an issue can only have one severity (severities to issues, 1 to M)
7. A priority can be in more than one issue, an issue can only have one priority (priorities to issues, 1 to M)
8. A user can report more than one issue, an issue can only be reported by one user (users to issues, 1 to M)
9. A user can be assigned to more than one issue, an issue can assign to more than one user (users to issues, M to M)
10. An issue has more than one issue comment, an issue comment belongs to only one issue (issues to issue comments, 1 to M)
11. A user can create more than one issue comment, an issue comment can only be created by one user (users to issue comments, 1 to M)

## 3. Entity Relationship Diagram (ERD)
<div align="center">

![java-bug-tracker-api-Page-2 drawio](https://github.com/timfu04/sneaker-monitors/assets/70854339/bf2b4cb0-8c6a-4dba-abfd-b637f74a50ef)

*Entity Relationship Diagram (ERD)*

</div>

## 4. Role
### 4.1 CRUD operations
1. Create role
   - `POST` request
   - API endpoint: `/api/v1/role/create` 
2. Get all roles
   - `GET` request
   - API endpoint: `/api/v1/role`
3. Get role by role id
   - `GET` request
   - API endpoint: `/api/v1/role/{roleId}`
4. Update role by role id
   - `PUT` request
   - API endpoint: `/api/v1/role/{roleId}/update`
5. Delete role by role id
   - `DELETE` request
   - API endpoint: `/api/v1/role/{roleId}/delete`

### 4.2 Expected roles
1. ADMIN
2. MANAGER
3. DEVELOPER
4. TESTER
5. MEMBER

### 4.3 Database constraints used
1. `UNIQUE`
2. `NOT NULL`

### 4.4 Validators used
1. `@NotBlank`

### 4.5 Custom exceptions used
1. `RoleNotFoundException`

### 4.6 Custom annotations used
1. `@ValidateBlankAndUppercase`

### 4.7 Custom validators used
1. `BlankAndUppercaseValidator`



## 5. User
### 5.1 CRUD operations
1. Create user
   - `POST` request
   - API endpoint: `/api/v1/user/create`
2. Get all users
   - `GET` request
   - API endpoint: `/api/v1/user`
3. Get user by user id
   - `GET` request
   - API endpoint: `/api/v1/user/{userId}`
4. Update user partial by user id
   - `PATCH` request
   - API endpoint: `/api/v1/user/{userId}/update-partial`
5. Delete user by user id
   - `DELETE` request
   - API endpoint: `/api/v1/user/{userId}/delete`
6. Update role by user id by role name
   - `PATCH` request
   - API endpoint: `/api/v1/user/{userId}/update-role`
7. Revoke role by user id
   - `PUT` request
   - API endpoint: `/api/v1/user/{userId}/revoke-role`

### 5.2 Database constraints used
1. `UNIQUE`
2. `NOT NULL`

### 5.3 Validators used
1. `@NotBlank`
2. `@Email`

### 5.4 Custom exceptions used
1. `UserNotFoundException`
2. `RoleNotFoundException`



## 6. Project
### 6.1 CRUD operations
1. Create project by user id
   - `POST` request
   - API endpoint: `/api/v1/user/{userId}/project/create`
2. Get all projects created by user id
   - `GET` request
   - API endpoint: `/api/v1/user/{userId}/project`
3. Get project created by user id by project id
   - `GET` request
   - API endpoint: `/api/v1/user/{userId}/project/{projectId}`
4. Update project partial by user id by project id
   - `PATCH` request
   - API endpoint: `/api/v1/user/{userId}/project/{projectId}/update-partial`
5. Delete project by user id by project id
   - `DELETE` request
   - API endpoint: `/api/v1/user/{userId}/project/{projectId}/delete`
6. Add user into project by user creator id by project id by user id
   - `PUT` request
   - API endpoint: `/api/v1/user/{userCreatorId}/project/{projectId}/add-user/user/{userIdToAdd}`
7. Remove user from project by user creator id by project id by user id
   - `DELETE` request
   - API endpoint: `/api/v1/user/{userCreatorId}/project/{projectId}/remove-user/user/{userIdToRemove}`
8. Get all projects
   - `GET` request
   - API endpoint: `/api/v1/project`
9. Get project by project id
   - `GET` request
   - API endpoint: `/api/v1/project/{projectId}`
10. Update project partial by project id
    - `PATCH` request
    - API endpoint: `/api/v1/project/{projectId}/update-partial`
12. Delete project by project id
    - `DELETE` request
    - API endpoint: `/api/v1/project/{projectId}/delete`
12. Add user into project by project id by user id
    - `PUT` request
    - API endpoint: `/api/v1/project/{projectId}/add-user/user/{userId}`
13. Remove user from project by project id by user id
    - `DELETE` request
    - API endpoint: `/api/v1/project/{projectId}/remove-user/user/{userId}`

### 6.2 Database constraints used
1. `UNIQUE`
2. `NOT NULL`

### 6.3 Validators used
1. `@NotBlank`

### 6.4 Custom exceptions used
1. `ProjectNotFoundException`
2. `ProjectNotCreatedByThisUserException`
3. `UserNotFoundException`
4. `UserNotInProjectException`
4. `DuplicateUserInProjectException`

### 6.5 Custom annotations used
1. `@ValidateProjectDate`

### 6.6 Custom validators used
1. `ProjectDateValidator`



## 7. Issue
### 7.1 CRUD operations
1. Create issue by user id by project id
   - `POST` request
   - API endpoint: `/api/v1/user/{userId}/project/{projectId}/issue/create`
2. Get all issues by user id by project id
   - `GET` request
   - API endpoint: `/api/v1/user/{userId}/project/{projectId}/issue`
3. Get all issues assigned by user id
    - `GET` request
    - API endpoint: `/api/v1/user/{userId}/issue`
4. Get issue by user id by project id by issue id
    - `GET` request
    - API endpoint: `/api/v1/user/{userId}/project/{projectId}/issue/{issueId}`
5. Update issue partial by user id by project id by issue id
    - `PATCH` request
    - API endpoint: `/api/v1/user/{userId}/project/{projectId}/issue/{issueId}/update-partial`
6. Delete issue by user id by project id by issue id
    - `DELETE` request
    - API endpoint: `/api/v1/user/{userId}/project/{projectId}/issue/{issueId}/delete`
7. Update updated date by user id by project id by issue id
    - `PATCH` request
    - API endpoint: `/api/v1/user/{userId}/project/{projectId}/issue/{issueId}/update-updated-date`
8. Update resolved date by user id by project id by issue id
    - `PATCH` request
    - API endpoint: `/api/v1/user/{userId}/project/{projectId}/issue/{issueId}/update-resolved-date`
9. Update closed date by user id by project id by issue id
    - `PATCH` request
    - API endpoint: `/api/v1/user/{userId}/project/{projectId}/issue/{issueId}/update-closed-date`
10. Update status by user id by project id by issue id by status name
    - `PATCH` request
    - API endpoint: `/api/v1/user/{userId}/project/{projectId}/issue/{issueId}/update-status`
11. Update severity by user id by project id by issue id by status name
    - `PATCH` request
    - API endpoint: `/api/v1/user/{userId}/project/{projectId}/issue/{issueId}/update-severity`
12. Update priority by user id by project id by issue id by status name
    - `PATCH` request
    - API endpoint: `/api/v1/user/{userId}/project/{projectId}/issue/{issueId}/update-priority`
13. Get all issues
    - `GET` request
    - API endpoint: `/api/v1/issue`
14. Get issue by issue id
    - `GET` request
    - API endpoint: `/api/v1/issue/{issueId}`
15. Update issue partial by issue id
    - `PATCH` request
    - API endpoint: `/api/v1/issue/{issueId}/update-partial`
16. Delete issue by issue id
    - `DELETE` request
    - API endpoint: `/api/v1/issue/{issueId}/delete`
17. Update updated date by issue id
    - `PATCH` request
    - API endpoint: `/api/v1/issue/{issueId}/update-updated-date`
18. Update resolved date by issue id
    - `PATCH` request
    - API endpoint: `/api/v1/issue/{issueId}/update-resolved-date`
19. Update closed date by issue id
    - `PATCH` request
    - API endpoint: `/api/v1/issue/{issueId}/update-closed-date`
20. Update status by issue id
    - `PATCH` request
    - API endpoint: `/api/v1/issue/{issueId}/update-status`
21. Update severity by issue id
    - `PATCH` request
    - API endpoint: `/api/v1/issue/{issueId}/update-severity`
22. Update priority by issue id
    - `PATCH` request
    - API endpoint: `/api/v1/issue/{issueId}/update-priority`
23. Assign user to issue by project id by issue id by user to add id
    - `PUT` request
    - API endpoint: `/api/v1/project/{projectId}/issue/{issueId}/add-user/user/{userToAddId}`
24. Remove user from issue by project id by issue id by user to remove id
    - `DELETE` request
    - API endpoint: `/api/v1/project/{projectId}/issue/{issueId}/remove-user/user/{userToRemoveId}`

### 7.3 Database constraints used
1. `UNIQUE`
2. `NOT NULL`

### 7.4 Validators used
1. `@NotBlank`

### 7.5 Custom exceptions used
1. UserNotFoundException
2. ProjectNotFoundException
3. StatusNotFoundException
4. SeverityNotFoundException
5. PriorityNotFoundException
6. IssueNotFoundException
7. UserNotInProjectException
8. UserNotInIssueException
9. DuplicateUserInIssueException
10. ProjectNotCreatedByThisUserException
11. IssueNotAssignedToUserException
12. IssueNotInThisProjectException

### 7.6 Custom annotations used
1. `@ValidateBlankAndUppercase`

### 7.7 Custom validators used
1. `BlankAndUppercaseValidator`



## 8. Status
### 8.1 CRUD operations
1. Create status
   - `POST` request
   - API endpoint: `/api/v1/status/create`
2. Get all statuses
   - `GET` request
   - API endpoint: `/api/v1/status`
3. Get status by status id
   - `GET` request
   - API endpoint: `/api/v1/status/{statusId}`
4. Update status by status id
   - `PUT` request
   - API endpoint: `/api/v1/status/{statusId}/update`
5. Delete status by status id
   - `DELETE` request
   - API endpoint: `/api/v1/status/{statusId}/delete`

### 8.2 Expected statuses
1. OPEN
2. ASSIGNED
3. IN PROGRESS
4. RESOLVED
5. CLOSED
6. REOPENED
7. DEFERRED
8. DUPLICATE
9. CANNOT REPRODUCE
10. NOT A BUG

### 8.3 Database constraints used
1. `UNIQUE`
2. `NOT NULL`

### 8.4 Validators used
1. `@NotBlank`

### 8.5 Custom exceptions used
1. `StatusNotFoundException`

### 8.6 Custom annotations used
1. `@ValidateBlankAndUppercase`

### 8.7 Custom validators used
1. `@BlankAndUppercaseValidator`



## 9. Severity
### 9.1 CRUD operations
1. Create severity
   - `POST` request
   - API endpoint: `/api/v1/severity/create`
2. Get all severities
   - `GET` request
   - API endpoint: `/api/v1/severity`
3. Get severity by severity id
   - `GET` request
   - API endpoint: `/api/v1/severity/{severityId}`
4. Update severity by severity id
   - `PUT` request
   - API endpoint: `/api/v1/severity/{severityId}/update`
5. Delete severity by severity id
   - `DELETE` request
   - API endpoint: `/api/v1/severity/{severityId}/delete`

### 9.2 Expected severities
1. CRITICAL
2. HIGH
3. MEDIUM
4. LOW

### 9.3 Database constraints used
1. `UNIQUE`
2. `NOT NULL`

### 9.4 Validators used
1. `@NotBlank`

### 9.5 Custom exceptions used
1. `SeverityNotFoundException`

### 9.6 Custom annotations used
1. `@ValidateBlankAndUppercase`

### 9.7 Custom validators used
1. `@BlankAndUppercaseValidator`



## 10. Priority
### 10.1 CRUD operations
1. Create priority
   - `POST` request
   - API endpoint: `/api/v1/priority/create`
2. Get all priorities
   - `GET` request
   - API endpoint: `/api/v1/priority`
3. Get priority by priority id
   - `GET` request
   - API endpoint: `/api/v1/priority/{priorityId}`
4. Update priority by priority id
   - `PUT` request
   - API endpoint: `/api/v1/priority/{priorityId}/update`
5. Delete priority by priority id
   - `DELETE` request
   - API endpoint: `/api/v1/priority/{priorityId}/delete`

### 10.2 Expected severities
1. HIGHEST
2. HIGH
3. MEDIUM
4. LOW
5. LOWEST

### 10.3 Database constraints used
1. `UNIQUE`
2. `NOT NULL`

### 10.4 Validators used
1. `@NotBlank`

### 10.5 Custom exceptions used
1. `PriorityNotFoundException`

### 10.6 Custom annotations used
1. `@ValidateBlankAndUppercase`

### 10.7 Custom validators used
1. `@BlankAndUppercaseValidator`



## 11. Issue Comment
### 11.1 CRUD operations
1. Create issue comment by user id by project id by issue id
    - `POST` request
    - API endpoint: `/api/v1/user/{userId}/project/{projectId}/issue/{issueId}/issue-comment/create`
2. Get all issue comments by user id by project id by issue id
    - `GET` request
    - API endpoint: `/api/v1/user/{userId}/project/{projectId}/issue/{issueId}/issue-comment`
3. Get all issue comments by user id
    - `GET` request
    - API endpoint: `/api/v1/user/{userId}/issue-comment`
4. Get issue comment by user id by project id by issue id by issue comment id
    - `GET` request
    - API endpoint: `/api/v1/user/{userId}/project/{projectId}/issue/{issueId}/issue-comment/{issueCommentId}`
5. Update issue comment by user id by project id by issue id by issue comment id
    - `PATCH` request
    - API endpoint: `/api/v1/user/{userId}/project/{projectId}/issue/{issueId}/issue-comment/{issueCommentId}/update`
6. Delete issue comment by user id by project id by issue id by issue comment id
    - `DELETE` request
    - API endpoint: `/api/v1/user/{userId}/project/{projectId}/issue/{issueId}/issue-comment/{issueCommentId}/delete`
7. Update updated date by user id by project id by issue id by issue comment id
    - `PATCH` request
    - API endpoint: `/api/v1/user/{userId}/project/{projectId}/issue/{issueId}/issue-comment/{issueCommentId}/update-updated-date`
8. Get all issue comments
    - `GET` request
    - API endpoint: `/api/v1/issue-comment`
9. Get issue comment by issue comment id
    - `GET` request
    - API endpoint: `/api/v1/issue-comment/{issueCommentId}`
10. Update issue comment by issue comment id
    - `PATCH` request
    - API endpoint: `/api/v1/issue-comment/{issueCommentId}/update`
11. Delete issue comment by issue comment id
    - `DELETE` request
    - API endpoint: `/api/v1/issue-comment/{issueCommentId}/delete`
12. Update updated date by issue comment id
    - `PATCH` request
    - API endpoint: `/api/v1/issue-comment/{issueCommentId}/update-updated-date`

### 10.2 Database constraints used
1. `NOT NULL`

### 10.3 Validators used
1. `@NotBlank`

### 10.4 Custom exceptions used
1. `UserNotFoundException`
2. `ProjectNotFoundException`
3. `IssueNotFoundException`
4. `IssueCommentNotFoundException`
5. `IssueNotAssignedToUserException`
6. `IssueNotInThisProjectException`
7. `UserNotInProjectException`
8. `IssueCommentNotCreatedByThisUserException` 
9. `IssueCommentNotInThisIssueException`

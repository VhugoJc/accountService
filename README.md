# Account Service

This project is a RESTful Java Spring Boot application that implements an Account Service. It utilizes H2 for the in-memory database, Spring Security for authentication and authorization, and Hibernate for object-relational mapping.

## Requirements

- Java Development Kit (JDK) 8 or higher
- Maven

## Installation

1. Clone the repository:

```bash
git clone https://github.com/VhugoJc/accountService.git
```
2. Navigate to the project directory:
```bash
cd accountService
```
3. Run the application:
```bash
mvn spring-boot:run
```
## Configuration
- `spring.datasource.url`: The JDBC URL for the H2 database
- `spring.datasource.username`: The username for the H2 database
- `spring.datasource.password`: The password for the H2 database
- `spring.jpa.hibernate.ddl-auto`: The strategy for database schema initialization 
- `spring.security.user.name`: The username for the default user (default: user)
- `spring.security.user.password`: The password for the default user

Modify the `application.properties` file in the `src/main/resources` directory to change these properties.

## Security

This project implements a robust security system with multiple roles to control access to various endpoints. The following is a description of the security roles for each endpoint:

| Endpoint                   | Anonymous | User | Accountant | Administrator | Auditor |
|----------------------------|-----------|------|------------|---------------|---------|
| `POST api/auth/signup`       |     +     |  +   |     +      |       +       |    +    |
| `POST api/auth/changepass`   |           |  +   |     +      |       +       |    -    |
| `GET api/empl/payment`       |     -     |  +   |     +      |       -       |    -    |
| `POST api/acct/payments`     |     -     |  -   |     +      |       -       |    -    |
| `PUT api/acct/payments`      |     -     |  -   |     +      |       -       |    -    |
| `GET api/admin/user`         |     -     |  -   |     -      |       +       |    -    |
| `DELETE api/admin/user`      |     -     |  -   |     -      |       +       |    -    |
| `PUT api/admin/user/role`    |     -     |  -   |     -      |       +       |    -    |
| `PUT api/admin/user/access`  |     -     |  -   |     -      |       +       |    -    |
| `GET api/security/events`    |     -     |  -   |     -      |       -       |    +    |


1. `POST api/auth/signup`
   - Roles: Anonymous, User, Accountant, Administrator, Auditor
   - Description: All users, regardless of their role, can access this endpoint to sign up and create a new account.

2. `POST api/auth/changepass`
   - Roles: User, Accountant, Administrator
   - Description: Only authenticated users with the roles User, Accountant, or Administrator can access this endpoint to change their password.

3. `GET api/empl/payment`
   - Roles: User, Accountant
   - Description: Only authenticated users with the roles User or Accountant can access this endpoint to retrieve payment information for employees.

4. `POST api/acct/payments`
   - Roles: Accountant
   - Description: Only authenticated users with the role Accountant can access this endpoint to create new payment records.

5. `PUT api/acct/payments`
   - Roles: Accountant
   - Description: Only authenticated users with the role Accountant can access this endpoint to update existing payment records.

6. `GET api/admin/user`
   - Roles: Administrator
   - Description: Only authenticated users with the role Administrator can access this endpoint to retrieve user information for administrative purposes.

7. `DELETE api/admin/user`
   - Roles: Administrator
   - Description: Only authenticated users with the role Administrator can access this endpoint to delete user accounts.

8. `PUT api/admin/user/role`
   - Roles: Administrator
   - Description: Only authenticated users with the role Administrator can access this endpoint to modify the roles of other users.

9. `PUT api/admin/user/access`
   - Roles: Administrator
   - Description: Only authenticated users with the role Administrator can access this endpoint to modify the access rights of other users.

10. `GET api/security/events`
    - Roles: Auditor
    - Description: Only authenticated users with the role Auditor can access this endpoint to retrieve security event logs and audit information.

Please note that authentication is required for all endpoints except for the `POST api/auth/signup` endpoint, which allows anonymous users to create an account. Additionally, access to each endpoint is restricted based on the specified roles. Users without the appropriate role will be denied access to the corresponding endpoint.

## API Endpoints
The following API endpoints are available:
`POST /api/auth/signup` - Create a new user account
Sample Request Body:
```json
{
   "name": "Admin",
   "lastname": "Admin",
   "email": "admin@acme.com",
   "password": "secret",
   "roles":["ROLE_ADMINISTRATOR"]
}
```
Response body:
```json
{
    "id": 1,
    "name": "Admin",
    "lastname": "Admin",
    "email": "admin@acme.com",
    "roles": [
        "ROLE_ADMINISTRATOR"
    ]
}
```

`POST /api/auth/changepass` - Change user password
Sample Request Body:
```json
{
    "new_password":"bZPGqH7fTJWW"
}
```
Response body:
```json
{
    "email": "johndoe2@acme.com",
    "status": "The password has been updated successfully"
}
```
`GET /api/empl/payment` - Retrieve payment information for employees

Response body:
```json
[
    {
        "name": "Victor",
        "lastname": "Jimenez",
        "period": "November-2026",
        "salary": "1234 dollar(s) 56 cent(s)"
    },
    {
        "name": "Victor",
        "lastname": "Jimenez",
        "period": "November-2027",
        "salary": "1234 dollar(s) 56 cent(s)"
    },
    {
        "name": "Victor",
        "lastname": "Jimenez",
        "period": "November-2023",
        "salary": "1234 dollar(s) 56 cent(s)"
    }
]
```
`POST /api/acct/payments` - Create a new payment record
Sample Request Body:
```JSON
[
    {
        "employee": "johndoe@acme.com",
        "period": "11-2026",
        "salary": 123456
    },
    {
        "employee": "johndoe@acme.com",
        "period": "11-2027",
        "salary": 123456
    },
    {
        "employee": "johndoe@acme.com",
        "period": "11-2023",
        "salary": 123456
    }
]
```
Response body:
```JSON
{
    "status": "Updated successfully!"
}
```

`PUT /api/acct/payments` - Update an existing payment record
Sample Request Body:
```JSON
{
    "employee": "johndoe@acme.com",
    "period": "01-2026",
    "salary": 2000
}
```
Response body:
```JSON
{
    "status": "Added successfully!"
}
```

`GET /api/admin/user` - Retrieve user information for administration

Response body:
```JSON
[
    {
        "id": 1,
        "name": "Victor",
        "lastname": "Jimenez",
        "email": "johndoe2@acme.com",
        "roles": [
            "ROLE_ADMINISTRATOR"
        ]
    },
    {
        "id": 2,
        "name": "Victor",
        "lastname": "Jimenez",
        "email": "johndoe@acme.com",
        "roles": [
            "ROLE_ADMINISTRATOR"
        ]
    }
]
```

`DELETE /api/admin/user/{user email}` - Delete a user account
Response body:
```JSON
{
    "user": "johndoe2@acme.com",
    "status": "Deleted successfully!"
}
```
`PUT /api/admin/user/role - Modify user roles`
Sample Request Body:
```JSON
{
   "user": "johndoe2@acme.com",
   "role": "ROLE_ADMINISTRATOR",
   "operation": "REMOVE"
}
```
Response body:
```JSON
{
    "id": 1,
    "name": "Victor",
    "lastname": "Jimenez",
    "email": "johndoe2@acme.com",
    "roles": ["ROLE_USER"]
}
```
## Logs Endpoint

**Description**: This endpoint provides access to the system logs, which record various events and actions within the application.

- **URL**: `/api/security/events`
- **Method**: GET
- **Role**: Auditor

### Request

No parameters are required for this endpoint.

### Response

The response will contain an array of log entries, each containing the following information:

| Field        | Type   | Description                                          |
|--------------|--------|------------------------------------------------------|
| date         | string | The timestamp when the event occurred.               |
| action       | string | The event name.                                      |
| subject      | string | The user who performed the action.                   |
| object       | string | The object on which the action was performed.        |
| path         | string | The api path                                         |
### Example

**Request**:

```JSON
GET /api/security/events HTTP/1.1
[
{
  "id" : 1,
  "date" : "<date>",
  "action" : "CREATE_USER",
  "subject" : "Anonymous", \\ A User is not defined, fill with Anonymous
  "object" : "johndoe@acme.com",
  "path" : "/api/auth/signup"
}, {
  "id" : 6,
  "date" : "<date>",
  "action" : "LOGIN_FAILED",
  "subject" : "maxmustermann@acme.com",
  "object" : "/api/empl/payment", \\ the endpoint where the event occurred
  "path" : "/api/empl/payment"
}, {
  "id" : 9,
  "date" : "<date>",
  "action" : "GRANT_ROLE",
  "subject" : "johndoe@acme.com",
  "object" : "Grant role ACCOUNTANT to petrpetrov@acme.com",
  "path" : "/api/admin/user/role"
}
]
````
### Event Name Description
The following table describes the event names and their corresponding events that can be logged in the system:

| Description                                                | Event Name      |
|------------------------------------------------------------|-----------------|
| A user has been successfully registered                     | CREATE_USER     |
| A user has changed the password successfully                | CHANGE_PASSWORD |
| A user is trying to access a resource without access rights | ACCESS_DENIED   |
| Failed authentication                                     | LOGIN_FAILED    |
| A role is granted to a user                                | GRANT_ROLE      |
| A role has been revoked                                   | REMOVE_ROLE     |
| The Administrator has locked the user                      | LOCK_USER       |
| The Administrator has unlocked a user                      | UNLOCK_USER     |
| The Administrator has deleted a user                       | DELETE_USER     |
| A user has been blocked on suspicion of a brute force attack | BRUTE_FORCE     |



## License
Feel free to use, modify, and distribute this project as per the terms of the license.

### Contributing
Contributions are welcome! If you find any issues or would like to add new features, please submit a pull request or open an issue.

# Inventory API — Secure Network Switch Inventory Management

A secure REST API built with Java and Spring Boot to manage network switch inventory. This project demonstrates backend application development, relational database integration, RESTful API design, and role-based access control using Spring Security and JSON Web Tokens (JWT). This project was created through both manual effort of basic Java skills and with help from AI to explore and learn about specific technology and framework such as Spring Boot, Spring Security, and Maven.

## Overview

The Inventory API provides a centralized way to manage network switch inventory records, including switch models, hostnames, locations, and lifecycle information. It supports standard CRUD (Create, Read, Update, Delete) operations through REST endpoints and integrates with MySQL for persistent data storage.

The application also includes a browser-based inventory interface with user authentication, providing a foundation for managing inventory through both a web interface and API clients.

Login Page:
<p>
   
</p>
<img width="672" height="547" alt="Login" src="https://github.com/user-attachments/assets/c065a74e-43bd-45f5-8c4f-9e1aa91329e0" />
<p>
   
</p>

Switch Inventory Page:
<p>
   
</p>
<img width="1499" height="845" alt="Inventory Page" src="https://github.com/user-attachments/assets/6cccf68f-c797-4aeb-bf55-ed3b216773c8" />
<p>
   
</p>
The project directory structure, or project template, was generated via the [Spring Initializr](https://start.spring.io/) web tool with the following parameters seelcted:
<p>
   
</p>
<img width="1271" height="923" alt="Spring" src="https://github.com/user-attachments/assets/203dac06-7d9f-45c3-9e92-a0e357ace2b3" />

## Key Features

- **RESTful API Development:** Implements HTTP endpoints for retrieving, creating, updating, and deleting network switch inventory records.
- **Database Integration:** Uses MySQL to store inventory records and application user accounts.
- **JWT Authentication:** Issues signed JSON Web Tokens following successful API login.
- **Role-Based Access Control (RBAC):** Restricts API operations according to user roles, allowing authorized users to view inventory and administrators to perform modifications.
- **Password Security:** Uses BCrypt password hashing to protect stored user passwords.
- **Spring Security Integration:** Validates bearer tokens and enforces authentication and authorization rules for protected API endpoints.
- **Browser-Based Authentication:** Provides a login page, authenticated inventory view, username display, and logout functionality using Spring Security form login and sessions.
- **Environment-Based Configuration:** Uses environment variables for sensitive configuration values, including database credentials and the JWT signing secret.

## Technology Stack

| Technology | Purpose |
|---|---|
| Java 21 | Application development |
| Spring Boot | Application framework |
| Spring Web | REST endpoint development |
| Spring Data JPA | Database access and persistence |
| Spring Security | Authentication and authorization |
| Spring Security OAuth2 Resource Server | JWT bearer-token validation |
| MySQL | Relational database |
| Maven | Dependency management and build automation |
| Thymeleaf | Server-rendered browser pages |
| Git and GitHub | Version control and source code hosting |

## API Endpoints

The following endpoints provide access to switch inventory records.

| Method | Endpoint | Description | Access |
|---|---|---|---|
| POST | `/api/auth/login` | Authenticate a user and obtain a JWT | Public |
| GET | `/api/switches` | Retrieve all switches | User or Admin |
| GET | `/api/switches/{id}` | Retrieve a switch by ID | User or Admin |
| POST | `/api/switches` | Create a switch record | Admin |
| PUT | `/api/switches/{id}` | Update a switch record | Admin |
| DELETE | `/api/switches/{id}` | Delete a switch record | Admin |

Protected API requests use the HTTP `Authorization` header with a bearer token:

`Authorization: Bearer <JWT>`

## Data Model

The application manages network switch inventory records with the following fields:

- `switchID` — Unique identifier for each switch.
- `switchModel` — Switch hardware model.
- `switchHostName` — Hostname assigned to the switch.
- `switchLocation` — Physical or logical location.
- `switchLifecycle` — Lifecycle information associated with the switch.

Application user accounts are maintained separately to support authentication and role-based authorization.

## Security Implementation

Security is an important part of this project.

- Authenticates API users through Spring Security.
- Generates signed JWTs with a configured expiration time.
- Validates bearer tokens on protected API requests.
- Maps JWT authority claims to Spring Security roles.
- Enforces role-based permissions for inventory operations.
- Hashes passwords with BCrypt rather than storing plaintext passwords.
- Separates REST API security configuration from browser-based form authentication.
- Uses environment variables to keep sensitive configuration out of application source code.

## Running the Project Locally

### Prerequisites

- Java Development Kit (JDK) 21
- Maven
- MySQL Server
- IntelliJ IDEA or another Java IDE
- Git

### Setup

1. Clone the repository:

   ```bash
   git clone https://github.com/Richdb20/inventory-api.git
   cd inventory-api
   ```

2. Create a MySQL database and configure the required inventory and application user tables according to the project's entity mappings and database configuration.

3. Configure the following environment variables in your IDE's run configuration or execution environment:

   - `DB_PASSWORD` — MySQL database password.
   - `JWT_SECRET` — A securely generated Base64-encoded signing secret suitable for HS256.
   - `APP_BOOTSTRAP_USERNAME` — Initial administrator username.
   - `APP_BOOTSTRAP_PASSWORD` — Initial administrator password.

   Do not commit real credentials or signing secrets to source control.

4. Verify the database connection settings in `src/main/resources/application.properties`.

5. Start the application using IntelliJ or Maven:

   ```bash
   mvn spring-boot:run
   ```

6. Access the browser interface:

   `http://localhost:8080/login`

7. Use the REST API login endpoint to obtain a JWT:

   `POST http://localhost:8080/api/auth/login`

   Submit a JSON request containing the configured username and password. Use the returned bearer token to access protected API endpoints.

## Skills Demonstrated

This project demonstrates practical experience with:

- Java backend development
- Spring Boot application architecture
- REST API design and HTTP methods
- Relational database integration using MySQL
- Spring Data JPA repositories and entity mapping
- Authentication and authorization
- JWT generation and validation
- Role-based access control
- Password hashing and credential management
- Environment-based application configuration
- Git version control and GitHub project management

## Project Goals

The goal of this project is to build a secure, maintainable backend application that combines inventory management, persistent data storage, and authentication. It serves as a practical demonstration of backend engineering fundamentals and application security concepts.

**Repository:** https://github.com/Richdb20/inventory-api

# Event-Management-App
Spring Boot RESTful Event Management System

This project is a backend application developed using Spring Boot, designed by following RESTful architecture. It manages event-related data with entities such as Event, Venue, Organizer, Registration, and Attendee. The application provides full CRUD operations along with pagination, sorting, and custom queries for advanced data handling.

Features :
1. CRUD operations on Event, Venue, Organizer, Registration, and Attendee
2. Pagination and sorting support for scalable data handling
3. Custom queries using Spring Data JPA
4. PostgreSQL database integration
5. API testing with Postman

Technologies Used :
1. Java
2. Spring Boot
3. Spring Data JPA
4. PostgreSQL
5. Eclipse IDE
6. Postman API

Project Structure :
The application follows a layered architecture including:
1. Controller layer – Handles REST API requests
2. Service layer – Contains business logic
3. DAO (Data Access Object) Layer – Provides an abstraction over persistence logic for cleaner data handling (often merged with Repository in Spring Data JPA, but can be used separately for clarity).
4. DTO (Data Transfer Object) Layer – Defines objects used to transfer data between layers without exposing entity classes directly.
5. Repository layer – Handles database interactions via JPA
6. Entity layer – Defines Event, Venue, Organizer, Registration, and Attendee classes

How It Works :
1. Entities are mapped to relational tables in PostgreSQL.
2. REST APIs allow performing create, read, update, and delete operations.
3. Pagination and sorting parameters can be passed to endpoints for efficient data retrieval.
4. Custom queries are defined in repository interfaces to meet specific business requirements.
5. Postman is used for testing and validating API endpoints.

# Movie Reservation System

A full-featured Movie Reservation System built with Java, Spring Boot, and JPA. This application allows users to browse movies, view showtimes, reserve seats, and manage reservations. Admin users can manage movies, halls, showtimes, and seats.

### challange from [https://roadmap.sh/projects/movie-reservation-system].
---
## Use Case Diagram

```mermaid
flowchart LR
  User([User])
  Admin([Admin])

  User --> RegisterLogin([Register/Login])
  User --> BrowseMovies([Browse Movies])
  User --> SearchShowtimes([Search Showtimes])
  User --> ReserveSeats([Reserve Seats])
  User --> ViewReservations([View Reservations])
  User --> CancelReservation([Cancel Reservation])

  Admin --> MovieMgmt([Add/Edit/Delete Movie])
  Admin --> HallMgmt([Add/Edit/Delete Hall])
  Admin --> ShowtimeMgmt([Add/Edit/Delete Showtime])
  Admin --> ConfigureSeats([Configure Seats])
  Admin --> ViewAllReservations([View All Reservations])
  Admin --> PromoteUser([Promote User])
```
---
## System Architecture Diagram

```mermaid
flowchart TD
    User["User / Admin"]
    FE["Frontend (e.g., React, Angular, Postman)"]
    API["Spring Boot REST API"]
    Auth["Authentication & Security (JWT)"]
    Controller["Controllers"]
    Service["Services"]
    Repo["Repositories (JPA)"]
    DB[("Database (MySQL)")]
    User --> FE
    FE -->|HTTP/JSON| API
    API --> Auth
    API --> Controller
    Controller --> Service
    Service --> Repo
    Repo --> DB
```

---

## Features
- User registration and authentication (JWT-based)
- Browse movies and showtimes
- Reserve seats for showtimes
- Admin panel for managing movies, halls, showtimes, and seats
- Role-based access control (admin/user)
- RESTful API with Swagger documentation

## Tech Stack
- **Backend:** Java, Spring Boot, Spring Security, Spring Data JPA
- **Database:** MySQL (or compatible RDBMS)
- **Build Tool:** Maven
- **API Docs:** Swagger/OpenAPI

## Getting Started

### Prerequisites
- Java 17 or higher
- Maven
- MySQL (or compatible database)

### Setup
1. **Clone the repository:**
   ```bash
   git clone https://github.com/meiatef066/Movie-Reservation-System
   cd Movie-Reservation-system
   ```
2. **Configure the database:**
   - Edit `movie_reservation_system/src/main/resources/application.properties` with your DB credentials.
3. **Build the project:**
   ```bash
   mvn clean install
   ```
4. **Run the application:**
   ```bash
   mvn spring-boot:run
   ```
5. **Access the API docs:**
   - Swagger UI: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## API Overview
- For a full list of endpoints, request/response examples, and authentication details, see:
  - [API Documentation](API_DOCUMENTATION.md)
  - [API Response Formats](API%20Response/API_RESPONSES.md)

## Database Design
- **Entity Relationship Diagram:** See [`Documentation/ERD.png`](Documentation/ERD.png)
- **SQL schema:** [`Documentation/drawSQL-mysql.sql`](Documentation/drawSQL-mysql.sql)

## Authentication & Security
- JWT-based authentication
- Role-based access control (`ADMIN`, `USER`)
- Secure endpoints for admin operations

## Contributing
Pull requests are welcome! For major changes, please open an issue first to discuss what you would like to change.

## Contact
For questions or support, please contact [maiatef066@gmail.com]. 

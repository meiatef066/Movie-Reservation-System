# API Response Formats

This document provides detailed response formats for all API endpoints in the Movie Reservation System.

---

## Standard Response Structure
All API responses are wrapped in this structure:
```json
{
  "statusCode": 200,
  "content": { ... },
  "message": "string"
}
```

---

## Authentication

### Register
- **POST** `/api/auth/register`
- **Success (201):**
```json
{
  "statusCode": 201,
  "content": "jwt-token-string",
  "message": "register success"
}
```
- **Email already taken (409):**
```json
{
  "statusCode": 409,
  "content": null,
  "message": "Email already taken"
}
```

### Login
- **POST** `/api/auth/login`
- **Success (200):**
```json
{
  "statusCode": 200,
  "content": "jwt-token-string",
  "message": "login success"
}
```
- **Wrong password (409):**
```json
{
  "statusCode": 409,
  "content": null,
  "message": "wrong password"
}
```
- **Email not exist (400):**
```json
{
  "statusCode": 400,
  "content": null,
  "message": "this email not exist, please create account then login 👍"
}
```

---

## Movies (Admin)

### Add Movie
- **POST** `/api/admin/movies`
- **Success (201):**
```json
{
  "statusCode": 201,
  "content": null,
  "message": "movie added successfully"
}
```

### Delete Movie
- **DELETE** `/api/admin/movies/{movieId}`
- **Success (200):**
```json
{
  "statusCode": 200,
  "content": null,
  "message": "movie deleted successfully"
}
```

### Update Movie
- **PATCH** `/api/admin/movies/{movieId}`
- **Success (200):**
```json
{
  "statusCode": 200,
  "content": {
    "id": 1,
    "title": "string",
    "description": "string",
    "imageUrl": "string",
    "year": 2024,
    "durationInMinutes": 120,
    "genre": "string",
    "showtimes": [ ... ]
  },
  "message": "Movie updated successfully"
}
```
- **Not found (404):**
```json
{
  "statusCode": 404,
  "content": null,
  "message": "Movie not found"
}
```

### Get All Movies
- **GET** `/api/admin/movies`
- **Success (200):**
```json
{
  "statusCode": 200,
  "content": [
    {
      "id": 1,
      "title": "string",
      "description": "string",
      "imageUrl": "string",
      "year": 2024,
      "durationInMinutes": 120,
      "genre": "string",
      "showtimes": [ ... ]
    }
    // ... more movies
  ],
  "message": null
}
```

---

## Halls (Admin)

### Add Hall
- **POST** `/api/admin/halls`
- **Success (201):**
```json
{
  "statusCode": 201,
  "content": "Hall Name",
  "message": "hall added successfully ✅ 🎞"
}
```
- **Already exists (409):**
```json
{
  "statusCode": 409,
  "content": null,
  "message": "Hall already exist"
}
```

### Delete Hall
- **DELETE** `/api/admin/halls/{hallId}`
- **Success (200):**
```json
{
  "statusCode": 200,
  "content": null,
  "message": "Hall successfully deleted"
}
```
- **Not found (404):**
```json
{
  "statusCode": 404,
  "content": null,
  "message": "Hall not found"
}
```

### Get All Halls
- **GET** `/api/admin/halls`
- **Success (200):**
```json
{
  "statusCode": 200,
  "content": [
    {
      "id": 1,
      "name": "Hall 1",
      "address": "Address",
      "totalRows": 10,
      "totalColumns": 20
    }
    // ... more halls
  ],
  "message": null
}
```

---

## Showtimes

### Create Showtime (Admin)
- **POST** `/api/admin/showtimes`
- **Success (201):**
```json
{
  "statusCode": 201,
  "content": {
    "title": "Movie Title",
    "description": "Movie Description",
    "image": "imageUrl",
    "HallName": "Hall 1",
    "HallAddress": "Address",
    "movieDuration": "120",
    "StartTime": "2024-07-01T18:00:00",
    "EndTime": "2024-07-01T20:00:00"
  },
  "message": "showtime created successfully.✅🎞"
}
```

### Delete Showtime (Admin)
- **POST** `/api/admin/showtimes/{ShowtimeId}`
- **Success (200):**
```json
{
  "statusCode": 200,
  "content": null,
  "message": "show time deleted successfully."
}
```

### Search Showtimes (Public)
- **GET** `/api/public/showtimes/search`
- **Success (200):**
```json
{
  "statusCode": 200,
  "content": [
    {
      "id": 1,
      "movieTitle": "Movie Title",
      "hallName": "Hall 1",
      "startTime": "2024-07-01T18:00:00",
      "endTime": "2024-07-01T20:00:00",
      "availableSeats": 50
    }
    // ... more showtimes
  ],
  "message": "Search successful."
}
```

---

## Reservations

### Create Reservation (User)
- **POST** `/api/reservations`
- **Success (201):**
```json
{
  "statusCode": 201,
  "content": {
    "TotalPrice": 40,
    "seatDetails": [
      {
        "id": 1,
        "row": 1,
        "number": 5,
        "seatPrice": 20,
        "seatType": "Normal"
      }
      // ... more seats
    ],
    "ShowtimeDate": "2024-07-01T18:00:00",
    "movieTitle": "Movie Title"
  },
  "message": "Reservation completed 🎉"
}
```
- **Seats already reserved (409):**
```json
{
  "statusCode": 409,
  "content": null,
  "message": "Seats already reserved: [10, 11]"
}
```

### Cancel Reservation (User)
- **DELETE** `/api/reservations/{reservationId}/cancel`
- **Success (200):**
```json
{
  "statusCode": 200,
  "content": null,
  "message": "Reservation successfully cancelled and seats are now available"
}
```

---

## Seats (Admin)

### Configure Seats
- **POST** `/api/admin/halls/{hallId}/seats`
- **Success (202):**
```json
{
  "statusCode": 202,
  "content": [
    {
      "row": 1,
      "column": 2,
      "seatNumber": 5,
      "seatType": "Normal",
      "seatPrice": 20
    }
    // ... more seats
  ],
  "message": "Seats configured successfully for hall Hall 1 in Address"
}
```

---

## General Error Response
- **Internal Server Error (500):**
```json
{
  "statusCode": 500,
  "content": null,
  "message": "Internal Server Error"
}
``` 
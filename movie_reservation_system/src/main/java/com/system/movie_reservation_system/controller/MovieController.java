package com.system.movie_reservation_system.controller;


import com.system.movie_reservation_system.model.Movie;
import com.system.movie_reservation_system.service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@PreAuthorize("role("admin")")
@RestController
@RequestMapping("/api/admin/movies")
@Tag(name = "Admin - Movies", description = "Endpoints for managing movies (admin only)")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @Operation(summary = "Add a new movie", description = "Creates a new movie with title, description, genre, and image URL.")
    @PostMapping
    public ResponseEntity<Object> addMovie( @RequestBody Movie movie ){
        var response=movieService.addMovie(movie);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
    @Operation(summary = "Delete a movie", description = "Deletes a movie by its ID.")
    @DeleteMapping("/{movieId}")
    public ResponseEntity<Object> deleteMovie(@PathVariable Long movieId){
        var response=movieService.deleteMovie(movieId);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @Operation(summary = "Update a movie", description = "Partially updates a movie by ID. Only non-null fields will be updated.")
    @PatchMapping("/{movieId}")
    public ResponseEntity<Object> updateMovie(@PathVariable Long movieId, @RequestBody Movie movie ){
        var response=movieService.updateMovie(movieId,movie);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @Operation(summary = "Get all movies", description = "Returns a list of all available movies.")
    @GetMapping
    public ResponseEntity<Object> getMovies() {
        var response=movieService.getMovies();
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
}
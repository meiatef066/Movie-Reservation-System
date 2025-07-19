package com.system.movie_reservation_system.service;

import com.system.movie_reservation_system.dto.responses.HttpCustomResponse;
import com.system.movie_reservation_system.model.Movie;
import com.system.movie_reservation_system.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    // Crud
    @Autowired
    private MovieRepository movieRepository;

    public HttpCustomResponse<Object> addMovie( Movie movie ) {
        try {
            // check if admin 403
            movieRepository.save(movie);
            return new HttpCustomResponse<>(201, null, "movie added successfully");

        } catch (Exception e) {
            e.printStackTrace();
            return new HttpCustomResponse<>(500, null, "Internal Server Error");
        }
    }

    public HttpCustomResponse<Object> deleteMovie( Long movieId ) {
        try {
            movieRepository.deleteById(movieId);
            return new HttpCustomResponse<>(200, null, "movie deleted successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return new HttpCustomResponse<>(500, null, "Internal Server Error");
        }
    }

    public HttpCustomResponse<Object> updateMovie( Long movieId, Movie movie ) {
        try {
            var movieOptional = movieRepository.findById(movieId);
            if (!movieOptional.isPresent()) {
                return new HttpCustomResponse<>(404, null, "Movie not found");
            }
            var existingMovie = movieOptional.get();

            if (movie.getTitle() != null) existingMovie.setTitle(movie.getTitle());

            if (movie.getDescription() != null) existingMovie.setDescription(movie.getDescription());

            if (movie.getGenre() != null) existingMovie.setGenre(movie.getGenre());

            if (movie.getImageUrl() != null) existingMovie.setImageUrl(movie.getImageUrl());

            // Save the updated movie
            movieRepository.save(existingMovie);
            return new HttpCustomResponse<>(200, existingMovie, "Movie updated successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return new HttpCustomResponse<>(500, null, "Internal Server Error");
        }
    }

    public HttpCustomResponse<Object> getMovies() {
        try {
            List<Movie> movies = movieRepository.findAll();
            return new HttpCustomResponse<>(200, movies, null);
        } catch (Exception e) {
            e.printStackTrace();
            return new HttpCustomResponse<>(500, null, "Internal Server Error");
        }

    }
}

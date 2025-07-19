package com.system.movie_reservation_system.repository;

import com.system.movie_reservation_system.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    Optional<Movie> findById( Long id);
    Movie findByTitle( String title );

    void deleteMoviesById( int movieId );
}

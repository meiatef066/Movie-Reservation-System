package com.system.movie_reservation_system.repository;

import com.system.movie_reservation_system.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail( String email );
}

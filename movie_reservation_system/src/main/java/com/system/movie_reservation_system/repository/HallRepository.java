package com.system.movie_reservation_system.repository;

import com.system.movie_reservation_system.model.Hall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HallRepository extends JpaRepository<Hall,Long> {
    Optional<Hall> findByNameAndAddress( String hallName, String hallAddress );

    List<Hall> findAllByName( String hallName );

    List<Hall> findAllByAddress( String hallAddress );
}

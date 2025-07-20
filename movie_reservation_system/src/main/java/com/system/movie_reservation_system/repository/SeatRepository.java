package com.system.movie_reservation_system.repository;

import com.system.movie_reservation_system.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {
    ArrayList<Seat> findAllByHallId( Long id );
}


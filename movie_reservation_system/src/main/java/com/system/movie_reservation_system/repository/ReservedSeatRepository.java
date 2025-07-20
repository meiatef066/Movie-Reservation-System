package com.system.movie_reservation_system.repository;

import com.system.movie_reservation_system.model.ReservedSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservedSeatRepository extends JpaRepository<ReservedSeat, Long> {

    @Query("SELECT COUNT(rs) FROM ReservedSeat rs WHERE rs.showtime.id = :showtimeId")
    int countReservedSeatsByShowtimeId(@Param("showtimeId") Long showtimeId);

}

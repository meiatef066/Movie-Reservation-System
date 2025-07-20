package com.system.movie_reservation_system.repository;

import com.system.movie_reservation_system.model.ReservedSeat;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservedSeatRepository extends JpaRepository<ReservedSeat, Long> {

    @Query("SELECT COUNT(rs) FROM ReservedSeat rs WHERE rs.showtime.id = :showtimeId")
    int countReservedSeatsByShowtimeId(@Param("showtimeId") Long showtimeId);

    List<ReservedSeat> findByShowtimeIdAndSeatIdIn( @NotBlank(message = "Showtime should not be empty") Long showtimeId, @NotBlank(message = "Select at least one seat") List<Long> seatIds );

    List<ReservedSeat> findByReservationId( Long reservationId );
}

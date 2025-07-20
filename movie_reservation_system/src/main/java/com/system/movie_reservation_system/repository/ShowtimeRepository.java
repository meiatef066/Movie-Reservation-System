package com.system.movie_reservation_system.repository;

import com.system.movie_reservation_system.dto.responses.ShowtimeSearchResponse;
import com.system.movie_reservation_system.model.Showtime;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;


@Repository
public interface ShowtimeRepository extends JpaRepository<Showtime,Long> {
    Optional<Showtime> findByHallIdAndStartTimeAndEndTime( Long id, LocalDateTime startTime, LocalDateTime endTime );

@Query("""
    SELECT new com.system.movie_reservation_system.dto.responses.ShowtimeSearchResponse(
        s.id,
        m.title,
        h.name,
        s.startTime,
        s.endTime,
        (SELECT COUNT(rs.id) FROM ReservedSeat rs WHERE rs.showtime.id = s.id)
    )
    FROM Showtime s
    JOIN s.movie m
    JOIN s.hall h
    WHERE (:movieName IS NULL OR LOWER(m.title) LIKE LOWER(CONCAT('%', :movieName, '%')))
      AND (:hallName IS NULL OR LOWER(h.name) LIKE LOWER(CONCAT('%', :hallName, '%')))
      AND (:date IS NULL OR FUNCTION('DATE', s.startTime) = FUNCTION('DATE', :date))
""")
Page<ShowtimeSearchResponse> searchShowtimesWithDTO(
        @Param("movieName") String movieName,
        @Param("hallName") String hallName,
        @Param("date") LocalDateTime date,
        Pageable pageable
);



}

package com.system.movie_reservation_system.dto.responses;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShowtimeSearchResponse {
    private Long id;
    private String movieTitle;
    private String hallName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private long availableSeats; // Use long instead of int to match COUNT result
//    public ShowtimeSearchResponse(
//            Long showtimeId,
//            String movieTitle,
//            String hallName,
//            LocalDateTime startTime,
//            LocalDateTime endTime,
//            long reservedSeats
//    ) {
//        this.showtimeId = showtimeId;
//        this.movieTitle = movieTitle;
//        this.hallName = hallName;
//        this.startTime = startTime;
//        this.endTime = endTime;
//        this.reservedSeats = reservedSeats;
//    }

}

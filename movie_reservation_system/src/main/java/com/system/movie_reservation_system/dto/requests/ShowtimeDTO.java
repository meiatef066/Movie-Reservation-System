package com.system.movie_reservation_system.dto.requests;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class ShowtimeDTO {
    private Long movieId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Long hallId;
}

package com.system.movie_reservation_system.dto.responses;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ShowtimeResponse {
    private String title;
    private String description;
    private String image;
    private String HallName;
    private String HallAddress;
    private String movieDuration;
    private LocalDateTime StartTime;
    private LocalDateTime EndTime;
}

package com.system.movie_reservation_system.dto.requests;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SearchShowtimeRequest {
    private String movieName;
    private String hallName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int page = 0;
    private int size = 10;
    private String sortBy = "startTime";
    private String sortDir = "asc";
}

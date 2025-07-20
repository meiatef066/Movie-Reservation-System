package com.system.movie_reservation_system.controller;


import com.system.movie_reservation_system.dto.requests.SearchShowtimeRequest;
import com.system.movie_reservation_system.dto.requests.ShowtimeDTO;
import com.system.movie_reservation_system.service.ShowtimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api")
public class ShowtimeController {
    @Autowired
    private ShowtimeService showtimeService;

    @PostMapping("/admin/showtimes")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> createShowtime( @RequestBody ShowtimeDTO showtimeDTO ) {
        var response = showtimeService.AddShowtime(showtimeDTO);
        return ResponseEntity.status(response.getStatusCode()).body(response.getContent() != null ? response.getMessage() : response);
    }

    @PostMapping("/admin/showtimes/{ShowtimeId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> deleteShowtime(@PathVariable  Long ShowtimeId ) {
        var response = showtimeService.deleteShowtime(ShowtimeId);
        return ResponseEntity.status(response.getStatusCode()).body(response.getContent() != null ? response.getMessage() : response);
    }

    @GetMapping("/public/showtimes/search")
    public ResponseEntity<Object> searchShowtime(
            @RequestParam(required = false) String movieName,
            @RequestParam(required = false) String hallName,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "startTime") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir
    ) {
        SearchShowtimeRequest request = new SearchShowtimeRequest();
        request.setMovieName(movieName);
        request.setHallName(hallName);
        request.setStartTime(startTime);
        request.setPage(page);
        request.setSize(size);
        request.setSortBy(sortBy);
        request.setSortDir(sortDir);

        var response = showtimeService.search(request);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

}


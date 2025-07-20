package com.system.movie_reservation_system.controller;

import com.system.movie_reservation_system.dto.requests.ConfigureSeatsRequest;
import com.system.movie_reservation_system.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/admin")
public class SeatController {
    @Autowired
    private SeatService seatService;

    @PostMapping("halls/{hallId}/seats")
    public ResponseEntity<Object> configureSeats( @PathVariable Long hallId, @RequestBody ArrayList<ConfigureSeatsRequest> seatsRequest )
    {
        var response=seatService.ConfigureSeatForHall(hallId,seatsRequest);
        return  ResponseEntity.status(response.getStatusCode()).body(response);
    }
}

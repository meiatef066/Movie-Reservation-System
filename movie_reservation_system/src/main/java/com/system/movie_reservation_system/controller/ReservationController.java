package com.system.movie_reservation_system.controller;

import com.system.movie_reservation_system.dto.requests.ReservationRequest;
import com.system.movie_reservation_system.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @PostMapping
    public ResponseEntity<Object> createReservation( @RequestBody ReservationRequest reservationRequest ) {
        String email= SecurityContextHolder.getContext().getAuthentication().getName();
        var response=reservationService.creatReservation(reservationRequest,email);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @DeleteMapping("/{reservationId}/cancel")
    public ResponseEntity<Object> cancelReservation( @PathVariable Long reservationId ) {
        String email= SecurityContextHolder.getContext().getAuthentication().getName();
        var response=reservationService.cancelReservation(reservationId,email);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
}

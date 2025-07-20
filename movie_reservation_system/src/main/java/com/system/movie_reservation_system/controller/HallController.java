package com.system.movie_reservation_system.controller;

import com.system.movie_reservation_system.dto.requests.HallRequest;
import com.system.movie_reservation_system.service.HallService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/admin/halls")
@PreAuthorize("hasRole('ADMIN')")
public class HallController {

    @Autowired
    private HallService hallService;

    @PostMapping
    public ResponseEntity<Object> addHall(@Valid @RequestBody HallRequest hallRequest) {
        var response = hallService.addHall(hallRequest);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @DeleteMapping("/{hallId}")
    public ResponseEntity<Object> deleteHall(@PathVariable Long hallId) {
        var response = hallService.deleteHall(hallId);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping
    public ResponseEntity<Object> getAllHalls() {
        var response = hallService.getAllHalls();
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/id/{hallId}")
    public ResponseEntity<Object> getHallById(@PathVariable Long hallId) {
        var response = hallService.getHallById(hallId);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/name/{hallName}")
    public ResponseEntity<Object> getHallByName(@PathVariable String hallName) {
        var response = hallService.getHallByHallName(hallName);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
    @GetMapping("/name/{address}")
    public ResponseEntity<Object> getHallByAddress(@PathVariable String address) {
        var response = hallService.getHallByHallName(address);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

}

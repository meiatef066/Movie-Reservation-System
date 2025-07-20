package com.system.movie_reservation_system.controller;

import com.system.movie_reservation_system.dto.requests.AuthRequest;
import com.system.movie_reservation_system.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Tag(
        name = "Authentication Controller",
        description = "Allows users to authenticate themselves on the app")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")

    public ResponseEntity<Object> login(@Valid @RequestBody AuthRequest authRequest ) {
        var response = authService.login(authRequest);
        return ResponseEntity.status(response.getStatusCode())
                .body(response);
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@Valid @RequestBody AuthRequest authRequest ) {
        var response = authService.register(authRequest);
        return ResponseEntity.status(response.getStatusCode())
                .body(response);
    }
}

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
    @Operation(
            summary = "Login",
            description = "It allows a user to authenticate itself",
            requestBody =
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Payload containing the user credentials.",
                    required = true,
                    content = @Content(schema = @Schema(implementation = AuthRequest.class))),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Authentication successful"),
                    @ApiResponse(responseCode = "400", description = "Bad credentials"),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal Server Error",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(example = "Internal Server Error")))
            })
    public ResponseEntity<Object> login(@Valid @RequestBody AuthRequest authRequest ) {
        var response = authService.login(authRequest);
        return ResponseEntity.status(response.getStatusCode())
                .body(response);
    }

    @Operation(
            summary = "Register",
            description = "It allows a person to create a user",
            requestBody =
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Payload containing the user credentials.",
                    required = true,
                    content = @Content(schema = @Schema(implementation = AuthRequest.class))),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Registration successful"),
                    @ApiResponse(responseCode = "400", description = "Email already taken"),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal Server Error",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(example = "Internal Server Error")))
            })
    @PostMapping("/register")
    public ResponseEntity<Object> register(@Valid @RequestBody AuthRequest authRequest ) {
        var response = authService.register(authRequest);
        return ResponseEntity.status(response.getStatusCode())
                .body(response);
    }
    @GetMapping
    public String hellow(){
        return "hello";
    }
}

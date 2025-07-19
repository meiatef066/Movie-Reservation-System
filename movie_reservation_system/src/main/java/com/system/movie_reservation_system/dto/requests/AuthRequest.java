package com.system.movie_reservation_system.dto.requests;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRequest {
    @Email(message = "Email should be valid")
    @NotBlank(message = "Email shouldn’t be empty")
    private String email;

    @NotBlank(message = "Password shouldn’t be empty")
    @Size(min = 6, message = "Password should be at least 6 characters long")
    private String password;

}

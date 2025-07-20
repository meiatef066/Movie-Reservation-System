package com.system.movie_reservation_system.dto.requests;

import com.system.movie_reservation_system.model.Seat;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConfigureSeatsRequest {
    @NotNull(message = "Please assign a value to seat row")
    @Min(value = 1, message = "Seat row must be at least 1")
    private Integer seatRow;

    @NotNull(message = "Please assign a value to seat column")
    @Min(value = 1, message = "Seat column must be at least 1")
    private Integer seatColumn;

    @NotNull(message = "Please assign a value to seat number")
    @Min(value = 1, message = "Seat number must be at least 1")
    private Integer seatNumber;

    @NotNull(message = "Please assign a value to seat type")
    private Seat.SeatType seatType;

    @NotNull(message = "Please assign a value to seat price")
    @Min(value = 0, message = "Seat price must be 0 or more")
    private Integer seatPrice;
}

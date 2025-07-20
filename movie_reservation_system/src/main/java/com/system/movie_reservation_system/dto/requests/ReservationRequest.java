package com.system.movie_reservation_system.dto.requests;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class ReservationRequest {

    @NotNull(message = "Showtime ID must not be null")
    private Long showtimeId;

    @NotEmpty(message = "Select at least one seat")
    private List<Long> seatIds;
}

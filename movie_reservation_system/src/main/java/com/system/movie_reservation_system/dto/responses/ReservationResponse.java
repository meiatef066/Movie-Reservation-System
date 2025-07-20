package com.system.movie_reservation_system.dto.responses;
import com.system.movie_reservation_system.model.Seat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ReservationResponse {
    private Integer TotalPrice;
    private List<SeatInfo> seatDetails;
    private LocalDateTime ShowtimeDate;
    private String movieTitle;
}

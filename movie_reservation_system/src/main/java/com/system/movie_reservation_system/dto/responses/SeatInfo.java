package com.system.movie_reservation_system.dto.responses;

import com.system.movie_reservation_system.model.Seat;
import lombok.Data;

@Data
public class SeatInfo {
    private Long id;
    private Integer row;
    private Integer number;
    private Integer seatPrice;
    private Seat.SeatType seatType;
}

package com.system.movie_reservation_system.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SeatResponse {
    private Integer row;
    private Integer column;
    private Integer seatNumber;
    private String seatType;
    private Integer seatPrice;
}

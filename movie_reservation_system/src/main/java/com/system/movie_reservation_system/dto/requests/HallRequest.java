package com.system.movie_reservation_system.dto.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HallRequest {
    //    private int hallId;
    private String hallName;
    private String hallAddress;
    private Integer totalRow;
    private Integer totalCol;
}

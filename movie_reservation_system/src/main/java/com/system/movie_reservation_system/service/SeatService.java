package com.system.movie_reservation_system.service;


import com.system.movie_reservation_system.dto.requests.ConfigureSeatsRequest;
import com.system.movie_reservation_system.dto.responses.HttpCustomResponse;
import com.system.movie_reservation_system.dto.responses.SeatResponse;
import com.system.movie_reservation_system.model.Seat;
import com.system.movie_reservation_system.repository.HallRepository;
import com.system.movie_reservation_system.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SeatService {
    @Autowired
    private HallRepository hallRepository;
    @Autowired
    private SeatRepository seatRepository;
    private Integer defaultPrice=20;
    private Integer VIPSeatPrice= (int) (defaultPrice*1.5);

    public HttpCustomResponse<Object> ConfigureSeatForHall( Long hallId, ArrayList<ConfigureSeatsRequest> seatsRequest ) {
        var hall=hallRepository.findById(hallId);
        if(hall.isEmpty()) {
            return new HttpCustomResponse<>(404,null,"Hall not Exist");
        }
        int hallColumn=hall.get().getTotalColumns();
        int hallRow=hall.get().getTotalRows();

        List<Seat> seats = new ArrayList<>();
        for(ConfigureSeatsRequest configureSeatsRequest : seatsRequest) {
            if(configureSeatsRequest.getSeatColumn()>hallColumn && configureSeatsRequest.getSeatRow()>hallRow) {
                return new HttpCustomResponse<>(400,null,"Not valid seat row or column");
            }
            Seat seat = Seat.builder()
                    .hall(hall.get())
                    .seatType(configureSeatsRequest.getSeatType()!=null?configureSeatsRequest.getSeatType(): Seat.SeatType.Normal)
                    .row(configureSeatsRequest.getSeatRow())
                    .column(configureSeatsRequest.getSeatColumn())
                    .seatNumber(configureSeatsRequest.getSeatNumber())
                    .seatPrice(configureSeatsRequest.getSeatPrice()!=null?configureSeatsRequest.getSeatPrice():defaultPrice)
                    .build();
            seats.add(seat);
        }
        seatRepository.saveAll(seats);

        List<SeatResponse> responseList = seats.stream()
                .map(seat -> new SeatResponse(
                        seat.getRow(),
                        seat.getColumn(),
                        seat.getSeatNumber(),
                        seat.getSeatType().toString(),
                        seat.getSeatPrice()
                ))
                .toList();

        return new HttpCustomResponse<>(202,responseList,"Seats configured successfully for hall"+hall.get().getName()+" in "+ hall.get().getAddress());
    }

}

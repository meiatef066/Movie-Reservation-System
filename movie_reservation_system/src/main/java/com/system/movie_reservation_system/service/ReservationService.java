package com.system.movie_reservation_system.service;

import com.system.movie_reservation_system.dto.requests.ReservationRequest;
import com.system.movie_reservation_system.dto.responses.HttpCustomResponse;
import com.system.movie_reservation_system.dto.responses.ReservationResponse;
import com.system.movie_reservation_system.dto.responses.SeatInfo;
import com.system.movie_reservation_system.model.*;
import com.system.movie_reservation_system.repository.*;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class  ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private ShowtimeRepository showtimeRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SeatRepository seatRepository;
    @Autowired
    private HallRepository hallRepository;
    @Autowired
    private ReservedSeatRepository reservedSeatRepository;
    @Transactional
    public HttpCustomResponse<Object> creatReservation( ReservationRequest request, String userEmail) {
            // 1. Check user
            var userOpt = userRepository.findByEmail(userEmail);
            if (userOpt.isEmpty()) {
                return new HttpCustomResponse<>(404, null, "User not found");
            }
            var user = userOpt.get();

            // 2. Check showtime
        if (request.getShowtimeId() == null || request.getSeatIds() == null || request.getSeatIds().isEmpty()) {
            return new HttpCustomResponse<>(400, null, "Showtime ID and seat IDs must not be null or empty.");
        }

        var showtimeOpt = showtimeRepository.findById(request.getShowtimeId());
            if (showtimeOpt.isEmpty()) {
                return new HttpCustomResponse<>(404, null, "Showtime does not exist");
            }
            var showtime = showtimeOpt.get();

            // 3. Check that all seats exist in that hall
            List<Seat> hallSeats = seatRepository.findAllByHallId(showtime.getHall().getId());
            List<Long> hallSeatIds = hallSeats.stream().map(Seat::getId).toList();
            for (Long seatId : request.getSeatIds()) {
                if (!hallSeatIds.contains(seatId)) {
                    return new HttpCustomResponse<>(400, null, "Invalid seat selected: " + seatId);
                }
            }
            // 4. Check if any seat is already reserved
            List<ReservedSeat> alreadyReserved = reservedSeatRepository
                    .findByShowtimeIdAndSeatIdIn(request.getShowtimeId(), request.getSeatIds());

            if (!alreadyReserved.isEmpty()) {
                List<Long> taken = alreadyReserved.stream()
                        .map(rs -> rs.getSeat().getId())
                        .toList();
                return new HttpCustomResponse<>(409, null, "Seats already reserved: " + taken);
            }


            // 5. Save reservation
            Reservation reservation = new Reservation();
            reservation.setUser(user);
            reservation.setShowtime(showtime);
            reservation.setReservationTime(LocalDateTime.now());
            reservation.setStatus(Reservation.Status.ACTIVE);
            reservationRepository.save(reservation);

            // 6. Save reserved seats
            Integer TotalPrice = 0;
            List<ReservedSeat> reservedSeats = new ArrayList<>();
            List<Seat> selectedSeats = new ArrayList<>();

            for (Long seatId : request.getSeatIds()) {
                Seat seat = seatRepository.findById(seatId).orElseThrow();
                selectedSeats.add(seat);
                seat.setActive(false );
                TotalPrice+=seat.getSeatPrice();
                ReservedSeat rs = new ReservedSeat();
                rs.setReservation(reservation);
                rs.setSeat(seat);
                rs.setShowtime(showtime);
                reservedSeats.add(rs);
            }
        List<SeatInfo> seatInfos = selectedSeats.stream().map(seat -> {
            SeatInfo info = new SeatInfo();
            info.setId(seat.getId());
            info.setRow(seat.getRow());
            info.setNumber(seat.getSeatNumber());
            info.setSeatPrice(seat.getSeatPrice());
            info.setSeatType(seat.getSeatType());
            return info;
        }).toList();
            reservedSeatRepository.saveAll(reservedSeats);
            ReservationResponse reservationResponse=new ReservationResponse();
            reservationResponse.setMovieTitle(showtime.getMovie().getTitle());
            reservationResponse.setShowtimeDate(showtime.getStartTime());
            reservationResponse.setSeatDetails(seatInfos);
            reservationResponse.setTotalPrice(TotalPrice);

            return new HttpCustomResponse<>(201, reservationResponse, "Reservation completed 🎉");

    }

    public HttpCustomResponse<Object> cancelReservation(Long reservationId, String userEmail) {
        // 1. Verify the user
        var userOpt = userRepository.findByEmail(userEmail);
        if (userOpt.isEmpty()) {
            return new HttpCustomResponse<>(404, null, "User not found");
        }
        var user = userOpt.get();

        // 2. Find the reservation
        var reservationOpt = reservationRepository.findById(Math.toIntExact(reservationId));
        if (reservationOpt.isEmpty()) {
            return new HttpCustomResponse<>(404, null, "Reservation not found");
        }
        var reservation = reservationOpt.get();

        // 3. Ensure the reservation belongs to the user
        if (!reservation.getUser().getId().equals(user.getId())) {
            return new HttpCustomResponse<>(403, null, "You are not authorized to cancel this reservation");
        }

        // 4. Fetch all reserved seats for this reservation
        List<ReservedSeat> reservedSeats = reservedSeatRepository.findByReservationId(reservationId);

        if (reservedSeats.isEmpty()) {
            return new HttpCustomResponse<>(400, null, "No seats associated with this reservation");
        }

        // 5. Mark each seat as active again
        for (ReservedSeat rs : reservedSeats) {
            Seat seat = rs.getSeat();
            seat.setActive(true);
            seatRepository.save(seat); // Update the seat status
        }

        // 6. Delete reserved seats first (due to FK constraint)
        reservedSeatRepository.deleteAll(reservedSeats);

        // 7. Delete the reservation
        reservation.setCanceledAt(LocalDateTime.now());
        reservation.setStatus(Reservation.Status.CANCELED);
        reservationRepository.save(reservation);

        return new HttpCustomResponse<>(200, null, "Reservation successfully cancelled and seats are now available");
    }

}

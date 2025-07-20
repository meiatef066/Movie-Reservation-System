package com.system.movie_reservation_system.service;

import com.system.movie_reservation_system.dto.requests.SearchShowtimeRequest;
import com.system.movie_reservation_system.dto.requests.ShowtimeDTO;
import com.system.movie_reservation_system.dto.responses.HttpCustomResponse;
import com.system.movie_reservation_system.dto.responses.ShowtimeResponse;
import com.system.movie_reservation_system.dto.responses.ShowtimeSearchResponse;
import com.system.movie_reservation_system.model.Movie;
import com.system.movie_reservation_system.model.Showtime;
import com.system.movie_reservation_system.repository.HallRepository;
import com.system.movie_reservation_system.repository.MovieRepository;
import com.system.movie_reservation_system.repository.ShowtimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.*;

import java.time.LocalDateTime;
import java.util.List;

// admin able to post(CRUD) showtime
// user able to Browse showtime
@Service
public class ShowtimeService {
    @Autowired
    private ShowtimeRepository showtimeRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private HallRepository hallRepository;

    // for admin
    public HttpCustomResponse<Object> AddShowtime( ShowtimeDTO showtimeDTO ) {
//        check if
//        movie and hall is existed ✅
//        hall not reserved for this time start time ✅
        try {
            var movie = movieRepository.findById(showtimeDTO.getMovieId());
            if (!movie.isPresent()) {
                return new HttpCustomResponse<>(404, null, "please provide an exist movie id.");
            }
            var hall = hallRepository.findById(showtimeDTO.getHallId());
            if (!hall.isPresent()) {
                return new HttpCustomResponse<>(404, null, "please provide an exist hall id.");
            }
            var showtime = showtimeRepository.findByHallIdAndStartTimeAndEndTime(hall.get().getId(), showtimeDTO.getStartTime(), showtimeDTO.getEndTime());
            if (showtime.isPresent()) {
                return new HttpCustomResponse<>(409, null, "Hal is reserved for this time " + showtime.get().getMovie().getTitle());
            }
            Showtime newShowtime = new Showtime();
            newShowtime.setHall(hall.get());
            newShowtime.setMovie(movie.get());
            newShowtime.setStartTime(showtimeDTO.getStartTime());
            newShowtime.setEndTime(showtimeDTO.getStartTime().plusMinutes(movie.get().getDurationInMinutes()));
            LocalDateTime now = LocalDateTime.now();
            newShowtime.setCreatedAt(now);

            showtimeRepository.save(newShowtime);

            ShowtimeResponse showtimeResponse = new ShowtimeResponse();
            showtimeResponse.setTitle(movie.get().getTitle());
            showtimeResponse.setDescription(movie.get().getDescription());
            showtimeResponse.setImage(movie.get().getImageUrl());
            showtimeResponse.setMovieDuration(String.valueOf(movie.get().getDurationInMinutes()));
            showtimeResponse.setHallName(hall.get().getName());
            showtimeResponse.setHallAddress(hall.get().getAddress());
            showtimeResponse.setStartTime(now);
            showtimeResponse.setEndTime(showtimeDTO.getStartTime().plusMinutes(movie.get().getDurationInMinutes()));
            return new HttpCustomResponse<>(201, showtimeResponse, "showtime created successfully.✅🎞");
        } catch (Exception e) {
            e.printStackTrace();
            return new HttpCustomResponse<>(500, e.getMessage(), "internal server error.");
        }
    }

    public HttpCustomResponse<Object> deleteShowtime( Long ShowtimeId ) {
        try {
            var showtime = showtimeRepository.findById(ShowtimeId);
            if (!showtime.isPresent()) {
                return new HttpCustomResponse<>(404, null, "show time not exist");
            }
            showtimeRepository.deleteById(ShowtimeId);
            return new HttpCustomResponse<>(200, null, "show time deleted successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            return new HttpCustomResponse<>(500, e.getMessage(), "internal server error.");
        }
    }
    // for public
    public HttpCustomResponse<Object> search(SearchShowtimeRequest request) {
        try {
            // Create pageable
            Sort sort = request.getSortDir().equalsIgnoreCase("desc")
                    ? Sort.by(request.getSortBy()).descending()
                    : Sort.by(request.getSortBy()).ascending();

            Pageable pageable = PageRequest.of(request.getPage(), request.getSize(), sort);

            Page<ShowtimeSearchResponse> pageResult = showtimeRepository.searchShowtimesWithDTO(
                    request.getMovieName(),
                    request.getHallName(),
                    request.getStartTime(),
                    pageable
            );
            System.out.println("Total Elements: " + pageResult.getTotalElements());

            pageResult.getContent().forEach(showtime -> {
                System.out.println(showtime.getMovieTitle() + " at " + showtime.getStartTime());
            });

            return new HttpCustomResponse<>(200, pageResult.getContent(), "Search successful.");
        } catch (Exception e) {
            e.printStackTrace();
            return new HttpCustomResponse<>(500, e.getMessage(), "Internal server error.");
        }
    }
}

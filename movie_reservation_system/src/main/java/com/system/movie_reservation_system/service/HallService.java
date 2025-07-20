package com.system.movie_reservation_system.service;

import com.system.movie_reservation_system.dto.requests.HallRequest;
import com.system.movie_reservation_system.dto.responses.HttpCustomResponse;
import com.system.movie_reservation_system.model.Hall;
import com.system.movie_reservation_system.repository.HallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HallService {
    @Autowired
    private HallRepository hallRepository;
    public HttpCustomResponse<Object> addHall( HallRequest hallRequest ) {
        try {
            // check if already exist
            var hall = hallRepository.findByNameAndAddress(hallRequest.getHallName(), hallRequest.getHallAddress());
            if (hall.isPresent()) {
                return new HttpCustomResponse<>(409, null, "Hall already exist");
            }

            Hall newHall = new Hall();
            newHall.setName(hallRequest.getHallName());
            newHall.setAddress(hallRequest.getHallAddress());
            newHall.setTotalColumns(hallRequest.getTotalCol());
            newHall.setTotalRows(hallRequest.getTotalRow());

            hallRepository.save(newHall);
            return new HttpCustomResponse<>(201, hallRequest.getHallName(), "hall added successfully ✅ 🎞");
        } catch (Exception e) {
            return new HttpCustomResponse<>(500, null, "Internal server error.");
        }
    }

    public HttpCustomResponse<Object> deleteHall  (Long hallId) {
        try {
            var hall = hallRepository.findById(hallId);
            if (!hall.isPresent()) {
                return new HttpCustomResponse<>(404, null, "Hall not found");
            }
            hallRepository.deleteById(hallId);
            return new HttpCustomResponse<>(200, null, "Hall successfully deleted");
        }catch (Exception e) {
            return new HttpCustomResponse<>(500, null, "Internal server error.");
        }
    }
    public HttpCustomResponse<Object> updateHall(HallRequest hallRequest) {return null;}
    public HttpCustomResponse<Object> getHallById(Long hallId) {
        var hall = hallRepository.findById(hallId);
        return new HttpCustomResponse<>(200,hall,null);
    }
    public HttpCustomResponse<Object> getAllHalls() {
        List<Hall> halls=hallRepository.findAll();
        return new HttpCustomResponse<>(200,halls,null);
    }
    public HttpCustomResponse<Object> getHallByHallName(String hallName) {
        List<Hall> halls=hallRepository.findAllByName(hallName);
        return new HttpCustomResponse<>(200,halls,null);
    }
    public HttpCustomResponse<Object> getHallByHallAddress(String hallAddress) {
        List<Hall>halls=hallRepository.findAllByAddress(hallAddress);
        return new HttpCustomResponse<>(200,halls,null);

    }
}

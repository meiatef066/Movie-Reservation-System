package com.system.movie_reservation_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@SpringBootApplication
public class MovieReservationSystemApplication {

	public static void main(String[] args) {
		System.out.println(LocalDateTime.now());
		SpringApplication.run(MovieReservationSystemApplication.class, args);
	}

}

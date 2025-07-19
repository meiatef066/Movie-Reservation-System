package com.system.movie_resevation_system.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="halls")
public class Hall {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private Integer TotalRows;
    private Integer TotalColumns;

    @OneToMany(mappedBy = "halls")
    private List<Seat> seats;

    @OneToMany(mappedBy = "hall")
    private List<Showtime> showtimes;
}

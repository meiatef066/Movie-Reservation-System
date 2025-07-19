package com.system.movie_resevation_system.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "seats", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"hall_id", "row","column", "seat_number"})
})
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "seat_number")
    private int seatNumber;

    private Integer column;
    private Integer row;

    @Enumerated(EnumType.STRING)
    private SeatType seatType;

    @ManyToOne
    @JoinColumn(name = "hall_id")
    private Hall hall;

    private boolean isActive;
    private Integer seatPrice;
    @OneToMany(mappedBy = "seat")
    private List<ReservedSeat> reservedSeats;

    public enum SeatType {
        VIP,
        Premium,
        Normal
    }
}

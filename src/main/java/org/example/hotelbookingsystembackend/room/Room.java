package org.example.hotelbookingsystembackend.room;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.hotelbookingsystembackend.hotel.Hotel;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;
    private String roomNumber;
    private int numberOfBeds;
    private Double roomPrice;
    @Column(nullable = false, updatable = false)
    private LocalDateTime created;
    @Column(nullable = false)
    private LocalDateTime updated;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;
}

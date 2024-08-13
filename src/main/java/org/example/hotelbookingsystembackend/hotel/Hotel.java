package org.example.hotelbookingsystembackend.hotel;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.hotelbookingsystembackend.room.Room;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String name;
    private String street;
    private Double streetNumber;
    private String city;
    private String zip;
    @Enumerated(EnumType.STRING)
    private Country country;
    private LocalDateTime created;
    private LocalDateTime updated;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Room> rooms = new ArrayList<>();
}

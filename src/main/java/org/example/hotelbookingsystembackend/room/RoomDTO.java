package org.example.hotelbookingsystembackend.room;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class RoomDTO {
    private Long id;
    private Long hotelId;
    private String roomNumber;
    private int numberOfBeds;
    private Double roomPrice;
    private LocalDateTime created;
    private LocalDateTime updated;

    public RoomDTO(Long hotelId, String roomNumber, int numberOfBeds, Double roomPrice) {
        this.hotelId = hotelId;
        this.roomNumber = roomNumber;
        this.numberOfBeds = numberOfBeds;
        this.roomPrice = roomPrice;
    }
}

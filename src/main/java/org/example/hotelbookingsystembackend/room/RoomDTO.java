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
    private String roomNumber;
    private int numberOfBeds;
    private Double roomPrice;
    private LocalDateTime created;
    private LocalDateTime updated;
}

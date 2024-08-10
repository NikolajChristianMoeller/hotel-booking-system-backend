package org.example.hotelbookingsystembackend.reservation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class ResReservationDTO {
    private long id;
    private String guestName;
    private int roomNumber;
    private LocalDate reservationDate;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;

}

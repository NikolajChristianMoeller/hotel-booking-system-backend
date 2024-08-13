package org.example.hotelbookingsystembackend.reservation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class ReservationDTO {
    private Long id;
    private Long guestId;
    private Long roomId;
    private LocalDate reservationDate;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;

}

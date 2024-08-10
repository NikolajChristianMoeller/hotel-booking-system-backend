package org.example.hotelbookingsystembackend.hotel;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class HotelDTO {

        private Long id;
        private String name;
        private String street;
        private Double streetNumber;
        @Enumerated(EnumType.STRING)
        private City city;
        private int zip;
        @Enumerated(EnumType.STRING)
        private Country country;
        private LocalDateTime created;
        private LocalDateTime updated;


}

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
        private String address;
        private String city;
        private String zip;
        private String country;
        private LocalDateTime created;
        private LocalDateTime updated;
        private int numberOfRooms;

        public HotelDTO(String name, String address, String city, String zip, String country) {
                this.name = name;
                this.address = address;
                this.city = city;
                this.zip = zip;
                this.country = country;
        }
}

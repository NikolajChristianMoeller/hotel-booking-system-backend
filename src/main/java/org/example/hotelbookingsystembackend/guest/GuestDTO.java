package org.example.hotelbookingsystembackend.guest;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class GuestDTO {

        private Long id;
        private String userName;
        private String fullName;
        private String email;
        private String phoneNumber;
        private LocalDateTime created;
        private LocalDateTime updated;
}

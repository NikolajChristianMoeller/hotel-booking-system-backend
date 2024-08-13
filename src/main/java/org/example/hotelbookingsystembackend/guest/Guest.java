package org.example.hotelbookingsystembackend.guest;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Guest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String userName;
    private String fullName;
    private String email;
    private String phoneNumber;
    private LocalDateTime created;
    private LocalDateTime updated;
}

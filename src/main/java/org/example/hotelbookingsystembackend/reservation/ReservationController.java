package org.example.hotelbookingsystembackend.reservation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {
    ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public ResponseEntity<List<ReservationDTO>> getAllReservations () {
        return ResponseEntity.ok(reservationService.getAllReservations());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationDTO> getReservationById(@PathVariable Long id) {
        return ResponseEntity.of(reservationService.getReservationById(id));
    }

    @PostMapping
    public ResponseEntity<ReservationDTO> createReservation (@RequestBody ReservationDTO ReservationDTO) {
        return ResponseEntity.status(201).body(reservationService.createReservation(ReservationDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservationDTO> updateReservation (@PathVariable Long id, @RequestBody ReservationDTO ReservationDTO) {
        return ResponseEntity.ok(reservationService.updateReservation(id, ReservationDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ReservationDTO> deleteReservation (@PathVariable Long id) {
        return ResponseEntity.ok(reservationService.deleteReservation(id));
    }

}

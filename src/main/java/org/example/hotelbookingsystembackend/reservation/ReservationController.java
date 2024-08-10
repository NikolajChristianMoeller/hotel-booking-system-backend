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
    public ResponseEntity<List<ResReservationDTO>> getAllReservations () {
        return ResponseEntity.ok(reservationService.getAllReservations());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResReservationDTO> getReservationById(@PathVariable Long id) {
        return ResponseEntity.of(reservationService.getReservationById(id));
    }

    @PostMapping
    public ResponseEntity<ResReservationDTO> createReservation (@RequestBody ReqReservationDTO reqReservationDTO) {
        return ResponseEntity.status(201).body(reservationService.createReservation(reqReservationDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResReservationDTO> updateReservation (@PathVariable Long id, @RequestBody ReqReservationDTO reqReservationDTO) {
        return ResponseEntity.ok(reservationService.updateReservation(id, reqReservationDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResReservationDTO> deleteReservation (@PathVariable Long id) {
        return ResponseEntity.ok(reservationService.deleteReservation(id));
    }

}

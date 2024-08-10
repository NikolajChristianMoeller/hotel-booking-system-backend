package org.example.hotelbookingsystembackend.guest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/guests")
public class GuestController {

    GuestService guestService;

    public GuestController(GuestService guestService) {
        this.guestService = guestService;
    }

    @GetMapping
    public ResponseEntity<List<GuestDTO>> getAllGuests () {
        return ResponseEntity.ok(guestService.getAllGuests());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GuestDTO> getGuestById(@PathVariable Long id) {
        return  ResponseEntity.of(guestService.getGuestById(id));
    }

    @PostMapping
    public ResponseEntity<GuestDTO> createGuest (@RequestBody GuestDTO guestDTO) {
        return ResponseEntity.status(201).body(guestService.createGuest(guestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GuestDTO> updateGuest (@PathVariable Long id, @RequestBody GuestDTO guestDTO) {
        return ResponseEntity.ok(guestService.updateGuest(id, guestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GuestDTO> deleteGuest (@PathVariable Long id) {
        return ResponseEntity.ok(guestService.deleteGuest(id));
    }
}

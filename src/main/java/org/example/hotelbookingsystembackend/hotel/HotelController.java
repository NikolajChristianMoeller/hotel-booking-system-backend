package org.example.hotelbookingsystembackend.hotel;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping
    public ResponseEntity<List<HotelDTO>> getAllHotels() {
        return ResponseEntity.ok(hotelService.getAllHotels());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HotelDTO> getHotelById(@PathVariable Long id) {
        return  ResponseEntity.of(hotelService.getHotelById(id));
    }

    @PostMapping
    public ResponseEntity<HotelDTO> createHotel (@RequestBody HotelDTO hotelDTO) {
        return ResponseEntity.status(201).body(hotelService.createHotel(hotelDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HotelDTO> updateHotel (@PathVariable Long id, @RequestBody HotelDTO hotelDTO) {
        return ResponseEntity.ok(hotelService.updateHotel(id, hotelDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HotelDTO> deleteHotel (@PathVariable Long id) {
        return ResponseEntity.ok(hotelService.deleteHotel(id));
    }
}

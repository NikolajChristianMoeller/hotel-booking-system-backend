package org.example.hotelbookingsystembackend.hotel;

import org.example.hotelbookingsystembackend.errorhandling.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HotelService {
    HotelRepository hotelRepository;

    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    private HotelDTO toDTO(Hotel hotel) {
        HotelDTO hotelDTO = new HotelDTO();
        hotelDTO.setId(hotel.getId());
        hotelDTO.setName(hotel.getName());
        hotelDTO.setAddress(hotel.getAddress());
        hotelDTO.setCity(hotel.getCity());
        hotelDTO.setZip(hotel.getZip());
        hotelDTO.setCountry(hotel.getCountry());
        hotelDTO.setCreated(hotel.getCreated());
        hotelDTO.setUpdated(hotel.getUpdated());
        return hotelDTO;
    }

    public List<HotelDTO> getAllHotels() {
        return hotelRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public Optional<HotelDTO> getHotelById(Long id) {
        if (id == null || id < 0) {
            throw new NotFoundException("Id must be provided");
        }

        Optional<Hotel> hotelOptional = hotelRepository.findById(id);

        if (hotelOptional.isEmpty()) {
            throw new NotFoundException("Hotel not found, provided id: " + id);
        }

        return hotelOptional.map(this::toDTO);
    }

    public HotelDTO createHotel(HotelDTO hotelDTO) {
        Hotel hotel = new Hotel();
        hotel.setName(hotelDTO.getName());
        hotel.setAddress(hotelDTO.getAddress());
        hotel.setCity(hotelDTO.getCity());
        hotel.setZip(hotelDTO.getZip());
        hotel.setCountry(hotelDTO.getCountry());
        hotel.setCreated(hotelDTO.getCreated());
        hotel.setUpdated(hotelDTO.getUpdated());
        return toDTO(hotelRepository.save(hotel));
    }

    public HotelDTO updateHotel(Long id, HotelDTO hotelDTO) {
        return hotelRepository.findById(id).map(hotel -> {
            hotel.setName(hotelDTO.getName());
            hotel.setAddress(hotelDTO.getAddress());
            hotel.setCity(hotelDTO.getCity());
            hotel.setZip(hotelDTO.getZip());
            hotel.setCountry(hotelDTO.getCountry());
            hotel.setUpdated(hotelDTO.getUpdated());
            return toDTO(hotelRepository.save(hotel));
        }).orElse(null);
    }

    public HotelDTO deleteHotel(Long id) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Hotel not found, provided id: " + id));
        HotelDTO hotelDTO = toDTO(hotel);
        hotelRepository.deleteById(id);
        return hotelDTO;
    }
}

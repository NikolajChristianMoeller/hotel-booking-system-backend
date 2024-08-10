package org.example.hotelbookingsystembackend.guest;

import org.example.hotelbookingsystembackend.errorhandling.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GuestService {
    GuestRepository guestRepository;

    public GuestService(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    private GuestDTO toDTO(Guest guest) {
        GuestDTO guestDTO = new GuestDTO();
        guestDTO.setId(guest.getId());
        guestDTO.setUserName(guest.getUserName());
        guestDTO.setFullName(guest.getFullName());
        guestDTO.setEmail(guest.getEmail());
        guestDTO.setPhoneNumber(guest.getPhoneNumber());
        guestDTO.setCreated(guest.getCreated());
        guestDTO.setUpdated(guest.getUpdated());
        return guestDTO;
    }

    public List<GuestDTO> getAllGuests() {
        return guestRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public Optional<GuestDTO> getGuestById(Long id) {
        if (id == null || id < 0) {
            throw new NotFoundException("Id must be provided");
        }

        Optional<Guest> guestOptional = guestRepository.findById(id);

        if (guestOptional.isEmpty()) {
            throw new NotFoundException("Guest not found, provided id: " + id);
        }

        return guestOptional.map(this::toDTO);
    }

    public GuestDTO createGuest(GuestDTO guestDTO) {
        Guest guest = new Guest();
        guest.setUserName(guestDTO.getUserName());
        guest.setFullName(guestDTO.getFullName());
        guest.setEmail(guestDTO.getEmail());
        guest.setPhoneNumber(guestDTO.getPhoneNumber());
        guest.setCreated(LocalDateTime.now());
        guest.setUpdated(LocalDateTime.now());
        return toDTO(guestRepository.save(guest));
    }

    public GuestDTO updateGuest(Long id, GuestDTO guestDTO) {
        return guestRepository.findById(id).map(guest -> {
            guest.setUserName(guestDTO.getUserName());
            guest.setFullName(guestDTO.getFullName());
            guest.setEmail(guestDTO.getEmail());
            guest.setPhoneNumber(guestDTO.getPhoneNumber());
            guest.setCreated(LocalDateTime.now());
            guest.setUpdated(LocalDateTime.now());
            return toDTO(guestRepository.save(guest));
        }).orElse(null);
    }

    public GuestDTO deleteGuest(Long id) {
        Guest guest = guestRepository.findById(id).orElseThrow(() -> new NotFoundException("Guest not found, provided id: " + id));
        GuestDTO guestDTO = toDTO(guest);
        guestRepository.deleteById(id);
        return guestDTO;
    }

}

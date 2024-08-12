package org.example.hotelbookingsystembackend.reservation;

import org.example.hotelbookingsystembackend.errorhandling.exception.NotFoundException;
import org.example.hotelbookingsystembackend.errorhandling.exception.ValidationException;
import org.example.hotelbookingsystembackend.guest.Guest;
import org.example.hotelbookingsystembackend.guest.GuestRepository;
import org.example.hotelbookingsystembackend.room.Room;
import org.example.hotelbookingsystembackend.room.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservationService {
    ReservationRepository reservationRepository;
    GuestRepository guestRepository;
    RoomRepository roomRepository;

    public ReservationService(ReservationRepository reservationRepository, GuestRepository guestRepository, RoomRepository roomRepository) {
        this.reservationRepository = reservationRepository;
        this.guestRepository = guestRepository;
        this.roomRepository = roomRepository;
    }

    public ReservationDTO toDTO(Reservation reservation) {
        ReservationDTO resReservationDTO = new ReservationDTO();
        resReservationDTO.setId(reservation.getId());
        resReservationDTO.setGuestId(reservation.getGuest().getId());
        resReservationDTO.setRoomId(reservation.getRoom().getId());
        resReservationDTO.setReservationDate(reservation.getReservationDate());
        resReservationDTO.setCheckInDate(reservation.getCheckInDate());
        resReservationDTO.setCheckOutDate(reservation.getCheckOutDate());

        return resReservationDTO;
    }

    public List<ReservationDTO> getAllReservations() {
        return reservationRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public Optional<ReservationDTO> getReservationById(Long id) {
        if (id == null || id < 0) {
            throw new ValidationException("Id must be provided");
        }

        Optional<Reservation> reservationOptional = reservationRepository.findById(id);

        if (reservationOptional.isEmpty()) {
            throw new NotFoundException("Reservation not found, provided id: " + id);
        }

        return reservationOptional.map(this::toDTO);
    }

    public ReservationDTO createReservation(ReservationDTO ReservationDTO) {
        Reservation reservation = new Reservation();

        if (ReservationDTO == null) {
            throw new ValidationException("Request body cannot be null");
        }

        Guest guest = guestRepository.findById(ReservationDTO.getGuestId())
            .orElseThrow(() -> new NotFoundException("GuestNotFount"));

        Room room = roomRepository.findById(ReservationDTO.getRoomId())
            .orElseThrow(() -> new NotFoundException("Room not found "));

        reservation.setGuest(guest);
        reservation.setRoom(room);

        reservationRepository.save(reservation);

        return toDTO(reservation);

        /*
        {
            "guestId": 1,
            "roomId": 3,
            "LocalDate": '2023-04-04,
            "LocalDate": "2023-04-04",
            "LocalDate": "2023-04-04"
        }
        * */

    }

    public ReservationDTO updateReservation(Long id, ReservationDTO ReservationDTO) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Reservation not found, provided id: " + id));

        reservation.setReservationDate(ReservationDTO.getReservationDate());
        reservation.setCheckInDate(ReservationDTO.getCheckInDate());
        reservation.setCheckOutDate(ReservationDTO.getCheckOutDate());

        Reservation savedReservation;
        try {
            savedReservation = reservationRepository.save(reservation);
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while saving the reservation", e);
        }
        return toDTO(savedReservation);
    }

    public ReservationDTO deleteReservation(Long id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Reservation not found, provided id: " + id));
        reservationRepository.delete(reservation);
        return toDTO(reservation);
    }

}

package org.example.hotelbookingsystembackend.reservation;

import org.example.hotelbookingsystembackend.errorhandling.exception.NotFoundException;
import org.example.hotelbookingsystembackend.errorhandling.exception.ValidationException;
import org.example.hotelbookingsystembackend.guest.GuestRepository;
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

    public ResReservationDTO toDTO(Reservation reservation) {
        ResReservationDTO resReservationDTO = new ResReservationDTO();
        resReservationDTO.setId(reservation.getId());
        resReservationDTO.setGuestName(reservation.getGuest().getFullName());
        resReservationDTO.setRoomNumber(reservation.getRoom().getRoomNumber());
        resReservationDTO.setReservationDate(reservation.getReservationDate());
        resReservationDTO.setCheckInDate(reservation.getCheckInDate());
        resReservationDTO.setCheckOutDate(reservation.getCheckOutDate());

        return resReservationDTO;
    }

    public List<ResReservationDTO> getAllReservations() {
        return reservationRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public Optional<ResReservationDTO> getReservationById(Long id) {
        if (id == null || id < 0) {
            throw new ValidationException("Id must be provided");
        }

        Optional<Reservation> reservationOptional = reservationRepository.findById(id);

        if (reservationOptional.isEmpty()) {
            throw new NotFoundException("Reservation not found, provided id: " + id);
        }

        return reservationOptional.map(this::toDTO);
    }

    public ResReservationDTO createReservation(ReqReservationDTO reqReservationDTO) {
        Reservation reservation = new Reservation();
        reservation.setReservationDate(reqReservationDTO.getReservationDate());
        reservation.setCheckInDate(reqReservationDTO.getCheckInDate());
        reservation.setCheckOutDate(reqReservationDTO.getCheckOutDate());

        Reservation savedReservation;

        try {
            savedReservation = reservationRepository.save(reservation);
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while saving the reservation", e);
        }
        return toDTO(savedReservation);
    }

    public ResReservationDTO updateReservation(Long id, ReqReservationDTO reqReservationDTO) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Reservation not found, provided id: " + id));

        reservation.setReservationDate(reqReservationDTO.getReservationDate());
        reservation.setCheckInDate(reqReservationDTO.getCheckInDate());
        reservation.setCheckOutDate(reqReservationDTO.getCheckOutDate());

        Reservation savedReservation;
        try {
            savedReservation = reservationRepository.save(reservation);
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while saving the reservation", e);
        }
        return toDTO(savedReservation);
    }

    public ResReservationDTO deleteReservation(Long id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Reservation not found, provided id: " + id));
        reservationRepository.delete(reservation);
        return toDTO(reservation);
    }

}

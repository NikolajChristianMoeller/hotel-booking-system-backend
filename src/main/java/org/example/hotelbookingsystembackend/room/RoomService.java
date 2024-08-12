package org.example.hotelbookingsystembackend.room;

import org.example.hotelbookingsystembackend.errorhandling.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoomService {
    RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    private RoomDTO toDTO(Room room) {
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setId(room.getId());
        roomDTO.setRoomNumber(room.getRoomNumber());
        roomDTO.setNumberOfBeds(room.getNumberOfBeds());
        roomDTO.setRoomPrice(room.getRoomPrice());
        roomDTO.setCreated(room.getCreated());
        roomDTO.setUpdated(room.getUpdated());
        return roomDTO;
    }

    public List<RoomDTO> getAllRooms() {
        return roomRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public Optional<RoomDTO> getRoomById(Long id) {
        if (id == null || id < 0) {
            throw new NotFoundException("Id must be provided");
        }

        Optional<Room> roomOptional = roomRepository.findById(id);

        if (roomOptional.isEmpty()) {
            throw new NotFoundException("Room not found, provided id: " + id);
        }

        return roomOptional.map(this::toDTO);
    }

    public RoomDTO createRoom(RoomDTO roomDTO) {
        Room room = new Room();
        room.setRoomNumber(roomDTO.getRoomNumber());
        room.setNumberOfBeds(roomDTO.getNumberOfBeds());
        room.setRoomPrice(roomDTO.getRoomPrice());
        room.setCreated(roomDTO.getCreated());
        room.setUpdated(roomDTO.getUpdated());
        return toDTO(roomRepository.save(room));
    }

    public RoomDTO updateRoom(Long id, RoomDTO roomDTO) {
        return roomRepository.findById(id).map(room -> {
            room.setRoomNumber(roomDTO.getRoomNumber());
            room.setNumberOfBeds(roomDTO.getNumberOfBeds());
            room.setRoomPrice(roomDTO.getRoomPrice());
            room.setCreated(roomDTO.getCreated());
            room.setUpdated(roomDTO.getUpdated());
            return toDTO(roomRepository.save(room));
        }).orElse(null);
    }

    public RoomDTO deleteRoom(Long id) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Room not found, provided id: " + id));
        RoomDTO roomDTO = toDTO(room);
        roomRepository.deleteById(id);
        return roomDTO;
    }

}
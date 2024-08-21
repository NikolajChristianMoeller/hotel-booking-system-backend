package org.example.hotelbookingsystembackend.room;

import org.example.hotelbookingsystembackend.errorhandling.exception.NotFoundException;
import org.example.hotelbookingsystembackend.hotel.Hotel;
import org.example.hotelbookingsystembackend.hotel.HotelDTO;
import org.example.hotelbookingsystembackend.hotel.HotelRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoomService {
    private final HotelRepository hotelRepository;
    RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository, HotelRepository hotelRepository) {
        this.roomRepository = roomRepository;
        this.hotelRepository = hotelRepository;
    }

    private RoomDTO toDTO(Room room) {
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setId(room.getId());
        roomDTO.setHotelId(room.getHotel().getId());
        roomDTO.setRoomNumber(room.getRoomNumber());
        roomDTO.setNumberOfBeds(room.getNumberOfBeds());
        roomDTO.setRoomPrice(room.getRoomPrice());
        // created og updated skal kun sættes på DTO'en fordi datoen kun skal vises
        roomDTO.setCreated(room.getCreated());
        roomDTO.setUpdated(room.getUpdated());
        return roomDTO;
    }

    private Room toEntity(RoomDTO roomDTO) {
        Room room = new Room();
        room.setId(roomDTO.getId());
        Hotel hotel = hotelRepository.findById(roomDTO.getHotelId()).orElse(null);
        room.setHotel(hotel);
        room.setRoomNumber(roomDTO.getRoomNumber());
        room.setNumberOfBeds(roomDTO.getNumberOfBeds());
        room.setRoomPrice(roomDTO.getRoomPrice());
        return room;
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
        Hotel hotel = hotelRepository.findById(roomDTO.getHotelId())
                .orElseThrow(() -> new NotFoundException("Hotel not found, provided id: " + roomDTO.getHotelId()));

        Room room = roomRepository.save(toEntity(roomDTO));
        hotel.getRooms().add(room);
        hotelRepository.save(hotel);

        return toDTO(room);
    }

    public List<RoomDTO> createRoomsInHotel(List<RoomDTO> roomDTOs, HotelDTO hotelDTO) {
        Hotel hotel = hotelRepository.findById(hotelDTO.getId())
                .orElseThrow(() -> new NotFoundException("Hotel not found, provided id: " + hotelDTO.getId()));
        // Går igennem listen af RoomDTOer, hvert RoomDTO skal laves til en room entity

        List<Room> rooms = roomDTOs.stream().map(this::toEntity).toList();

        return roomRepository.saveAll(rooms).stream().map(this::toDTO).toList();
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

package org.example.hotelbookingsystembackend.configuration;

import org.example.hotelbookingsystembackend.hotel.HotelDTO;
import org.example.hotelbookingsystembackend.hotel.HotelService;
import org.example.hotelbookingsystembackend.room.RoomDTO;
import org.example.hotelbookingsystembackend.room.RoomRepository;
import org.example.hotelbookingsystembackend.room.RoomService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class InitData implements CommandLineRunner {
    private final HotelService hotelService;
    private final RoomService roomService;
    private final RoomRepository roomRepository;

    public InitData(HotelService hotelService, RoomService roomService, RoomRepository roomRepository) {
        this.hotelService = hotelService;
        this.roomService = roomService;
        this.roomRepository = roomRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (hotelService.getAllHotels().isEmpty()) {
            System.out.println("Creating hotels and rooms");
            for (int i = 1; i <= 250; i++) {
                //System.out.println("Creating hotel " + i);
                HotelDTO hotelDTO = createHotel(i);
                int numberOfRooms = (int) (Math.random() * 30) + 10;
                //System.out.println("Creating " + numberOfRooms + " rooms in hotel " + hotelDTO.getName());
                createRoomsInHotel(numberOfRooms, hotelDTO);
            }
            System.out.println("Done creating hotels and rooms");
        } else {
            System.out.println("Hotels and rooms already exist");
        }
    }

    private HotelDTO createHotel(int number){
        HotelDTO hotel = new HotelDTO(
                "Hotel " + number,
                "Address " + number,
                "City " + number,
                "Zip " + number,
                "DANMARK"
        );

        HotelDTO nyDTO = hotelService.createHotel(hotel);
        return nyDTO;
    }

    public void createRoom(int number, HotelDTO hotel){
        int numberOfBeds = (int) (Math.random() * 4) + 1;
        RoomDTO room = new RoomDTO(
                hotel.getId(),
                "Room " + number,
                numberOfBeds,
                100.0

        );
        roomService.createRoom(room);

    }

    public void createRoomsInHotel(int numberOfRooms, HotelDTO hotel){
        for (int number = 1; number <= numberOfRooms; number++) {
            int numberOfBeds = (int) (Math.random() * 4) + 1;
            RoomDTO room = new RoomDTO(
                    hotel.getId(),
                    "Room " + number,
                    numberOfBeds,
                    100.0
            );
            roomService.createRoom(room);
        }
    }
}

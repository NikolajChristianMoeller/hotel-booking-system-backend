package org.example.hotelbookingsystembackend.configuration;

import org.example.hotelbookingsystembackend.hotel.Country;
import org.example.hotelbookingsystembackend.hotel.HotelDTO;
import org.example.hotelbookingsystembackend.hotel.HotelService;
import org.example.hotelbookingsystembackend.room.RoomDTO;
import org.example.hotelbookingsystembackend.room.RoomService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitData implements CommandLineRunner {
    private final HotelService hotelService;
    private final RoomService roomService;

    public InitData(HotelService hotelService, RoomService roomService) {
        this.hotelService = hotelService;
        this.roomService = roomService;
    }

    @Override
    public void run(String... args) throws Exception {
        for (int i = 1; i <= 250; i++) {
            System.out.println("Creating hotel " + i);
            HotelDTO hotelDTO = createHotel(i);
            int numberOfRooms = (int) (Math.random() * 30) + 10;
            System.out.println("Creating " + numberOfRooms + " rooms in hotel " + hotelDTO.getName());
            createRoomsInHotel(numberOfRooms, hotelDTO);
        }
        System.out.println("Done creating hotels and rooms");
    }

    private HotelDTO createHotel(int number){
        HotelDTO hotel = new HotelDTO(
                "Hotel " + number,
                "Address " + number,
                "City " + number,
                "Zip " + number,
                Country.Denmark
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
        String bedWord = numberOfBeds > 1 ? "beds" : "bed";

        System.out.println("Created " + room.getRoomNumber() + " in " + hotel.getName() +
                " with " + numberOfBeds + " "+ bedWord + ".");
        roomService.createRoom(room);
    }

    public void createRoomsInHotel(int numberOfRooms, HotelDTO hotel){
        for (int i = 1; i <= numberOfRooms; i++) {
            createRoom(i, hotel);
        }
    }

}

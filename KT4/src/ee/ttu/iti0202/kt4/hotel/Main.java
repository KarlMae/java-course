package ee.ttu.iti0202.kt4.hotel;

import ee.ttu.iti0202.kt4.hotel.room.Room;
import ee.ttu.iti0202.kt4.hotel.room.RoomBuilder;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {


        // No time for input reading


        Hotel hotel = new Hotel();
        System.out.println("Money in hotel in the beginning: " + hotel.getMoney());
        System.out.println("\n");

        // Book room, no rooms present
        System.out.println("Book room, no rooms present");
        try {
            hotel.bookRoom(Room.RoomType.PARTY, 2, 4);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        System.out.println("\n");

        // Add rooms
        try {
            hotel.addRoom(new RoomBuilder(hotel).setID(1).setRoomType(Room.RoomType.PARTY).createRoom());
            hotel.addRoom(new RoomBuilder(hotel).setID(2).setRoomType(Room.RoomType.ROMANCE).createRoom());
            hotel.addRoom(new RoomBuilder(hotel).setID(3).setRoomType(Room.RoomType.NORMAL).createRoom());
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        System.out.println("Added rooms");
        System.out.println(hotel.getRooms());
        System.out.println("\n");

        // Book room
        try {
            hotel.bookRoom(Room.RoomType.PARTY, 2, 4);
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        System.out.println("Booked room");
        System.out.println(hotel.getRooms());
        System.out.println("Money in hotel: " + hotel.getMoney() + "\n");

        // Can't book room
        System.out.println("Can't book room, already taken");
        try {
            hotel.bookRoom(Room.RoomType.PARTY, 4, 4);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        System.out.println("\n");


        // Add new room same type
        try {
            hotel.addRoom(new RoomBuilder(hotel).setID(10).setRoomType(Room.RoomType.PARTY).createRoom());
        } catch (Exception e) {
            System.out.println(e.toString());
        }


        // Now can book room (1 day)
        System.out.println("Added new room, booking successful");
        try {
            hotel.bookRoom(Room.RoomType.PARTY, 4, 4);
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        System.out.println(hotel.getRooms());
        System.out.println("Money in hotel: " + hotel.getMoney());
        System.out.println("\n");


        System.out.println("Trying to add bad rooms");
        // Can't add room ID = 0
        try {
            hotel.addRoom(new RoomBuilder(hotel).setID(0).setRoomType(Room.RoomType.PARTY).createRoom());
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        // Can't add room ID already exists
        try {
            hotel.addRoom(new RoomBuilder(hotel).setID(1).setRoomType(Room.RoomType.PARTY).createRoom());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}

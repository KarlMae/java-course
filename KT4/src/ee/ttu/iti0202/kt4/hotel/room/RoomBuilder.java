package ee.ttu.iti0202.kt4.hotel.room;

import ee.ttu.iti0202.kt4.hotel.Hotel;
import ee.ttu.iti0202.kt4.hotel.exceptions.InvalidRoomIDException;
import ee.ttu.iti0202.kt4.hotel.exceptions.RoomIDInUseException;

public class RoomBuilder {
    private int id;
    private Room.RoomType roomType;
    private Hotel hotel;

    public RoomBuilder(Hotel hotel) {
        this.hotel = hotel;
    }

    public RoomBuilder setID(int id) throws Exception {

        if (id <= 0) throw new InvalidRoomIDException();

        for (Room room : hotel.getRooms()) {
            if (room.getID() == id) throw new RoomIDInUseException();
        }

        this.id = id;
        return this;
    }

    public RoomBuilder setRoomType(Room.RoomType roomType) {
        this.roomType = roomType;
        return this;
    }

    public Room createRoom() {
        return new Room(id, roomType);
    }
}
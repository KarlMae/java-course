package ee.ttu.iti0202.kt4.hotel;

import ee.ttu.iti0202.kt4.hotel.exceptions.NoRoomsAvailableException;
import ee.ttu.iti0202.kt4.hotel.room.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Hotel {

    private int money;
    private List<Room> rooms = new ArrayList<>();

    public List<Room> getRooms() {
        return rooms;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public int getMoney() {
        return money;
    }

    public boolean bookRoom(Room.RoomType roomType, int startTime, int endTime) throws NoRoomsAvailableException {

        List<Integer> timesToBook = new ArrayList<>();
        Room availableRoom = null;

        for (int i = startTime; i <= endTime; i++) {
            timesToBook.add(i);
        }

        for (Room room : rooms.stream().filter(room -> room.getRoomType().equals(roomType)).collect(Collectors.toList())) {
            if (room.getBookedTimes()
                    .stream()
                    .filter(timesToBook::contains)
                    .collect(Collectors.toList()).isEmpty()) {
                availableRoom = room;
                break;
            }
        }

        if (availableRoom == null) throw new NoRoomsAvailableException();

        availableRoom.addBookedTimes(timesToBook);
        money += availableRoom.getPrice() * timesToBook.size();
        return true;
    }


}

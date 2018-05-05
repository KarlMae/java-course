package ee.ttu.iti0202.kt4.hotel.room;

import java.util.ArrayList;
import java.util.List;

public class Room {

    private int ID;

    public enum RoomType { PARTY, ROMANCE, NORMAL }
    private RoomType roomType;

    private List<Integer> bookedTimes = new ArrayList<>();

    Room(int ID, RoomType roomType) {
        this.ID = ID;
        this.roomType = roomType;
    }

    public int getID() {
        return ID;
    }

    public int getPrice() {
        int price = 100;

        if (roomType == RoomType.NORMAL) return price;
        if (roomType == RoomType.PARTY) return (int) (price * 1.5);
        if (roomType == RoomType.ROMANCE) return (int) (price * 2);

        return price;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public List<Integer> getBookedTimes() {
        return bookedTimes;
    }

    public void addBookedTimes(List<Integer> bookedTimes) {
        this.bookedTimes.addAll(bookedTimes);
    }

    @Override
    public String toString() {
        return "Room{" +
                "ID=" + ID +
                ", roomType=" + roomType +
                ", bookedTimes=" + bookedTimes +
                '}';
    }
}

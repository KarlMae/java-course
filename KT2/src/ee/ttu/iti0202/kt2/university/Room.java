package ee.ttu.iti0202.kt2.university;

import java.util.ArrayList;
import java.util.List;

public class Room {

    private String name;
    private List<Integer> bookHour = new ArrayList<>();

    public Room(String name) {
        this.name = name;
    }

    public int bookRoom(int hour) {
       bookHour.add(int);
    }

    public boolean getBooked(int hour) {
        return bookHour.contains(hour);
    }

    @Override
    public String toString() {
        return name;
    }
}

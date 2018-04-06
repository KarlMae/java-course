package ee.ttu.iti0202.university;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class University {

    private List<Room> universityRooms = new ArrayList<>();

    public University(){

    }

    public Optional createRoom(String name){
        if (name.equals("")) {
            return Optional.empty();
        } else {
            Room room = new Room(name);
            universityRooms.add(room);

            Optional<Room> roomOptional = Optional.of(room);

            return roomOptional;
        }
    }


    public void addRoom(Room room){
        universityRooms.add(room);
    }

    public List<Room> getRooms(){
        return universityRooms;
    }

    public List<Room> getFreeRooms(int slot) {
        return universityRooms;
    }

    public boolean bookARoom(Room room, int slot) {
        return false;
    }



}

package ee.ttu.iti0202.kt2.university;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class University {

    private List<Room> universityRooms = new ArrayList<>();

    public University(){

    }

    public Optional<Room> createRoom(String name){

        Optional<Room> roomOptional = Optional.empty();
        if (!name.equals("")) {
            Room room = new Room(name);
            universityRooms.add(room);
            roomOptional = Optional.of(room);
        }

        return roomOptional;
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

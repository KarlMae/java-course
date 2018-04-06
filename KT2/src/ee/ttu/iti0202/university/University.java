package ee.ttu.iti0202.university;

import java.util.List;
import java.util.Optional;

public class University {

    List<Room> rooms;
    Optional<Room> optional;

    public University(){

    }

    public Optional createRoom(String name){
        return optional;
    }

    public void addRoom(Room room){

    }

    public List<Room> getRooms(){
        return rooms;
    }

    public List<Room> getFreeRooms(int slot) {
        return rooms;
    }

    public boolean bookARoom(Room room, int slot) {
        return false;
    }



}

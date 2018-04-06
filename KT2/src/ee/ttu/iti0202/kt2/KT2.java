package ee.ttu.iti0202.kt2;

import ee.ttu.iti0202.university.Room;
import ee.ttu.iti0202.university.University;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class KT2 {

    public int centeredAverage(List<Integer> nums) {

        int sum = 0;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < nums.size(); i++) {
            int number = nums.get(i);
            if (number < min) min = number;
            if (number > max) max = number;
            sum += number;
        }

        return (sum - min - max) / (nums.size() - 2);
    }

    public String zipZap(String str) {
        String outputString = "";

        for (int i = 0; i < str.length(); i++) {
            if (i == str.length() - 2) {
                outputString = outputString.concat(str.substring(str.length() - 2, str.length()));
                break;
            }

            if (str.charAt(i) == 'z' && str.charAt(i + 2) == 'p') {
                outputString = outputString.concat("zp");
                i = i + 2;
            } else {
                outputString = outputString.concat(str.substring(i, i + 1));
            }
        }
        return outputString;
    }


    public static void main(String[] args) {
        KT2 kt2 = new KT2();
        System.out.println(kt2.zipZap("zopXzop"));


        University university = new University();
        Optional<Room> room0 = university.createRoom("");
        System.out.println(room0); // Optional.empty
        Room room1 = university.createRoom("ICT-121").get();
        Room room2 = university.createRoom("ICT-122").get();
        System.out.println(room1); // ICT-121
        System.out.println(university.getFreeRooms(1)); // [ICT-121, ICT-122]
        System.out.println(university.bookARoom(room1, 1)); // true
        System.out.println(university.bookARoom(room1, 1)); // false, already booked
        System.out.println(university.getFreeRooms(1)); // [ICT-122]
        System.out.println(university.bookARoom(room2, 1)); // true
        System.out.println(university.getFreeRooms(1)); // []
        System.out.println(university.getFreeRooms(99)); // [ICT-121, ICT-122]
        System.out.println(university.bookARoom(room2, 99)); // true
        System.out.println(university.getFreeRooms(99)); // [ICT-121]

        Room room3 = new Room("Presidential suite");
        System.out.println(university.bookARoom(room3, 2)); // false, no such room in university
        System.out.println(university.getFreeRooms(2)); // [ICT-121, ICT-122]
        System.out.println(university.getRooms()); // [ICT-121, ICT-122]
        university.addRoom(room3);
        System.out.println(university.getRooms()); // [ICT-121, ICT-122, Presidential suite]
        university.addRoom(room3);
        System.out.println(university.getRooms()); // [ICT-121, ICT-122, Presidential suite]

    }
}

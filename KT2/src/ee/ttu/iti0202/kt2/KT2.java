package ee.ttu.iti0202.kt2;

import ee.ttu.iti0202.kt2.university.Room;
import ee.ttu.iti0202.kt2.university.University;

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


    }
}

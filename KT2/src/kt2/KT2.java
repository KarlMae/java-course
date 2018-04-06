package kt2;

import java.util.Arrays;
import java.util.List;

public class KT2 {

    public int centeredAverage(List<Integer> nums){

        int sum = 0;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < nums.size(); i++) {
            int number = nums.get(i);
            if (number < min) min = number;
            if (number > max) max = number;
            sum += number;
        }

        return (sum - min - max) / nums.size() - 2;
    }

    public String zipZap(String str) {

        for (int i = 0; i < 0; i++) {

        }

        return "";
    }



    public static void main(String[] args) {
        System.out.println(centeredAverage(Arrays.asList(2, 3, 4, 5)));
    }
}

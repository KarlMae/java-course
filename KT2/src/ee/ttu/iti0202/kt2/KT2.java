package ee.ttu.iti0202.kt2;
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
        String outputString = "";

        for (int i = 0; i < str.length(); i++) {
            if (i == str.length() - 2) break;

            if (str.charAt(i) == 'z' && str.charAt(i + 2) == 'p'){
                outputString = outputString.concat("zp");
                i = i + 2;
            } else {
                outputString = outputString.concat(str.substring(i, i));
            }
        }
        return outputString;
    }



    public static void main(String[] args) {
        System.out.println("tere");

    }
}

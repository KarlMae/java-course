package ee.ttu.iti0202.rec;

import java.util.List;

public class Rec {

    public static int maxElement(List<Integer> nums) {
        int maxnum = Integer.MIN_VALUE;
        for (Integer number : nums) {
            if (number > maxnum) maxnum = number;
        }
        return maxnum;
    }


    // Miks siin nullist ei alga indeksid
    public static int maxElement(List<Integer> nums, int max) {
        if (nums.isEmpty()) return max;
        return nums.get(0) > max ? maxElement(nums.subList(1, nums.size()), nums.get(0)) :
                maxElement(nums.subList(1, nums.size()), max);
    }

    public static int maxGrowth(List<Integer> nums) {
        return checkNumber(nums, Integer.MIN_VALUE, 0, 0, 0);
    }

    private static int checkNumber(List<Integer> numbers, int lastNumber, int index, int currentSequence, int
            longestSequence) {
        if (index >= numbers.size()) {
            longestSequence = currentSequence >= longestSequence ? currentSequence : longestSequence;
            return longestSequence;
        }

        int currentNumber = numbers.get(index);
        index++;

        if (currentNumber <= lastNumber) {
            longestSequence = currentSequence >= longestSequence ? currentSequence : longestSequence;
            currentSequence = 1;

        } else {
            currentSequence++;
        }

        return checkNumber(numbers, currentNumber, index, currentSequence, longestSequence);
    }
}

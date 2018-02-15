
package ee.ttu.iti0202.string;


public class StringOperations {

    private static String removeDuplicates(String s) {
        String returnString = "";

        for (int i = 0; i < s.length(); i++) {

            if (i == s.length() - 1) {   //Last letter
                returnString = returnString.concat(s.substring(i, i + 1));
                break;
            }

            if (s.charAt(i) != s.charAt(i + 1)) {
                returnString = returnString.concat(s.substring(i, i + 1));
            }
        }
        return returnString;
    }

    private static int countDigits(String s) {
        int count = 0;

        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {   //Last letter
                count += 1;
            }
        }
        return count;
    }

    private static Boolean xyBalance(String s) {
        s = new StringBuilder(s).reverse().toString();

        for (int i = 0; i < s.length(); i++) {
            switch (s.charAt(i)) {
                case 'y':
                    return true;

                case 'x':
                    return false;

                default:
                    continue;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(removeDuplicates("aaabbbc")); // abc
        System.out.println(removeDuplicates("")); //
        System.out.println(removeDuplicates("tere")); // tere

        System.out.println(countDigits("12ab3")); // 3
        System.out.println(countDigits("abc")); // 0
        System.out.println(countDigits("1111222")); // 7

        System.out.println(xyBalance("aaxbby")); // true
        System.out.println(xyBalance("aaxbb")); // false
        System.out.println(xyBalance("yaaxbb")); // false
    }
}


package ee.ttu.iti0202.calculator;

public class Calculator {

    private enum LongerPad { LEFT, RIGHT }

    /**
     * Return a string following a naming convention.
     * [first three letters in uppercase]-[length of string][last two letters of string in lowercase]
     * If length of string is less than 3, return "ERROR".
     *
     * @param s original name
     */
    private static String convertName(String s) {
        if (s.length() < 3) {
            return "ERROR";
        } else {
            return s.substring(0, 3).toUpperCase() + "-" + Integer.toString(s.length()) + s.substring(s.length() - 2,
                    s.length()).toLowerCase();
        }
    }

    /**
     * Return an expression that sums the numbers a and b.
     * Example: a = 3, b = 7 -> "3 + 7 = 101"
     */
    private static String addition(int a, int b) {
        int sum = a + b;
        return Integer.toString(a) + " + " + Integer.toString(b) + " = " + Integer.toString(sum);
    }

    /**
     * Return an expression that subtracts b from a.
     * Example: a = 3, b = 1 -> "3 - 1 = 2"
     */
    private static String subtraction(int a, int b) {
        int sum = a - b;
        return Integer.toString(a) + " - " + Integer.toString(b) + " = " + Integer.toString(sum);
    }

    /**
     * Repeat the input string n times.
     */
    private static String repeat(String s, int n) {
        String newString = "";
        for (int i = 0; i < n; i++) {
            newString = newString.concat(s);
        }
        return newString;
    }

    /**
     * Create a line separator using "-". Width includes the decorators if it has any.
     *
     * @param width     width of the line, which includes the decorator, if it has one
     * @param decorated if True, line starts with ">" and ends with "<", if False, consists of only "-"
     *                  If decorated and width is 1, return an empty string ("").
     */
    private static String line(int width, boolean decorated) {
        if (!decorated) {
            return repeat("-", width);
        } else {
            if (width <= 1) {
                return "";
            }
            return ">" + repeat("-", width - 2) + "<";
        }
    }

    /**
     * Create a line separator using "-".
     *
     * @param width width of the line.
     */
    private static String line(int width) {
        return line(width, false);
    }

    /**
     * Center justify a string.
     * <p>
     * "a", 3, LongerPad.LEFT -> " a "
     * <p>
     * When the string does not fit exactly:
     * If LongerPad.LEFT make the left padding longer, otherwise the right padding should be longer.
     * "ab", 5, LongerPad.LEFT  -> "  ab "
     * "ab", 5, LongerPad.RIGHT -> " ab  "
     * <p>
     * If the width is smaller than the length of the string, return only the center part of the text.
     * "abcde", 2, LongerPad.LEFT  -> "bc" or "cd" (both are fine)
     * <p>
     * Return an empty string ("") if the width is negative.
     *
     * @param s     string to center
     * @param width width of the outcome
     * @param pad   left longer if LongerPad.LEFT, pad right longer if LongerPad.RIGHT
     * @return new center justified string
     */
    private static String center(String s, int width, LongerPad pad) {

        if (width < 0) {
            return "";
        }

        if (s.length() == width) {
            return s;
        }

        if (s.length() > width) {
            if (s.length() / 2 % 2 != 0) {  //  Return an even amount of letters
                return s.substring((s.length() - width) / 2, s.length() - (s.length() - width) / 2);
            } else {                        //  Return an uneven amount of letters
                return s.substring((s.length() - width) / 2, s.length() - (s.length() - width + 1) / 2);
            }
        }

        if (((width - s.length()) % 2) != 0) {    //  If padding is uneven
            int shorterPadLength = ((width - s.length()) / 2);
            int longerPadLength = ((width - s.length()) / 2) + 1;
            String shorterPad = new String(new char[shorterPadLength]).replace("\0", " ");
            String longerPad = new String(new char[longerPadLength]).replace("\0", " ");

            if (pad == LongerPad.LEFT) {
                return longerPad + s + shorterPad;
            } else {
                return shorterPad + s + longerPad;
            }

        } else { //If padding is even
            int padLength = ((width - s.length()) / 2);
            String padding = new String(new char[padLength]).replace("\0", " ");
            return padding + s + padding;
        }

    }

    /**
     * @param currentLength Set width of the calculator
     * @param textLength    Length of the text inside the calculator
     * @return The longer of the previous two
     */
    private static int setCalculatorWidth(int currentLength, int textLength) {
        if (currentLength < textLength) {
            return textLength;
        } else {
            return currentLength;
        }
    }

    /**
     * Create a string representing the display.
     * Use LongerPad.LEFT when centering.
     *
     * @param a         number
     * @param b         number
     * @param name      full name of calculator company
     * @param operation operation ("addition" or "subtraction") applied to numbers a and b
     * @param width     width of the display
     * @return string representing the final format
     */
    private static String display(int a, int b, String name, String operation, int width) {

        //  Set operation
        switch (operation) {
            case "addition":
                operation = addition(a, b);
                break;

            case "subtraction":
                operation = subtraction(a, b);
                break;

            default:
                operation = "ERROR";
                break;
        }

        width = setCalculatorWidth(width, operation.length());

        name = convertName(name);
        width = setCalculatorWidth(width, name.length());

        //  Align name to the right
        name = new String(new char[width - name.length()]).replace("\0", " ") + name;


        return name + "\n"
                + line(width, true) + "\n"
                + "|" + center(operation, width - 2, LongerPad.LEFT) + "|" + "\n"
                + line(width);
    }
}

package ee.ttu.iti0202.cb;

public class String3 {


    public int countYZ(String str) {
        Character lastChar = 'a';
        int count = 0;

        for (Character ch : str.toLowerCase().toCharArray()) {
            if ((lastChar == 'z' || lastChar == 'y') && !Character.isLetter(ch)) {
                count++;
            }
            lastChar = ch;
        }

        if ((lastChar == 'z' || lastChar == 'y')) {
            count++;
        }

        return count;
    }


    public String withoutString(String base, String remove) {
        while (base.toLowerCase().contains(remove.toLowerCase())) {
            int index = base.toLowerCase().indexOf(remove.toLowerCase());
            base = base.substring(0, index)
                    .concat(base.substring(index + remove.length(), base.length()));
        }

        return base;
    }


    public boolean equalIsNot(String str) {
        int notCount = 0;
        int isCount = 0;

        while (str.contains("not")) {
            notCount++;
            str = str.replaceFirst("not", "");
        }

        while (str.contains("is")) {
            isCount++;
            str = str.replaceFirst("is", "");
        }

        return isCount == notCount;
    }


    public boolean gHappy(String str) {
        if (str.length() == 0) return true;
        if (str.length() == 1) return str.charAt(0) != 'g';
        if (str.charAt(0) == 'g' && str.charAt(1) != 'g') return false;
        if (str.charAt(str.length() - 1) == 'g' && str.charAt(str.length() - 2) != 'g') return false;

        for (int i = 1; i < str.length() - 1; i++) {
            if (str.charAt(i) == 'g' && (str.charAt(i + 1) != 'g' && str.charAt(i - 1) != 'g')) {
                return false;
            }
        }
        return true;
    }


    public int countTriple(String str) {
        int count = 0;
        for (int i = 0; i < str.length() - 2; i++) {
            if (str.charAt(i) == str.charAt(i + 1) && str.charAt(i + 1) == str.charAt(i + 2)) {
                count++;
            }
        }
        return count;
    }


    public int sumDigits(String str) {
        int sum = 0;

        for (Character c : str.toCharArray()) {
            if (Character.isDigit(c)) sum += Character.getNumericValue(c);
        }
        return sum;
    }


    public String sameEnds(String string) {
        String returnString = "";

        for (int i = 0; i <= (string.length() / 2); i++) {
            if (string.substring(0, i).equals(string.substring(string.length() - i, string.length()))) {
                returnString = string.substring(0, i);
            }
        }

        return returnString;
    }


    public String mirrorEnds(String string) {
        int length = 0;

        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == (string.charAt(string.length() - 1 - i))) {
                length++;
            } else {
                break;
            }
        }
        return string.substring(0, length);
    }


    public int maxBlock(String str) {
        int currentLength = 1;
        int maxLength = 0;

        for (int i = 0; i < str.length() - 1; i++) {
            if (str.charAt(i) == str.charAt(i + 1)) {
                currentLength++;
            } else {
                currentLength = 1;
            }

            if (currentLength > maxLength) maxLength = currentLength;
        }

        return maxLength;
    }


    public int sumNumbers(String str) {
        int sum = 0;
        int numberStart = 0;
        boolean number = false;

        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {
                if (!number) numberStart = i;
                number = true;
            } else {
                if (number) {
                    sum += Integer.parseInt(str.substring(numberStart, i));
                }
                numberStart = 0;
                number = false;
            }
        }

        if (numberStart != 0) {
            sum += Integer.parseInt(str.substring(numberStart));
        }

        return sum;
    }


    public String notReplace(String str) {
        if (str.length() == 0) return str;
        String returnString = "";

        str = " " + str + " ";

        for (int i = 1; i < str.length(); i++) {
            if (!Character.isLetter(str.charAt(i - 1))
                    && str.charAt(i) == 'i'
                    && str.charAt(i + 1) == 's'
                    && !Character.isLetter(str.charAt(i + 2))) {
                returnString = returnString.concat("is not");
                i++;
            } else {
                returnString = returnString.concat(str.substring(i, i+1));
            }
        }

        // Remove added spaces
        if (returnString.charAt(0) == ' ') {
            returnString = returnString.substring(1);
        }

        if (returnString.charAt(returnString.length() - 1) == ' ') {
            returnString = returnString.substring(0, returnString.length() - 1);
        }

        return returnString;
    }

    public static void main(String[] args) {
        String3 string3 = new String3();
        System.out.println(string3.notReplace("is test"));
    }


}
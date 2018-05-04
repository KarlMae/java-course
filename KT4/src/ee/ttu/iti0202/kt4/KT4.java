package ee.ttu.iti0202.kt4;

import ee.ttu.iti0202.kt4.mail.Letter;
import ee.ttu.iti0202.kt4.mail.Mailbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KT4 {

    public List<Integer> zeroEnd(List<Integer> nums) {

        List<Integer> numbers = new ArrayList<>();
        int zeros = 0;

        //Remove 0's
        for (Integer i : nums) {
            if (i == 0) {
                zeros++;
            } else {
                numbers.add(i);
            }
        }

        //Add 0's
        for (int i = 0; i < zeros; i++) {
            numbers.add(0);
        }

        return numbers;
    }

    public String mixString(String a, String b) {
        String newString = "";
        int newStringLength = 0;

        newStringLength += a.length();
        newStringLength += b.length();

        for (int i = 0; i < newStringLength; i++) {
            if (i / 2 > a.length() - 1) {
                newString = newString.concat(b.substring(i/2, b.length()));
                break;
            }

            if (i / 2 > b.length() - 1) {
                newString = newString.concat(a.substring(i/2, a.length()));
                break;
            }

            newString = newString.concat(i % 2 == 0 ? Character.toString(a.charAt(i / 2)) : Character.toString(b.charAt(i / 2)));
        }

        return newString;
    }





    public static void main(String[] args) {
        KT4 kt4 = new KT4();
        System.out.println(kt4.zeroEnd(Arrays.asList(1, 2, 3, 0))); // [1, 2, 3, 0]
        System.out.println(kt4.zeroEnd(Arrays.asList(1, 0, 3, 0))); // [1, 3, 0, 0]
        System.out.println(kt4.zeroEnd(Arrays.asList(0, 0))); // [0, 0]
        System.out.println(kt4.zeroEnd(Arrays.asList(4, 8))); // [4, 8]

        System.out.println(kt4.mixString("aca", "b")); // abc


        Letter normalLetter = new Letter(12, "From grandma", "Dear ..., I hope your test will be fine.");
        Letter normalLetter2 = new Letter(12, "Warning", "Don't forget to fix that bug or our boss will be angry...");
        Letter spam = new Letter(9999, "Small pillows - 50% OFF!!!", "Don't miss your change and buy a lot of small pillows!!!");

        Mailbox mailbox = new Mailbox(12);

        mailbox.receiveLetter(normalLetter);
        mailbox.receiveLetter(normalLetter2);
        mailbox.receiveLetter(spam);

        List<Letter> normalLetters = mailbox.getNormalLetters();
        List<Letter> spamLetters = mailbox.getSpam();

        System.out.println(normalLetters.size()); // 2
        System.out.println(spamLetters.size()); // 1

        System.out.println(normalLetters.contains(normalLetter)); // true
        System.out.println(normalLetters.contains(normalLetter2)); // true
        System.out.println(normalLetters.contains(spam)); // false
        System.out.println(spamLetters.contains(spam)); // true

// Override toString to see results
        System.out.println(mailbox.findLetterByTitle("From grandma")); // Optional[Letter{mailBoxID=12, title='From grandma'}]
        System.out.println(mailbox.findLetterByTitle("abc")); // Optional.empty
        System.out.println(mailbox.findLetterByTitle("Small pillows - 50% OFF!!")); // Optional.empty
        System.out.println(mailbox.findLetterByTitle("small PILLOWS - 50% off!!!")); // Optional[Letter{mailBoxID=9999, title='Small pillows - 50% OFF!!!'}]
    }

}

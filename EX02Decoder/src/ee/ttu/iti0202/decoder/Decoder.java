package ee.ttu.iti0202.decoder;

import java.util.Scanner;

public class Decoder {

    /**
     * Method deciphers given message based on the dictionary.
     * <p>
     * The message consists of a series of numbers, which point to a letter's
     * index location in the dictionary. e.g. with a dictionary of "abcd" and
     * message of "3021" the deciphered message would be "dacb".
     * <p>
     * If the index of the letter is comprised of 2 or more numbers, the message will
     * read as those numbers separated by plus signs. e.g. 1+3 => 13 ; 1+2+3 => 123.
     * <p>
     * For every "$" symbol that appears in the message, the next
     * letter must be converted to upper case.
     * <p>
     * Any other symbols appearing in the message should be added to the
     * deciphered message untouched.
     *
     * @param dictionary - dictionary to be used.
     * @param message    - message to be deciphered.
     * @return - deciphered message.
     */
    private static String decodeMessage(String dictionary, String message) {
        Boolean upperCase = false;
        StringBuilder currentLetter = new StringBuilder();
        StringBuilder decryptedMessage = new StringBuilder();

        for (int i = 0; i < message.length(); i++) {
            if (message.charAt(i) == '$') {
                upperCase = true;
                continue;
            }

            if (i < message.length() - 1) {
                if (message.charAt(i + 1) == '+') {
                    currentLetter.append(message.substring(i, i + 1));
                    i++;
                } else {
                    currentLetter.append(message.substring(i, i + 1));
                    decryptedMessage.append(addLetter(dictionary, currentLetter.toString(), upperCase));
                    currentLetter.setLength(0); //clear buffer
                    upperCase = false;
                }
            } else {
                currentLetter.append(message.substring(i, i + 1));
                decryptedMessage.append(addLetter(dictionary, currentLetter.toString(), upperCase));
            }
        }

        return decryptedMessage.toString();
    }


    /**
     * If letterKey is in dictionary, get the corresponding letter. If not, leave the input as is.
     * If uppercase, make the letter uppercase.
     * @param dictionary - String containing letters associated with numeric keys.
     * @param letterKey - Key to extract letter from dict.
     * @param upperCase - Boolean, uppercase or not.
     * @return String containing one letter.
     */
    private static String addLetter(String dictionary, String letterKey, Boolean upperCase) {
        int key;

        try {
            key = Integer.parseInt(letterKey);
        } catch (NumberFormatException e) {
            return letterKey;
        }

        if (key < dictionary.length()) {
            if (upperCase) {
                return Character.toString(dictionary.charAt(key)).toUpperCase();
            } else {
                return Character.toString(dictionary.charAt(key));
            }
        } else {
            return letterKey;
        }
    }

    public static void main(String[] args) {
        System.out.print("Enter dictionary: ");
        Scanner in = new Scanner(System.in);
        String userDictionary;
        userDictionary = in.nextLine();

        while (true) {
            System.out.print("Enter message: ");
            if (in.hasNext()) {
                String userInput = in.nextLine();
                StringBuilder sb = new StringBuilder();
                sb.append("Decoded message: ");
                sb.append(Decoder.decodeMessage(userDictionary, userInput));
                sb.append("\n");
                System.out.print(sb.toString());
            } else {
                break;
            }
        }
    }
}


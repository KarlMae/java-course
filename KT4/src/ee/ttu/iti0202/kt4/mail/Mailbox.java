package ee.ttu.iti0202.kt4.mail;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Mailbox {

    private int id;
    private List<Letter> normalLetters = new ArrayList<>();
    private List<Letter> spam = new ArrayList<>();

    public Mailbox(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public List<Letter> getNormalLetters() {
        return normalLetters;
    }

    public List<Letter> getSpam() {
        return spam;
    }

    public void receiveLetter(Letter letter) {
        if (letter.getDestinationMailboxID() == id) {
            normalLetters.add(letter);
        } else {
            spam.add(letter);
        }
    }

    public Optional<Letter> findLetterByTitle(String title) {
        for (Letter letter : normalLetters) {
            if (letter.getTitle().equalsIgnoreCase(title)) {
                return Optional.of(letter);
            }
        }

        for (Letter letter : spam) {
            if (letter.getTitle().equalsIgnoreCase(title)) {
                return Optional.of(letter);
            }
        }

        return Optional.empty();
    }
}

package ee.ttu.iti0202.kt4.mail;

public class Letter {

    private int mailBoxID;
    private String title;
    private String content;

    public Letter(int mailBoxID, String title, String content) {
        this.mailBoxID = mailBoxID;
        this.title = title;
        this.content = content;
    }

    public int getDestinationMailboxID() {
        return mailBoxID;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Letter letter = (Letter) o;

        return title != null ? title.equals(letter.title) : letter.title == null;
    }

    @Override
    public int hashCode() {
        return title != null ? title.hashCode() : 0;
    }
}

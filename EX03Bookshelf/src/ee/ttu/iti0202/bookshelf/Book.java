
package ee.ttu.iti0202.bookshelf;

public class Book {

    private String title;
    private String author;
    private int yearOfPublishing;
    private int price;
    private Person owner;
    private static int staticId = 0;
    private int id;

    public Book(String title, String author, int yearOfPublishing, int price) {
        this.title = title;
        this.author = author;
        this.yearOfPublishing = yearOfPublishing;
        this.price = price;
        this.id = getAndIncrementNextId();
    }

    public static int getAndIncrementNextId() {
        return staticId++;
    }

    public String getTitle() {
        return this.title;
    }

    public String getAuthor() {
        return this.author;
    }

    public int getYearOfPublishing() {
        return this.yearOfPublishing;
    }

    public Person getOwner() {
        return this.owner;
    }

    public int getPrice() {
        return this.price;
    }

    public int getId() {
        return this.id;
    }

    public boolean buy(Person buyer) {
        if (buyer == null && this.owner == null) {
            return false;
        }


        if (buyer == null) {
            this.owner.sellBook(this);
            return true;
        }

        if (buyer != this.owner && buyer.getMoney() >= this.price) {

            if (this.owner == null) {
                buyer.buyBook(this);
                return true;
            }

            this.owner.sellBook(this);
            buyer.buyBook(this);
            return true;
        }

        return false;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

}

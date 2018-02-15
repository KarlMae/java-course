package ee.ttu.iti0202.bookshelf;


public class Person {
    private int money;
    private String name;

    public Person(String name, int money) {
        this.name = name;
        this.money = money;
    }

    public void addMoney(int money) {
        this.money += money;
    }

    public int getMoney() {
        return this.money;
    }

    public String getName() {
        return this.name;
    }

    public boolean buyBook(Book book) {
        if (book != null && this.money >= book.getPrice() && book.getOwner() != this) {
            this.money -= book.getPrice();
            book.setOwner(this);
            return true;
        }
        return false;
    }

    public boolean sellBook(Book book) {
        if (book != null && book.getOwner() == this) {
            this.money += book.getPrice();
            book.setOwner(null);
            return true;
        }
        return false;
    }
}

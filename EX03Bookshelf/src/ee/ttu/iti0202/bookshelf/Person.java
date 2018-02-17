package ee.ttu.iti0202.bookshelf;

import java.util.*;

public class Person {
    private int money;
    private String name;
    private ArrayList<Book> books = new ArrayList<>();

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
            books.add(book);
            return true;
        }
        return false;
    }

    public boolean sellBook(Book book) {
        if (book != null && book.getOwner() == this) {
            this.money += book.getPrice();
            book.setOwner(null);
            books.remove(book);
            return true;
        }
        return false;
    }

    public List<Book> getBooks() {
        return books;
    }
}

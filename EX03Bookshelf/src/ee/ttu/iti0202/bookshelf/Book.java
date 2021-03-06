package ee.ttu.iti0202.bookshelf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;


public class Book {

    private String title;
    private String author;
    private int yearOfPublishing;
    private int price;
    private Person owner;
    private static int staticId = 0;
    private static ArrayList<Book> books = new ArrayList<>();
    private static HashMap<String, ArrayList<Book>> booksByAuthor = new HashMap<>();
    private int id;
    private static Book lastBookAdded = null;

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

    /**
     * Add book to list(books) and to hashmap(booksByAuthor).
     * It has already been checked, that this book does not exist yet.
     */
    private static void addBook(String title, String author, int yearOfPublishing, int price) {
        Book bookToAdd = new Book(title, author, yearOfPublishing, price);

        //Add book to list
        books.add(bookToAdd);

        //Get list of books corresponding to the author from hash.
        ArrayList<Book> bookList = booksByAuthor.get(author.toLowerCase());

        //If listing of author does not exist create it
        if (bookList == null) {
            bookList = new ArrayList<>();
            bookList.add(bookToAdd);
            booksByAuthor.put(author.toLowerCase(), bookList);
        } else {
            //If book is not already in list, add it
            if (!bookList.contains(bookToAdd)) bookList.add(bookToAdd);
        }
    }

    public static Book of(String title, String author, int yearOfPublishing, int price) {
        if (booksByAuthor.containsKey(author.toLowerCase())) {
            for (Book book : booksByAuthor.get(author.toLowerCase())) {
                if (book.getTitle().equals(title) && book.getAuthor().equals(author)
                        && book.getYearOfPublishing() == yearOfPublishing) {
                    return book;
                }
            }
        }

        addBook(title, author, yearOfPublishing, price);
        lastBookAdded = books.get(books.size() - 1);
        return lastBookAdded;
    }

    /**
     *Create new book with the author and date of last book added.
     */
    public static Book of(String title, int price) {
        //If there is no book added, before this one
        if (lastBookAdded == null) {
            return null;
        }

        addBook(title, lastBookAdded.getAuthor(), lastBookAdded.getYearOfPublishing(), price);
        return books.get(books.size() - 1);
    }

    public static List<Book> getBooksByOwner(Person owner) {
        return owner.getBooks();
    }

    /**
     * Remove book from list(books) and hash(booksByAuthor).
     */
    public static boolean removeBook(Book book) {
        if (book == null) {
            return false;
        }

        if (books.contains(book)) {

            //Sell book
            if (book.getOwner() != null) {
                book.getOwner().sellBook(book);
            }

            //Remove book from list
            books.remove(book);

            //Remove book from hash
            if (booksByAuthor.containsKey(book.getAuthor().toLowerCase())) {
                ArrayList<Book> bookList = booksByAuthor.get(book.getAuthor().toLowerCase());
                bookList.remove(book);
                booksByAuthor.put(book.getAuthor().toLowerCase(), bookList);
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * Return list containing books with given author.
     * If author has no books, returns empty list.
     */
    public static List<Book> getBooksByAuthor(String author) {
        if (booksByAuthor.containsKey(author.toLowerCase())) {
            return booksByAuthor.get(author.toLowerCase());
        } else {
            return Collections.emptyList();
        }
    }
}

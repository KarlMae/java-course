package ee.ttu.iti0202.bookshelf;

import java.util.*;

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

    private static void addBook(String title, String author, int yearOfPublishing, int price){
        Book bookToAdd = new Book(title, author, yearOfPublishing, price);
        books.add(bookToAdd);

        ArrayList<Book> bookList = booksByAuthor.get(author.toLowerCase());

        // if listing of author does not exist create it
        if(bookList == null) {
            bookList = new ArrayList<Book>();
            bookList.add(bookToAdd);
            booksByAuthor.put(author.toLowerCase(), bookList);
        } else {
            // add if book is not already in list
            if(!bookList.contains(bookToAdd)) bookList.add(bookToAdd);
        }
    }

    public static Book of(String title, String author, int yearOfPublishing, int price) {
        for (Book book : books) {
            if (book.getTitle().equals(title) && book.getAuthor().equals(author) && book.getYearOfPublishing() == yearOfPublishing) {
                return book;
            }
        }

        addBook(title, author, yearOfPublishing, price);
        return books.get(books.size() - 1);
    }

    public static Book of(String title, int price) {
        if (books.size() == 0) {
            return null;
        }

        Book lastBook = books.get(books.size() - 1);

        addBook(title, lastBook.getAuthor(), lastBook.getYearOfPublishing(), price);
        return books.get(books.size() - 1);
    }

    public static List<Book> getBooksByOwner(Person owner) {
        return owner.getBooks();
    }

    public static boolean removeBook(Book book) {
        if (book == null) {
            return false;
        }

        if (books.contains(book)) {
            if (book.getOwner() != null) {
                book.getOwner().sellBook(book);
            }
            books.remove(book);
            booksByAuthor.remove(book.getAuthor(), book);
            return true;
        } else {
            return false;
        }
    }

    public static List<Book> getBooksByAuthor(String author) {
        if (booksByAuthor.containsKey(author.toLowerCase())) {
            return booksByAuthor.get(author);
        } else {
            return Collections.emptyList();
        }
    }


}

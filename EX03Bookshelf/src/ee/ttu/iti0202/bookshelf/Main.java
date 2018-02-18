
package ee.ttu.iti0202.bookshelf;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Book tammsaare = new Book("Truth and Justice", "Tammsaare", 1926, 100);
        Book meri = new Book("Silverwhite", "Meri", 1976, 200);

        Person mati = new Person("Mati", 200);
        Person kati = new Person("Kati", 300);

        System.out.println(mati.buyBook(tammsaare)); // true
        System.out.println(mati.getMoney());  // 100
        System.out.println(tammsaare.getOwner().getName()); // Mati

        System.out.println(mati.sellBook(tammsaare)); // true
        System.out.println(mati.getMoney()); // 200
        System.out.println(tammsaare.getOwner()); // null

        System.out.println(mati.sellBook(null)); // false

        System.out.println(mati.buyBook(null)); // true
        System.out.println(mati.getMoney()); // 0

        System.out.println(meri.buy(null)); // true
        System.out.println(mati.getMoney()); // 200
        System.out.println(kati.getMoney()); // 100
        System.out.println(meri.buy(kati)); // false
        System.out.println(kati.getMoney()); // 100
        System.out.println(meri.getOwner().getName()); // Kati
        System.out.println(kati.sellBook(null)); // true
        System.out.println(meri.getOwner()); // null

        // id
        System.out.println(tammsaare.getId()); // 0
        System.out.println(meri.getId()); // 1
        System.out.println(Book.getAndIncrementNextId()); // 2


        // BONUS
        Book b0 = Book.of("Java EX00", 1);
        System.out.println(b0); // null
        Book b1 = Book.of("Java EX01", "Ago Luberg", 2018, 3);
        Book b2 = Book.of("Java EX02", 4);
        System.out.println(b2.getAuthor()); // Ago Luberg
        Book b3 = Book.of("Java EX03", 7);
        Book b4 = Book.of("Java EX01", 11);
        System.out.println(b1 == b4); // true

        Book harry1 = Book.of("Harry Potter: The Philosopher's Stone", "J. K. rowling", 1997, 1000);
        Book harry2 = Book.of("Harry Potter: The Chamber of Secrets", "J. K. Rowling", 1998, 1000);
        List<Book> rowlingBooks = Book.getBooksByAuthor("j. k. rowling");
        System.out.println(rowlingBooks.size()); // 2
        System.out.println(rowlingBooks.get(0).getTitle()); // Harry Potter: The Philosopher's Stone
        System.out.println(rowlingBooks.get(1).getAuthor()); // J. K. Rowling

        Person bonusPerson = new Person("Joonas Boonus", 10000);
        b1.buy(bonusPerson);
        bonusPerson.buyBook(harry1);

        List<Book> personBooks = Book.getBooksByOwner(bonusPerson);
        System.out.println(personBooks.size()); // 2
        System.out.println(personBooks.contains(b1));  // true
        System.out.println(personBooks.contains(harry1)); // true
        System.out.println(bonusPerson.getMoney()); // 8997

        Book.removeBook(b1);
        personBooks = Book.getBooksByOwner(bonusPerson);
        System.out.println(personBooks.size()); // 1
        System.out.println(personBooks.contains(b1)); // false
        System.out.println(bonusPerson.getMoney()); // 9000

    }


}

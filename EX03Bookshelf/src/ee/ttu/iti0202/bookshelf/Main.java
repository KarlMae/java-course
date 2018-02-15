
package ee.ttu.iti0202.bookshelf;

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

    }
}

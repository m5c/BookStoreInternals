package eu.kartoffelquadrat.bookstoreinternals;

/**
 * This class is to demo / test the functionality of the bookstore. It has no importance in a microservice context.
 */
public class DesktopLauncher {

    public static void main(String[] args) {

        Assortment assortment = AssortmentImpl.getInstance();
        Comments comments = CommentsImpl.getInstance();
        GlobalStock stock = GlobalStockImpl.getInstance();

        System.out.println("Welcome to the BookStore.");
        System.out.println("-------------------------");
        System.out.println("Here is a listing of the initialized data:");
    }
}

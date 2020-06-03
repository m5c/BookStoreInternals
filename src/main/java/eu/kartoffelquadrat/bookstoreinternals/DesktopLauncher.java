package eu.kartoffelquadrat.bookstoreinternals;

/**
 * This class is to demo / test the functionality of the bookstore. It has no importance in a microservice context.
 *
 * @author Maximilian Schiedermeier
 */
public class DesktopLauncher {

    /**
     * The desktop launcher has no relevance for a REST case study. However it can be run to test the initial population
     * of the dummy database.
     */
    public static void main(String[] args) {

        printData();
    }

    public static void printData() {
        Assortment assortment = AssortmentImpl.getInstance();
        Comments comments = CommentsImpl.getInstance();
        GlobalStock stock = GlobalStockImpl.getInstance();

        System.out.println("Welcome to the BookStore.");
        System.out.println("-------------------------");
        System.out.println("Here is a listing of the persisted data:");
        System.out.print(assortment);
        System.out.print(stock);
        System.out.print(comments);
    }
}

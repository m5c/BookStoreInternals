package eu.kartoffelquadrat.bookstoreinternals;


import java.util.Collection;
import java.util.Map;

/**
 * A DAO for the book stock in various locations.
 *
 * @author Maximilian Schiedermeier
 */
public interface GlobalStock {

    /**
     * Returns the amount in stock of a given book in a given city
     *
     * @param city for the city of interest
     * @param isbn for book id of interest
     * @return the amount in stock
     */
    int getStock(String city, Long isbn);

    /**
     * Updates the stock for a given city
     *
     * @param city   for the city of interest
     * @param isbn   for book id of interest
     * @param amount for the new amount in stock
     */
    void setStock(String city, Long isbn, Integer amount);

    /**
     * Returns a list of all cities that have a local stock
     *
     * @return a list of all cities (strings)
     */
    Collection<String> getStoreLocations();

    /**
     * Returns the entire stock of a local store.
     *
     * @return a map holding for each book (by isbn) the amount of books in stock at the specified location.
     */
    Map<Long, Integer> getEntireStoreStock(String city);

}

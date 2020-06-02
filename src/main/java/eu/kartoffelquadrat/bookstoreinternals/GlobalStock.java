package eu.kartoffelquadrat.bookstoreinternals;

/**
 * A DAO for the book stock in various locations.
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
}

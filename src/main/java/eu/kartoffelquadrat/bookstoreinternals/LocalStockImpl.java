package eu.kartoffelquadrat.bookstoreinternals;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Collections;

/**
 * Implementation for the LocalStock interface.
 *
 * @author Maximilian Schiedermeier
 */
public class LocalStockImpl implements LocalStock {

    // private map to register the amount of book copies in store, per isbn.
    private final Map<Long, Integer> stockByBook;

    /**
     * Default constructor.
     */
    public LocalStockImpl() {
        stockByBook = new LinkedHashMap<>();
    }

    /**
     * Tells the amount in stock for a given book id (isbn).
     *
     * @param isbn as identifier of the book in question
     * @return the amount in stock for the given book.
     */
    @Override
    public int getAmount(long isbn) {

        // Reject queries for non-indexed books
        if (!AssortmentImpl.getInstance().getEntireAssortment().contains(isbn))
            throw new RuntimeException("Stock queries are only allowed for indexed books.");

        if (!stockByBook.containsKey(isbn))
            return 0;

        return stockByBook.get(isbn);
    }

    /**
     * Update the amount of books in local stock, for a given book
     *
     * @param isbn   as identifies of the book in question
     * @param amount as the new amount in stock for the book in question
     */
    @Override
    public void setAmount(long isbn, int amount) {

        // Reject queries for non-indexed books
        if (!AssortmentImpl.getInstance().getEntireAssortment().contains(isbn))
            throw new RuntimeException("Stock updates are only allowed for indexed books.");

        stockByBook.put(isbn, amount);
    }

    /**
     * Returns the entire stock of this location, as an immutable map
     *
     * @return a map, where the key is a book isbn and the value the amount in stock for this isbn.
     */
    @Override
    public Map<Long, Integer> getEntireStock() {
        return Collections.unmodifiableMap(stockByBook);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (long isbn : stockByBook.keySet()) {
            sb.append(" > ")
                    .append(isbn)
                    .append(": ")
                    .append(stockByBook.get(isbn))
                    .append("\n");
        }
        return sb.toString();
    }
}

package eu.kartoffelquadrat.bookstoreinternals;


/**
 * Represents the stock of a local bookstore.
 *
 * @author Maximilian Schiedermeier
 */
public interface LocalStock {

    /**
     * Tells the amount in stock for a given book id (isbn).
     *
     * @param isbn as identifier of the book in question
     * @return the amount in stock for the given book.
     */
    int getAmount(long isbn);

    /**
     * Update the amount of books in local stock, for a given book
     *
     * @param isbn   as identifies of the book in question
     * @param amount as the new amount in stock for the book in question
     */
    void setAmount(long isbn, int amount);
}

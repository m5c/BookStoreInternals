package eu.kartoffelquadrat.bookstoreinternals;

/**
 * Interface for never-changing meta-data of a book. Book Details are immunable, therefore this interface has only
 * getters, no setters.
 *
 * @author Maximilian Schiedermeier
 */
public interface BookDetails {

    /**
     * Getter for the unique identifier of the book.
     *
     * @return unique book identifier as a long.
     */
    long getIsbn();

    /**
     * Getter for the title of the book.
     *
     * @return book title as string.
     */
    String getTitle();

    /**
     * Getter for the author of the book.
     *
     * @return book author as String.
     */
    String getAuthor();

    /**
     * Getter for the abstract of the book.
     *
     * @return book abstract as String.
     */
    String getBookAbstract();

    /**
     * Getter for book price in cents, (currency is CND).
     *
     * @return book price in cents as int value.
     */
    int getPriceInCents();
}

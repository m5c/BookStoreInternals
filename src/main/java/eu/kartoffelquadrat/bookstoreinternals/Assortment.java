package eu.kartoffelquadrat.bookstoreinternals;

import java.util.Collection;

/**
 * A DAO for indexed books. There can only be one Assortment. Assortment implementations should not have a public
 * constructor, only be accessed as a singleton.
 *
 * @author Maximilian Schiedermeier
 */
public interface Assortment {

    /**
     * Retrieves all books that are indexed (that a description is knwon for), no matter if they are in stock somewhere
     * or not.
     *
     * @return the list off known isbns.
     */
    Collection<Long> getEntireAssortment();

    /**
     * Retrieved book details for a specific book, identified by isbn.
     *
     * @param isbn for the identifier of the book in question.
     * @return A BookDetail object, containing the static metadata of the requested book.
     */
    BookDetailsImpl getBookDetails(Long isbn);

    /**
     * Indexes a new book. The isbn of the bookDetails parameter bean must not conflict with an existing book.
     *
     * @param bookDetails for the exact data of the book to be added to the assortment.
     */
    void addBookToAssortment(BookDetailsImpl bookDetails);
}

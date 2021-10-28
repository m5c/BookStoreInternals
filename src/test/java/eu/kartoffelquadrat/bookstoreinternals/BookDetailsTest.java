package eu.kartoffelquadrat.bookstoreinternals;

import org.junit.Test;

/**
 * @author Maximilian Schiedermeier
 */
public class BookDetailsTest {

    @Test
    public void verifyAllBookDetails() {
        long harryPotterIsbn = Long.valueOf("9780739360385");

        BookDetailsImpl details = AssortmentImpl.getInstance().getBookDetails(harryPotterIsbn);

        assert details.getAuthor().equals("J.K.Rowling");
        assert details.getIsbn() == harryPotterIsbn;
        assert details.getTitle().equals("Harry Potter and the deathly hallows");
        assert details.getPriceInCents() == 5450;
        assert !details.getBookAbstract().trim().isEmpty();
    }

    @Test
    public void defaultConstructor() {
        BookDetailsImpl bd = new BookDetailsImpl();
    }
}

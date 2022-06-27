package eu.kartoffelquadrat.bookstoreinternals;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author Maximilian Schiedermeier
 */
public class AssortmentTest {

    @Test
    public void verifyPopulation() {

        Assortment assortment = getNewAssortmentInstance();
        assert assortment.getEntireAssortment().size() == 4;
        assert assortment.getEntireAssortment().contains(Long.valueOf("9780739360385"));
    }

    @Test
    public void testGetDetails() {
        Assortment assortment = getNewAssortmentInstance();
        BookDetailsImpl bookDetails = assortment.getBookDetails(Long.valueOf("9780739360385"));
        assert bookDetails != null;
        assert bookDetails.getAuthor().equals("J.K.Rowling");
    }

    @Test
    public void testSingleton() {
        Assortment firstInstance = AssortmentImpl.getInstance();
        Assortment secondInstance = AssortmentImpl.getInstance();

        assert firstInstance == secondInstance;
    }

    @Test
    public void testIndexNewBook() {
        Assortment assortment = getNewAssortmentInstance();

        BookDetailsImpl littleMissSunshine = new BookDetailsImpl(Long.valueOf("9780843178166"), "Little Miss Sunshine", "Roger Hargreaves", 310, "They're back! Rediscover the zaniest characters you've ever met in this best-selling series which has sold millions worldwide. Bright and charming, with easily recognizable characters and a small take-along format, Mr. Men and Little Miss books are easy enough for young readers, witty enough for humor-prone adults...");
        int assortmentSizeBeforeAdding = assortment.getEntireAssortment().size();

        assortment.addBookToAssortment(littleMissSunshine);
        assert assortment.getEntireAssortment().size() == assortmentSizeBeforeAdding + 1;
    }

    @Test(expected = RuntimeException.class)
    public void testRejectExistingIsbn() {
        Assortment assortment = getNewAssortmentInstance();

        // Try to add something that collides with the harry potter ISBN
        BookDetailsImpl conflictingBook = new BookDetailsImpl(Long.valueOf("9780739360385"), "Some fake title", "Some fake author", 42, "A dummy book that by ISB conflicts with HP and the deathly hallows.");
        int assortmentSizeBeforeAdding = assortment.getEntireAssortment().size();

        assortment.addBookToAssortment(conflictingBook);
        assert assortment.getEntireAssortment().size() == assortmentSizeBeforeAdding;
    }

    /**
     * Helper method to access the private default constructor. This test should not operate on the singleton's
     * getInstance method, since the tests may alter the bean state and therefore cannot be executed in arbitrary order.
     * To avoid this we use this method instead to ensure individual tests operate on isolated instances.
     *
     * @return a guaranteed new instance of AssortmentImpl
     */
    private Assortment getNewAssortmentInstance() {
        try {
            Constructor<AssortmentImpl> constructor = AssortmentImpl.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            return constructor.newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException("Something went wrong creating a NEW instance of AssortmentImpl via reflection");
        }
    }
}

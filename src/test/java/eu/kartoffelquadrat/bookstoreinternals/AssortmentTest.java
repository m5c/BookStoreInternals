package eu.kartoffelquadrat.bookstoreinternals;

import org.junit.Test;

/**
 * @author Maximilian Schiedermeier
 */
public class AssortmentTest {

    @Test
    public void verifyPopulation() {

        Assortment assortment = new AssortmentImpl();
        assert assortment.getEntireAssortment().size() == 4;
        assert assortment.getEntireAssortment().contains(Long.valueOf("9780739360385"));
    }

    @Test
    public void testGetDetails() {
        Assortment assortment = new AssortmentImpl();
        BookDetails bookDetails = assortment.getBookDetails(Long.valueOf("9780739360385"));
        assert bookDetails != null;
        assert bookDetails.getAuthor().equals("J.K.Rowling");
    }

    @Test
    public void testSingleton() {
        Assortment firstInstance = AssortmentImpl.getInstance();
        Assortment secondInstance = AssortmentImpl.getInstance();

        assert firstInstance == secondInstance;
    }
}

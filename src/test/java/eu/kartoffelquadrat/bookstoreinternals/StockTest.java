package eu.kartoffelquadrat.bookstoreinternals;

import org.junit.Test;

import java.util.Collection;
import java.util.Map;

/**
 * @author Maximilian Schiedermeier
 */
public class StockTest {

    /**
     * Try to modify the amount of copies on store for a given book.
     */
    @Test
    public void testUpdateStock() {

        long harryPotterIsbn = Long.valueOf("9780739360385");

        GlobalStock globalStock = new GlobalStockImpl();
        globalStock.setStock("Lyon", harryPotterIsbn, 100);
        int stock = globalStock.getStock("Lyon", harryPotterIsbn);

        assert stock == 100;
    }

    /**
     * Test write access to a book that is not indexed by ISBN.
     */
    @Test(expected = RuntimeException.class)
    public void testInvalidIsbnWriteAccess() {
        GlobalStock globalStock = new GlobalStockImpl();
        globalStock.setStock("Lyon", Long.valueOf("12341234"), 100);
    }

    /**
     * Test read access to a book that is not indexed by ISBN.
     */
    @Test(expected = RuntimeException.class)
    public void testInvalidIsbnReadAccess() {
        GlobalStock globalStock = new GlobalStockImpl();
        globalStock.getStock("Lyon", Long.valueOf("43214321"));
    }

    /**
     * Try to get the list of all cities that have a local stock
     */
    @Test
    public void testGetAllStoreLocations()
    {
        GlobalStock globalStock = new GlobalStockImpl();
        Collection<String> locations = globalStock.getStoreLocations();

        assert locations.size() == 4;
        assert locations.contains("Lyon");
    }

    /**
     * Try write access on a city that does not exist.
     */
    @Test(expected = RuntimeException.class)
    public void testInvalidCityReadAccess() {

        long harryPotterIsbn = Long.valueOf("9780739360385");

        GlobalStock globalStock = new GlobalStockImpl();
        globalStock.setStock("Bielefeld", harryPotterIsbn, 100);
    }

    /**
     * Try read access on a city that does not exist.
     */
    @Test(expected = RuntimeException.class)
    public void testInvalidCityWriteAccess() {

        long harryPotterIsbn = Long.valueOf("9780739360385");

        GlobalStock globalStock = new GlobalStockImpl();
        globalStock.getStock("Bielefeld", harryPotterIsbn);
    }

    /**
     * Try to get full stock of a local store.
     */
    @Test
    public void testFullLocalStockAccess()
    {
        GlobalStock globalStock = new GlobalStockImpl();
        Map<Long, Integer> lyonStock = globalStock.getEntireStoreStock("Lyon");
        assert lyonStock != null;
        assert lyonStock.size() > 0;
    }

    /**
     * Try to get a list of all cities that have something in stock
     */
    @Test
    public void testGetAllLocations()
    {
        GlobalStock globalStock = new GlobalStockImpl();
        Collection<String> cities = globalStock.getStoreLocations();
        assert !cities.isEmpty();
        assert cities.size() == 4;
        assert cities.contains("Lyon");
    }

    /**
     * Default constructor test
     */
    @Test
    public void testDefaultConstructor()
    {
        GlobalStock gs = new GlobalStockImpl();
    }

    /**
     * Singleton constructor test
     */
    @Test
    public void testSingleton()
    {
        GlobalStock gs = GlobalStockImpl.getInstance();
    }
}

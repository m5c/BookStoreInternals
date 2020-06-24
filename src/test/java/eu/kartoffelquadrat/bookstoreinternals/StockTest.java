package eu.kartoffelquadrat.bookstoreinternals;

import org.junit.Test;

import java.util.Collection;
import java.util.Map;

/**
 * @author Maximilian Schiedermeier
 */
public class StockTest {

    @Test
    public void testUpdateStock() {

        long harryPotterIsbn = Long.valueOf("9780739360385");

        GlobalStock globalStock = GlobalStockImpl.getInstance();
        globalStock.setStock("Lyon", harryPotterIsbn, 100);
        int stock = globalStock.getStock("Lyon", harryPotterIsbn);

        assert stock == 100;
    }

    @Test(expected = RuntimeException.class)
    public void testInvalidIsbnWriteAccess() {
        GlobalStock globalStock = GlobalStockImpl.getInstance();
        globalStock.setStock("Lyon", Long.valueOf("12341234"), 100);
    }

    @Test(expected = RuntimeException.class)
    public void testInvalidIsbnReadAccess() {
        GlobalStock globalStock = GlobalStockImpl.getInstance();
        globalStock.getStock("Lyon", Long.valueOf("43214321"));
    }

    /**
     * Try to get the list of all cities that have a local stock
     */
    @Test
    public void testGetAllStoreLocations()
    {
        GlobalStock globalStock = GlobalStockImpl.getInstance();
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

        GlobalStock globalStock = GlobalStockImpl.getInstance();
        globalStock.setStock("Bielefeld", harryPotterIsbn, 100);
    }

    /**
     * Try read access on a city that does not exist.
     */
    @Test(expected = RuntimeException.class)
    public void testInvalidCityWriteAccess() {

        long harryPotterIsbn = Long.valueOf("9780739360385");

        GlobalStock globalStock = GlobalStockImpl.getInstance();
        globalStock.getStock("Bielefeld", harryPotterIsbn);
    }

    /**
     * Try to get full stock of a local store.
     */
    @Test
    public void testFullLocalStockAccess()
    {
        GlobalStock globalStock = GlobalStockImpl.getInstance();
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
        GlobalStock globalStock = GlobalStockImpl.getInstance();
        Collection<String> cities = globalStock.getStoreLocations();
        assert !cities.isEmpty();
        assert cities.size() == 4;
        assert cities.contains("Lyon");
    }

    /**
     * Default contructor test
     */
    @Test
    public void testDefaultConstructor()
    {
        GlobalStock gs = new GlobalStockImpl();
    }
}

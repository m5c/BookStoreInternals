package eu.kartoffelquadrat.bookstoreinternals;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
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

        GlobalStock globalStock = getNewInstance();
        globalStock.setStock("Lyon", harryPotterIsbn, 100);
        int stock = globalStock.getStock("Lyon", harryPotterIsbn);

        assert stock == 100;
    }

    /**
     * Test write access to a book that is not indexed by ISBN. Although a real bookstore should not allow this, for
     * simplicity we do not prevent setting stock for non-indexed books.
     */
    @Test
    public void testInvalidIsbnWriteAccess() {
        GlobalStock globalStock = getNewInstance();
        globalStock.setStock("Lyon", Long.valueOf("12341234"), 100);
    }

    /**
     * Test read access to a book that is not indexed by ISBN. Although a real bookstore should not allow this, for
     * simplicity we do not prevent looking up stock for non-indexed books.
     */
    @Test
    public void testInvalidIsbnReadAccess() {
        GlobalStock globalStock = getNewInstance();
        globalStock.getStock("Lyon", Long.valueOf("43214321"));
    }

    /**
     * Try to get the list of all cities that have a local stock
     */
    @Test
    public void testGetAllStoreLocations() {
        GlobalStock globalStock = getNewInstance();
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

        GlobalStock globalStock = getNewInstance();
        globalStock.setStock("Bielefeld", harryPotterIsbn, 100);
    }

    /**
     * Try read access on a city that does not exist.
     */
    @Test(expected = RuntimeException.class)
    public void testInvalidCityWriteAccess() {

        long harryPotterIsbn = Long.valueOf("9780739360385");

        GlobalStock globalStock = getNewInstance();
        globalStock.getStock("Bielefeld", harryPotterIsbn);
    }

    /**
     * Try to get full stock of a local store.
     */
    @Test
    public void testFullLocalStockAccess() {
        GlobalStock globalStock = getNewInstance();
        Map<Long, Integer> lyonStock = globalStock.getEntireStoreStock("Lyon");
        assert lyonStock != null;
        assert lyonStock.size() > 0;
    }

    /**
     * Try to get a list of all cities that have something in stock
     */
    @Test
    public void testGetAllLocations() {
        GlobalStock globalStock = getNewInstance();
        Collection<String> cities = globalStock.getStoreLocations();
        assert !cities.isEmpty();
        assert cities.size() == 4;
        assert cities.contains("Lyon");
    }

    /**
     * Private constructor test
     */
    @Test
    public void testDefaultConstructor() {
        GlobalStock gs = getNewInstance();
    }

    /**
     * Singleton constructor test
     */
    @Test
    public void testSingleton() {
        GlobalStock gs = GlobalStockImpl.getInstance();
    }

    /**
     * Helper method to access the private default constructor. This test should not operate on the singleton's
     * getInstance method, since the tests may alter the bean state and therefore cannot be executed in arbitrary order.
     * To avoid this we use this method instead to ensure individual tests operate on isolated instances.
     *
     * @return a guaranteed new instance of GlobalStockImpl
     */
    private GlobalStockImpl getNewInstance() {
        try {
            Constructor<GlobalStockImpl> constructor = GlobalStockImpl.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            return constructor.newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException("Something went wrong creating a NEW instance of CommentsImpl via reflection");
        }
    }
}

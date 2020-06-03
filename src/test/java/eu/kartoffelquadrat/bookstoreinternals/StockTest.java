package eu.kartoffelquadrat.bookstoreinternals;

import org.junit.Test;

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
}

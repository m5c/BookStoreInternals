package eu.kartoffelquadrat.bookstoreinternals;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Maximilian Schiedermeier
 */
public class GlobalStockImpl implements GlobalStock {

    private static GlobalStock singletonReference;
    Map<String, LocalStock> stocksPerCity;

    /**
     * Public default constructor for frameworks that automatically initialize beans as singletons.
     */
    public GlobalStockImpl() {}


    /**
     * Default constructor adds dummy data to the "in-ram DB"
     *
     * @param populate flag causes the global stock to be initialized with dummy data, if set to true
     */
    private GlobalStockImpl(boolean populate) {
        stocksPerCity = new LinkedHashMap<>();

        if (populate) {
            populateWithDummyData();
        }
    }

    /**
     * Singleton access method.
     *
     * @return the singleton instance.
     */
    public static GlobalStock getInstance() {

        if (singletonReference == null)
            singletonReference = new GlobalStockImpl(true);

        return singletonReference;
    }

    public int getStock(String city, Long isbn) {

        if (!stocksPerCity.containsKey(city))
            throw new RuntimeException("Can not lookup amount in stock. No such city: " + city);

        return stocksPerCity.get(city).getAmount(isbn);
    }

    public void setStock(String city, Long isbn, Integer amount) {

        if (!stocksPerCity.containsKey(city))
            throw new RuntimeException("Can not update amount in stock. No such city: " + city);

        stocksPerCity.get(city).setAmount(isbn, amount);
    }

    @Override
    public Collection<String> getStoreLocations() {
        return stocksPerCity.keySet();
    }

    @Override
    public Map<Long, Integer> getEntireStoreStock(String city) {
        return stocksPerCity.get(city).getEntireStock();
    }

    private void populateWithDummyData() {

        // Add some locations.
        stocksPerCity.put("Montréal", new LocalStockImpl());
        stocksPerCity.put("München", new LocalStockImpl());
        stocksPerCity.put("Osterhofen", new LocalStockImpl());
        stocksPerCity.put("Lyon", new LocalStockImpl());

        // initialize stock for locations.
        stocksPerCity.get("Montréal").setAmount(Long.valueOf("9780739360385"), 1);
        stocksPerCity.get("Montréal").setAmount(Long.valueOf("9781977791122"), 2);
        stocksPerCity.get("Montréal").setAmount(Long.valueOf("9780262538473"), 3);

        stocksPerCity.get("München").setAmount(Long.valueOf("9780739360385"), 50);
        stocksPerCity.get("München").setAmount(Long.valueOf("9780553382563"), 2);
        stocksPerCity.get("München").setAmount(Long.valueOf("9781977791122"), 7);

        stocksPerCity.get("Osterhofen").setAmount(Long.valueOf("9780739360385"), 15);
        stocksPerCity.get("Osterhofen").setAmount(Long.valueOf("9780553382563"), 2);
        stocksPerCity.get("Osterhofen").setAmount(Long.valueOf("9781977791122"), 5);
        stocksPerCity.get("Osterhofen").setAmount(Long.valueOf("9780262538473"), 8);

        stocksPerCity.get("Lyon").setAmount(Long.valueOf("9780739360385"), 4);
        stocksPerCity.get("Lyon").setAmount(Long.valueOf("9780262538473"), 2);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("\n **************\n *    Stock   * \n **************\n");
        for (String city : stocksPerCity.keySet())
        {
            sb.append(city+":\n");
            sb.append(stocksPerCity.get(city));
        }
        return sb.toString();
    }

}

package eu.kartoffelquadrat.bookstoreinternals;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Implementation for the GlobalStock interface.
 *
 * @author Maximilian Schiedermeier
 */
public class GlobalStockImpl implements GlobalStock {

  private static GlobalStock singletonReference;
  Map<String, LocalStock> localStocks;

  /**
   * Private constructor for singleton pattern. Implicitly populates the stocksPerCity map, so that
   * the one and only instance of this class has some data to work with.
   */
  private GlobalStockImpl() {

    localStocks = new LinkedHashMap<>();
    populateWithDummyData();
  }

  /**
   * Singleton access method.
   *
   * @return the singleton instance.
   */
  public static GlobalStock getInstance() {

    if (singletonReference == null) {
      singletonReference = new GlobalStockImpl();
    }

    return singletonReference;
  }

  /**
   * Returns the amount in stock of a given book in a given city.
   *
   * @param city for the city of interest
   * @param isbn for book id of interest
   * @return the amount in stock
   */
  @Override
  public int getStock(String city, Long isbn) {

    if (!localStocks.containsKey(city)) {
      throw new RuntimeException("Can not lookup amount in stock. No such city: " + city);
    }

    return localStocks.get(city).getAmount(isbn);
  }

  /**
   * Updates the stock for a given city.
   *
   * @param city   for the city of interest
   * @param isbn   for book id of interest
   * @param amount for the new amount in stock
   */
  @Override
  public void setStock(String city, Long isbn, Integer amount) {

    if (!localStocks.containsKey(city)) {
      throw new RuntimeException("Can not update amount in stock. No such city: " + city);
    }

    localStocks.get(city).setAmount(isbn, amount);
  }

  /**
   * Returns a list of all cities that have a local stock.
   *
   * @return a list of all cities (strings)
   */
  @Override
  public Collection<String> getStoreLocations() {
    return localStocks.keySet();
  }

  /**
   * Returns the entire stock of a local store.
   *
   * @param city for the city of interest
   * @return a map holding for each book the amount of books in stock at the specified location.
   */
  @Override
  public Map<Long, Integer> getEntireStoreStock(String city) {
    return localStocks.get(city).getEntireStock();
  }

  /**
   * Helper method to look up if a local stock has a given employee.
   *
   * @param city as the location of the local stock store.
   * @param name as the family name of the employee.
   * @return true if such an employee works in that city, false otherwise.
   */
  @Override
  public boolean isEmplyed(String city, String name) {
    return localStocks.get(city).hasEmployee(name);
  }

  /**
   * Helper method to add some dummy stock data for all local branches. Should be invoked upon
   * creation of this class.
   */
  private void populateWithDummyData() {

    // Add some locations.
    localStocks.put("Montréal", new LocalStockImpl());
    localStocks.put("München", new LocalStockImpl());
    localStocks.put("Osterhofen", new LocalStockImpl());
    localStocks.put("Lyon", new LocalStockImpl());

    // Add some employees
    localStocks.get("Osterhofen").addEmployee(new StoreEmployee("Merkel", "Angela"));
    localStocks.get("Lyon").addEmployee(new StoreEmployee("Curie", "Marie"));
    localStocks.get("München").addEmployee(new StoreEmployee("Plank", "Max"));
    localStocks.get("Montréal").addEmployee(new StoreEmployee("Cohen", "Leonard"));


    // initialize stock for locations.
    localStocks.get("Montréal").setAmount(Long.parseLong("9780739360385"), 1);
    localStocks.get("Montréal").setAmount(Long.parseLong("9781977791122"), 2);
    localStocks.get("Montréal").setAmount(Long.parseLong("9780262538473"), 3);

    localStocks.get("München").setAmount(Long.parseLong("9780739360385"), 50);
    localStocks.get("München").setAmount(Long.parseLong("9780553382563"), 2);
    localStocks.get("München").setAmount(Long.parseLong("9781977791122"), 7);

    localStocks.get("Osterhofen").setAmount(Long.parseLong("9780739360385"), 15);
    localStocks.get("Osterhofen").setAmount(Long.parseLong("9780553382563"), 2);
    localStocks.get("Osterhofen").setAmount(Long.parseLong("9781977791122"), 5);
    localStocks.get("Osterhofen").setAmount(Long.parseLong("9780262538473"), 8);

    localStocks.get("Lyon").setAmount(Long.parseLong("9780739360385"), 4);
    localStocks.get("Lyon").setAmount(Long.parseLong("9780262538473"), 2);
  }

  /**
   * Helper method to convert all stored stock information into human readable format.
   *
   * @return A nicely formatted string listing all local stocks.
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("\n **************\n *    Stock   * \n **************\n");
    for (String city : localStocks.keySet()) {
      sb.append(city).append(":\n");
      sb.append(localStocks.get(city));
    }
    return sb.toString();
  }

}

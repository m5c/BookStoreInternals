package eu.kartoffelquadrat.bookstoreinternals;


import java.util.Collection;
import java.util.Map;

/**
 * Represents the stock levels of all local store branches.
 *
 * @author Maximilian Schiedermeier
 */
public interface GlobalStock {

  /**
   * Returns the amount in stock of a given book in a given city.
   *
   * @param city for the city of interest
   * @param isbn for book id of interest
   * @return the amount in stock
   */
  int getStock(String city, Long isbn);

  /**
   * Updates the stock for a given city.
   *
   * @param city   for the city of interest
   * @param isbn   for book id of interest
   * @param amount for the new amount in stock
   */
  void setStock(String city, Long isbn, Integer amount);

  /**
   * Returns a list of all cities that have a local stock.
   *
   * @return a list of all cities (strings)
   */
  Collection<String> getStoreLocations();

  /**
   * Returns the entire stock of a local store.
   *
   * @param city for the city of interest
   * @return a map holding for each book the amount of books in stock at the specified location.
   */
  Map<Long, Integer> getEntireStoreStock(String city);

  /**
   * Helper method to look up if a local stock has a given employee.
   *
   * @param city as the location of the local stock store.
   * @param name as the family name of the employee.
   * @return true if such an employee works in that city, false otherwise.
   */
  boolean isEmployed(String city, String name);
}

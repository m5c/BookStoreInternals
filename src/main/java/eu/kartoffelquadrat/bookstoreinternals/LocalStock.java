package eu.kartoffelquadrat.bookstoreinternals;


import java.util.Collection;
import java.util.Map;

/**
 * Represents the stock of a local bookstore.
 *
 * @author Maximilian Schiedermeier
 */
public interface LocalStock {

  /**
   * Tells the amount in stock for a given book id (isbn).
   *
   * @param isbn as identifier of the book in question
   * @return the amount in stock for the given book.
   */
  int getAmount(long isbn);

  /**
   * Update the amount of books in local stock, for a given book.
   *
   * @param isbn   as identifies of the book in question
   * @param amount as the new amount in stock for the book in question
   */
  void setAmount(long isbn, int amount);

  /**
   * Adds a given employee to the store.
   *
   * @param employee as the employee to add.
   */
  void addEmployee(StoreEmployee employee);

  /**
   * Method to look up if an employee with the given name works for this store.
   *
   * @param employeeFamilyName as the family name of the employy to check for.
   * @return true if an employe with this name works for the local store, false otherwise.
   */
  boolean hasEmployee(String employeeFamilyName);

  /**
   * Generates list of all employee's family names for this local store.
   *
   * @return list of strings with all employees family names.
   */
  Collection<String> getAllEmployeeNames();

  /**
   * Returns the entire stock of this location, as an immutable map.
   *
   * @return a map, where the key is a book isbn and the value the amount in stock for this isbn.
   */
  Map<Long, Integer> getEntireStock();

}

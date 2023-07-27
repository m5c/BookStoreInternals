package eu.kartoffelquadrat.bookstoreinternals;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Implementation for the LocalStock interface.
 *
 * @author Maximilian Schiedermeier
 */
public class LocalStockImpl implements LocalStock {

  // private map to register the amount of book copies in store, per isbn.
  private final Map<Long, Integer> stockByBook;

  // private map to store list of all local store employees.
  private final Collection<StoreEmployee> employees;

  /**
   * Default constructor.
   */
  public LocalStockImpl() {
    stockByBook = new LinkedHashMap<>();
    employees = new LinkedList<>();
  }

  /**
   * Tells the amount in stock for a given book id (isbn). There is no effective check if that book
   * is actually known to the assortment.
   *
   * @param isbn as identifier of the book in question.
   * @return the amount in stock for the given book.
   */
  @Override
  public int getAmount(long isbn) {

    if (!stockByBook.containsKey(isbn)) {
      return 0;
    }

    return stockByBook.get(isbn);
  }

  /**
   * Update the amount of books in local stock, for a given book. There is no effective check if
   * that book is actually known to the assortment.
   *
   * @param isbn   as identifies of the book in question
   * @param amount as the new amount in stock for the book in question
   */
  @Override
  public void setAmount(long isbn, int amount) {

    stockByBook.put(isbn, amount);
  }

  /**
   * Adds a given employee to the store.
   *
   * @param employee as the employee to add.
   */
  @Override
  public void addEmployee(StoreEmployee employee) {

    employees.add(employee);
  }

  /**
   * Method to look up if an employee with the given name works for this store.
   *
   * @param employeeFamilyName as the family name of the employy to check for.
   * @return true if an employe with this name works for the local store, false otherwise.
   */
  @Override
  public boolean hasEmployee(String employeeFamilyName) {
    return employees.stream().map(employe -> employe.getName()).toList()
        .contains(employeeFamilyName);
  }

  /**
   * Generates list of all employee's family names for this local store.
   *
   * @return list of strings with all employees family names.
   */
  @Override
  public Collection<String> getAllEmployeeNames() {
    return employees.stream().map(employe -> employe.getName()).toList();
  }

  /**
   * Returns the entire stock of this location, as an immutable map.
   *
   * @return a map, where the key is a book isbn and the value the amount in stock for this isbn.
   */
  @Override
  public Map<Long, Integer> getEntireStock() {
    return Collections.unmodifiableMap(stockByBook);
  }

  /**
   * Overrides default toString method to serialize instances of this class to human readable
   * string.
   *
   * @return Human readable string representation of current object.
   */
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (long isbn : stockByBook.keySet()) {
      sb.append(" > ").append(isbn).append(": ").append(stockByBook.get(isbn)).append("\n");
    }
    return sb.toString();
  }
}

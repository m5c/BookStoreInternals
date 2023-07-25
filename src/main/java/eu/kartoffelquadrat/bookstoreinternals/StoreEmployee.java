/**
 * Entitiy representing personnel working for a specific store location. Emplyes serve as sample
 * entities for use cases with API access restricitions.
 *
 * @author Maximilian Schiedermeier
 */

package eu.kartoffelquadrat.bookstoreinternals;

/**
 * Represents and immutable store employe. The Bookstore offers no external means to modify or
 * reassign employees.
 */
public class StoreEmployee {

  private final String name;
  private final String firstName;

  /**
   * Constructor for store employee.
   *
   * @param name      as the employees family name.
   * @param firstName as the employees first name.
   */
  public StoreEmployee(String name, String firstName) {
    this.name = name;
    this.firstName = firstName;
  }

  /**
   * Getter for employees family name.
   *
   * @return family name of employee.
   */
  public String getName() {
    return name;
  }

  /**
   * Getter for employees first name.
   *
   * @return first name of employee.
   */
  public String getFirstName() {
    return firstName;
  }
}

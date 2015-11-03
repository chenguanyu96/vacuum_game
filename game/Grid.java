package game;

/**
 * A interface to represent the Grid.
 * @author Guan Yu Chen
 *
 * @param <T> generic type T
 */
public interface Grid<T> {

  /**
   * Changes the value of row, column and the item in a cell. 
   * 
   * @param row    value to be set for row
   * @param column value to be set for column
   * @param item   value to be set for item
   */
  public void setCell(int row, int column, T item);

  /**
   * Returns the value of the item in the cell at the coordinates (row, column).
   * 
   * @param row    row number of cell to be returned
   * @param column column number of cell to be returned
   * @return       Object in specified cell
   */
  public T getCell(int row, int column);

  /** Returns the number of rows in Grid.
   * 
   * @return number of rows in Grid
   */
  public int getNumRows();

  /** Returns the number of columns in Grid.
   * 
   * @return number of columns in Grid
   */
  public int getNumColumns();

  /** Returns true iff both the object other and caller are both of the same
   * type and have the same number of rows and columns, otherwise false.
   * 
   * @param other object to be compared to with caller
   * @return      true if equal and false if not
   */
  public boolean equals(Object other);

  /** Returns the String representation of Grid type object.
   * 
   * @return string representation of Grid type object
   */
  public String toString();
}

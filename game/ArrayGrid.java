package game;

/** A class to represent the generic type ArrayGrid.
 * @author Guan Yu Chen
 *
 * @param <T> generic type T
 */
public class ArrayGrid<T> implements Grid<T> {

  // number of rows
  private int numRows;
  
  // number of columns
  private int numColumns;
  
  // grid
  private T[][] grid;

  /** Creates a new ArrayGrid with numRows and numColumns.
   * 
   * @param numRows    number of rows ArrayGrid has
   * @param numColumns number of columns ArrayGrid has
   */
  @SuppressWarnings("unchecked")
  public ArrayGrid(int numRows, int numColumns) {
    super();
    this.numRows = numRows;
    this.numColumns = numColumns;
    
    // Initializes the grid to take Object type and cast it to type of T
    grid = (T[][]) new Object[numRows][numColumns];
  }

  /** Changes the item in the cell at (row, column) to the item specified.*/
  @Override
  public void setCell(int row, int column, T item) {
    grid[row][column] = item;
  }
  
  /** Returns the item in the cell at (row, column).*/
  @Override
  public T getCell(int row, int column) {
    return grid[row][column];
  }

  /** Returns the number of rows ArrayGrid has.*/
  @Override
  public int getNumRows() {
    return numRows;
  }

  /** Returns the number of columns ArrayGrid has.*/
  @Override
  public int getNumColumns() {
    return numColumns;
  }

  /** Returns true iff other and the caller are both of type ArrayGrid and have
   * the same number of rows and columns, otherwise false.
   */
  @Override
  public boolean equals(Object other) {

    /* Check if other is in instance of ArrayGrid in order to get the number
     * of rows and columns that other has.
     */
    if (other.getClass() == this.getClass()) {

      /* Checks if the number of columns and rows in other equals to the
       * number of columns and rows in the caller, true is returned if above
       * condition is satisfied.
       */
      if ((((ArrayGrid<?>) other).getNumColumns() == this.getNumColumns()) 
          &&  (((ArrayGrid<?>) other).getNumRows() == this.getNumRows()) 
          && isMatchingContent(other) == true) {
        return true;
      }
    }

    /* False is returned if other is not of type ArrayGrid, or other doesn't
     * have the same number of rows and columns as the caller.
     */
    return false;
  }
  
  /** Returns True iff the contents of the 2 ArrayGrid objects matches, otherwise returns false.
   * 
   * @param other the object being compared to
   * @return      true if contents match, otherwise false
   */
  private boolean isMatchingContent(Object other) {
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[i].length; j++) {
        if (this.getCell(i, j) != ((ArrayGrid<?>) other).getCell(i, j)) {
          return false;
        }
      }
    }
    return true;
  }

  /** Returns the String representation of an ArrayGrid.*/
  @Override
  public String toString() {
    String retStr = "";

    /* Iterates through arrayGrid and stores every value in according to its
     * row number of column number.
     */
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[i].length; j++) {
        retStr = retStr + grid[i][j];
      }

      // Start on a newline after every row.
      retStr = retStr + "\n";
    }
    return retStr;
  }
}

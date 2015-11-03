package sprites;

/**
 * An abstract class to represent an object of type Sprite.
 * @author Guan Yu Chen
 *
 */
public abstract class Sprite {

  protected char symbol;
  protected int row;
  protected int column;

  /** Creates a Sprite object with a symbol, row and column values to represent the position of the
   * object on the grid.
   * 
   * @param symbol the symbol that is associated with the Sprite object
   * @param row    the row value that the Sprite object is at on the grid
   * @param column the column value that the Sprite object is at on the grid
   */
  public Sprite(char symbol, int row, int column) {
    super();
    this.symbol = symbol;
    this.row = row;
    this.column = column;
  }

  /** Returns the value stored in the variable symbol.
   * 
   * @return the symbol
   */
  public char getSymbol() {
    return symbol;
  }

  /** Return the value stored in the variable row.
   * 
   * @return the row
   */
  public int getRow() {
    return row;
  }

  /** Returns the value stored in the variable column.
   * 
   * @return the column
   */
  public int getColumn() {
    return column;
  }

  /** Returns a String representation of a Sprite object. */
  public String toString() {
    return Character.toString(symbol);
  }
}

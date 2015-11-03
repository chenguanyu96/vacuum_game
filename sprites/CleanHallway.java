package sprites;

/**
 * A class to represent a CleanHallway of type Sprite.
 * @author Guan Yu Chen
 */
public class CleanHallway extends Sprite{

  /** Creates a CleanHallway object that contains the symbol, and its current location on the grid,
   * (row, column).
   * 
   * @param symbol the symbol assigned to the Sprite object
   * @param row    the row value that the Sprite object is currently at
   * @param column the column value that the Sprite object is currently at
   * @see   Sprite
   */
  public CleanHallway(char symbol, int row, int column) {
    super(symbol, row, column);
  }

}

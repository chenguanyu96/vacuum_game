package sprites;

/**
 * A class to represent a Dirt object of type Sprite.
 * @author Guan Yu Chen
 */
public class Dirt extends Sprite{

  protected int value;

  /** Creates a Dirt object of type Sprite that has the symbol, value, and the current position of
   * the Dirt object on the grid (row, column).
   * 
   * @param symbol symbol of the Sprite object
   * @param row    row value of the Sprite object on the grid
   * @param column column value of the Sprite object on the grid
   * @param value  the score earned when cleaning a dirt object
   * @see   Sprite
   */
  public Dirt(char symbol, int row, int column, int value) {
    super(symbol, row, column);
    this.value = value;
  }

  /** Returns the value stored in the variable value.
   * 
   * @return the value
   * @see    Sprite
   */
  public int getValue() {
    return value;
  }

}

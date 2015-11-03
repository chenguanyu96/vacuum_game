package sprites;

/** A class to represent a Wall. 
 * @author Guan Yu Chen
 *
 */
public class Wall extends Sprite{

  /** Creates a Wall object with a symbol, row value and column value associated with it.
   * 
   * @param symbol symbol for a Wall object
   * @param row    row value that the Wall is located
   * @param column column value that the Wall is located
   */
  public Wall(char symbol, int row, int column) {
    super(symbol, row, column);
  }

}

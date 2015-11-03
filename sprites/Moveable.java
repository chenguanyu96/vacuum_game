package sprites;

/** An interface in order to make a Sprite object moveable.
 * @author Guan Yu Chen
 *
 */
public interface Moveable {

  /** Moves an object that implements the type Moveable to a certain row value and column value.
   * 
   * @param row    row value to be moved to
   * @param column column value to moved to
   */
  public void moveTo(int row, int column);
}

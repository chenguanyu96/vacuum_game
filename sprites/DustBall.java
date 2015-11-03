package sprites;

/** A class that represents a DustBall.
 * @author Guan Yu Chen
 *
 */
public class DustBall extends Dirt implements Moveable{

  /** Creates a DustBall with the symbol associated with it, row value, column value, and the value.
   * 
   * @param symbol the symbol that represents a DustBall
   * @param row    the row value of where the DustBall is positioned
   * @param column the column value of where the DustBall is positioned
   * @param value  the value scored if a DustBall is cleared
   */
  public DustBall(char symbol, int row, int column, int value) {
    super(symbol, row, column, value);
  }

  /** Moves the DustBall to the correct row value and column value. */
  @Override
  public void moveTo(int row, int column) {
    super.row = row;
    super.column = column;
  }

}

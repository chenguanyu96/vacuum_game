package sprites;

import game.Constants;

/** A class to represent a Vacuum.
 * @author Guan Yu Chen
 *
 */
public class Vacuum extends Sprite implements Moveable{

  private int score = Constants.INIT_SCORE;
  private int capacity;
  private int fullness;
  private Sprite under;

  /** Creates a Vacuum object that has a symbol associated with it, row value, column value and
   * a capacity.
   * 
   * @param symbol   symbol associated with the vacuum
   * @param row      row value of the column position
   * @param column   column value of the vacuum position
   * @param capacity capacity of the vacuum
   */
  public Vacuum(char symbol, int row, int column, int capacity) {
    super(symbol, row, column);
    this.capacity = capacity;
  }

  /** Moves the vacuum to the specified row value and column value. */
  @Override
  public void moveTo(int row, int column) {
    this.row = row;
    this.column = column;
  }

  /** Returns True iff the clean was successful (score and fullness incremented), otherwise false
   * is returned.
   * 
   * @param score the score to be incremented by with the original score
   * @return      true iff the clean was successful, otherwise false
   */
  public boolean clean(int score) {
    if (fullness < capacity) {
      this.score += score;
      fullness += Constants.FULLNESS_INC;
      return true;
    }
    return false;
  }

  /** Empties the vacuum; sets fullness = 0. */
  public void empty() {
    fullness = 0;
  }

  /** Returns the value stored in the variable score.
   * 
   * @return the score
   */
  public int getScore() {
    return score;
  }

  /** Sets the value stored in the variable under.
   * 
   * @param under the Sprite object that is under another object of type Sprite
   * @see   #getUnder()
   */
  public void setUnder(Sprite under) {
    this.under = under;
  }

  /** Returns the value stored in the variable under.
   * 
   * @return under
   * @see    #setUnder(Sprite)
   */
  public Sprite getUnder() {
    return under;
  }

}

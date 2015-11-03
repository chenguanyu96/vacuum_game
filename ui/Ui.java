package ui;

/**
 * An interface that represents the methods that are need for object of type UI.
 * @author Guan Yu Chen
 *
 */
public interface Ui {

  /** Launches the game either for console or GUI. */
  public void launchGame();

  /** Displays the winner of the game, in case of GUI, in a pop-up window with a "OK" button, and in
   * case of text, it displays it after the game has ended on the last line.
   */
  public void displayWinner();
}

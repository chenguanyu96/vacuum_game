package ui;

import game.Constants;
import game.VacuumGame;

import java.util.Scanner;

/**
 * A class that is creates a text UI for the user play the game in the console.
 * @author Guan Yu Chen
 *
 */
public class TextUi implements Ui{

  private VacuumGame game;

  /** Creates a TextUI object in order to launch the game.
   * 
   * @param game the VacuumGame object
   */
  public TextUi(VacuumGame game) {
    super();
    this.game = game;
  }

  /** Launches the TextUI in the console and allows user to play the game. */
  @Override
  public void launchGame() {
    Scanner sc = new Scanner(System.in);
    /* Displays the title of the game, file path to read map, and the controls for Vacuum 1 and 
     * Vacuum 2.
     */
    System.out.println("******************************");
    System.out.println("-----------Vacuum Game--------");
    System.out.println("******************************");
    System.out.println("Filepath: " + Constants.FILENAME);
    System.out.println("Controls for Vacuum 1 (Player 1): W - Move Up");
    System.out.println("                                  S - Move Down");
    System.out.println("                                  A - Move Left");
    System.out.println("                                  D - Move Right\n");
    System.out.println("Controls for Vacuum 2 (Player 2): I - Move Up");
    System.out.println("                                  K - Move Down");
    System.out.println("                                  J - Move Left");
    System.out.println("                                  L - Move Right");
    
    // Launches the game, and handles the user interactions
    do {
      System.out.println(game.getGrid());
      getUserInput(sc);
    } while (!game.gameOver());

    sc.close();
  }
  
  /** Gets the user input, if it is valid, the player moves, if it is not valid, it will ask user 
   * for the correct input until the correct . 
   */
  private void getUserInput(Scanner sc) {
    // Stores the next move as a String in order to check if it is valid
    String nextMove;
    
    // Gets the next move from the user
    System.out.print("Please enter your move: ");
    nextMove = sc.nextLine();
    
    // Moves the desired vacuum if possible
    boolean didMove = game.move(nextMove.charAt(0));
    
    /* If the user entered an invalid key, the user have to enter a valid key in order for the game
     * to proceed.
     */
    while (didMove == false) {
      System.out.print("Please try again. ");
      nextMove = sc.nextLine();
      didMove = game.move(nextMove.charAt(0));
    }
  }

  /** Displays the winner of the game when the game is over. */
  @Override
  public void displayWinner() {
    int won = game.getWinner();
    
    // Return message for the user, declaring winner or a tie
    String message;
    
    // Checking values that is returned to determine who won or if it is a tie
    if (won == 0) {
      message = "It's a tie!";
    } else if (won == 1) {
      message = "Congratulations Player 1! You won the game with a score of "
          + game.getVacuumOne().getScore() + ".";
    } else { 
      message = "Congratulations Player 2! You won the game with a score of "
          + game.getVacuumTwo().getScore() + ".";
    }
    
    // Prints out the final message, to signify the game is over.
    System.out.println(message);
  }

}

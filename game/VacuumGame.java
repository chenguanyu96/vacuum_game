package game;

import game.Constants;

import sprites.CleanHallway;
import sprites.Dirt;
import sprites.Dumpster;
import sprites.DustBall;
import sprites.Sprite;
import sprites.Vacuum;
import sprites.Wall;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/** A class that represents the basic functionality of the vacuum game.
 * This class is responsible for performing the following operations:
 * 1. At creation, it initializes the instance variables used to store the
 *    current state of the game.
 * 2. When a move is specified, it checks if it is a legal move and makes the
 *    move if it is legal.
 * 3. It reports information about the current state of the game when asked.
 */
public class VacuumGame {

  // A random number generator to move the DustBalls
  private Random random;

  // the grid
  private Grid<Sprite> grid;

  // the first player
  private Vacuum vacuum1;

  /// the second player
  private Vacuum vacuum2;

  // the dirt (both static dirt and mobile dust balls)
  private List<Dirt> dirts;

  // the dumpsters
  private List<Dumpster> dumpsters;

  /** Creates a new VacuumGame that corresponds to the given input text file.
   * Assumes that the input file has one or more lines of equal lengths, and
   * that each character in it (other than newline) is a character that 
   * represents one of the sprites in this game.
   * 
   * @param layoutFileName path to the input grid file
   */
  public VacuumGame(String layoutFileName) throws IOException {
    dirts = new ArrayList<Dirt>();
    dumpsters = new ArrayList<Dumpster>();
    random = new Random();

    // open the file, read the contents, and determine dimensions of the grid
    int[] dimensions = getDimensions(layoutFileName);
    grid = new ArrayGrid<Sprite>(dimensions[0], dimensions[1]);

    // open the file again, read the contents, and store them in grid
    Scanner sc = new Scanner(new File(layoutFileName));

    /* INITIALIZE THE GRID HERE
     * 
     * Sets the line; points to the current line in the file, sets it to the
     * first line of the file.
     */
    String line = sc.nextLine();
    while (sc.hasNextLine()) {

      // Adds the contents of the file to the grid
      for (int i = 0; i < dimensions[0] - 1; i++) {
        for (int j = 0; j < dimensions[1]; j++) {

          /* Creates an object of type Sprite and adds it to the 
           * grid; ArrayGrid<Sprite>.
           */
          grid.setCell(i, j, makeSprite(line.charAt(j), i, j));
        }

        // Sets line to the next line in the file.
        line = sc.nextLine();
      }
    }
    
    // Adds the last line of the file to the grid.
    for (int j = 0; j < dimensions[1]; j++) {
      grid.setCell(dimensions[0] - 1, j, makeSprite(line.charAt(j), dimensions[0] - 1, j));
    }
    sc.close();
  }

  /** Returns the dimensions of the grid in the file named layoutFileName.
   * 
   * @param layoutFileName path of the input grid file
   * @return an array      [numRows, numCols], where numRows is the number of rows 
   *                       and numCols is the number of columns in the grid that
   *                       corresponds to the given input grid file
   * @throws IOException   if cannot open file layoutFileName
   */
  private int[] getDimensions(String layoutFileName) throws IOException {       

    Scanner sc = new Scanner(new File(layoutFileName));

    // Find the number of columns.
    String nextLine = sc.nextLine();
    int numCols = nextLine.length();

    int numRows = 1;

    // Find the number of rows.
    while (sc.hasNext()) {
      numRows++;
      nextLine = sc.nextLine();
    }

    sc.close();
    return new int[]{numRows, numCols};
  }

  /** Returns the Sprite that associate with each defined symbols.
   * 
   * @param item defined symbol associated with an Sprite object
   * @param row  row number item is located
   * @param col  column number item is located
   * @return     sprite object associated with the item
   */
  private Sprite makeSprite(char item, int row, int col) {
    // Checks if item is CleanHallway
    if (item == Constants.CLEAN) {
      return new CleanHallway(item, row, col);
      
      // Checks if item a Dirt
    } else if (item == Constants.DIRT) {
      
      // Adds Dirt object to dirts to keep track of how many dirts are on the grid.
      dirts.add(new Dirt(item, row, col, Constants.DIRT_SCORE));
      return new Dirt(item, row, col, Constants.DIRT_SCORE);
      
      // Checks if item is a Dumpster
    } else if (item == Constants.DUMPSTER) {
      
      /* Adds Dumpster object to dumpsters to keep track of how many dumpsters
       * are on the grid.
       */
      dumpsters.add(new Dumpster(item, row, col));
      return new Dumpster(item, row, col);
      
      // Checks if item is a DustBall
    } else if (item == Constants.DUST_BALL) {
      
      /* Adds DustBall object to dirts to keep track of how many DustBalls are
       * on the grid.
       */
      dirts.add(new DustBall(item, row, col, Constants.DUST_BALL_SCORE));
      return new DustBall(item, row, col, Constants.DUST_BALL_SCORE);
      
      // Checks if item is Player 1; Vacuum 1
    } else if (item == Constants.P1) {
      vacuum1 = new Vacuum(item, row, col, Constants.CAPACITY);
      return vacuum1;
      
      // Checks if item is Player 2; Vacuum 2
    } else if (item == Constants.P2) {
      vacuum2 = new Vacuum(item, row, col, Constants.CAPACITY);
      return vacuum2;
      
      // Item is a Wall, returns the Wall object
    } else {
      return new Wall(Constants.WALL, row, col);
    }
  }

  /** Returns the grid.
   * 
   * @return the grid; ArrayGrid
   */
  public Grid<Sprite> getGrid() {
    return grid;
  }

  /** Returns vacuum1.
   * 
   * @return vacuum1
   */
  public Vacuum getVacuumOne() {
    return vacuum1;
  }

  /** Returns vacuum2.
   * 
   * @return vacuum2
   */
  public Vacuum getVacuumTwo() {
    return vacuum2;
  }

  /** Returns the number of rows that grid has.
   * 
   * @return number of rows that grid has
   * @see    ArrayGrid#getNumRows()
   */
  public int getNumRows() {
    return grid.getNumRows();
  }

  /** Returns the number of columns that grid has.
   * 
   * @return number of columns that grid has
   * @see    ArrayGrid#getNumColumns()
   */
  public int getNumColumns() {
    return grid.getNumColumns();
  }

  /** Returns the Sprite that in the specified cell.
   * 
   * @param row cell's Sprite to be returned row number
   * @param col cell's Sprite to be returned column number
   * @return    Sprite at the specified cell
   */
  public Sprite getSprite(int row, int col) {
    return grid.getCell(row, col);
  }

  /** Returns true iff it is a valid move character, otherwise false. Moves
   * the specified player and moves the Dustballs randomly regardless if
   * player moved or not.
   * 
   * @param nextMove a character that commands a player to move
   * @return         true if character is valid, otherwise false
   */
  public boolean move(char nextMove) {
    /* Player 1 (Vacuum 1):
     * Key detection for Vacuum 1: 
     *    - Move Up: W
     *    - Move Down: S
     *    - Move Left: A
     *    - Move Right: D
     */
    if (nextMove == Constants.P1_UP) {
      if (canMoveUd(vacuum1, Constants.UP) == true) {
        vacuumMoveUd(vacuum1, Constants.UP);
      }
      dustBallsMove(vacuum1);
      return true;
    } else if (nextMove == Constants.P1_DOWN) {
      if (canMoveUd(vacuum1, Constants.DOWN) == true) {
        vacuumMoveUd(vacuum1, Constants.DOWN);
      }
      dustBallsMove(vacuum1);
      return true;
    } else if (nextMove == Constants.P1_LEFT) {
      if (canMoveRl(vacuum1, Constants.LEFT) == true) {
        vacuumMoveRl(vacuum1, Constants.LEFT);
      }
      dustBallsMove(vacuum1);
      return true;
    } else if (nextMove == Constants.P1_RIGHT) {
      if (canMoveRl(vacuum1, Constants.RIGHT) == true) {
        vacuumMoveRl(vacuum1, Constants.RIGHT);
      }
      dustBallsMove(vacuum1);
      return true;
    } 
    
    /* Player 2 (Vacuum 2):
     * Key detection for Vacuum 2:
     *     - Move Up: I
     *     - Move Down: K
     *     - Move Left: J
     *     - Move Right: L
     */
    if (nextMove == Constants.P2_UP) {
      if (canMoveUd(vacuum2, Constants.UP) == true) {
        vacuumMoveUd(vacuum2, Constants.UP);
      }
      dustBallsMove(vacuum2);
      return true;
    } else if (nextMove == Constants.P2_DOWN) {
      if (canMoveUd(vacuum2, Constants.DOWN) == true) {
        vacuumMoveUd(vacuum2, Constants.DOWN);
      }
      dustBallsMove(vacuum2);
      return true;
    } else if (nextMove == Constants.P2_LEFT) {
      if (canMoveRl(vacuum2, Constants.LEFT) == true) {
        vacuumMoveRl(vacuum2, Constants.LEFT);
      }
      dustBallsMove(vacuum2);
      return true;
    } else if (nextMove == Constants.P2_RIGHT) {
      if (canMoveRl(vacuum2, Constants.RIGHT) == true) {
        vacuumMoveRl(vacuum2, Constants.RIGHT);
      }
      dustBallsMove(vacuum2);
      return true;
    } 
    return false;
  }
  
  /** Returns True iff the player is allowed to move in the direction (up/down) specified., 
   * otherwise false.
   * 
   * @param player    the player that wants to move
   * @param direction the constant that is associated with the direction
   * @return          true if possible, otherwise false
   * @see             Vacuum#getColumn()
   * @see             Vacuum#getRow()
   */
  private boolean canMoveUd(Vacuum player, int direction) {
    int temp = player.getRow() + direction;
    
    // Checks if there is a Wall or Vacuum in the cell that the player might move to.
    if (player.getRow() < grid.getNumRows() 
        && (getSprite(temp, player.getColumn()).getSymbol() != Constants.WALL)
        && ((getSprite(temp, player.getColumn()).getSymbol() != Constants.P1) 
        && (getSprite(temp, player.getColumn()).getSymbol() != Constants.P2))) {
      return true;
      
      // Returns false if the above condition is not met.
    } else {
      return false;
    }
  }

  /** Returns True iff the player is allowed to move in the direction (right/left) specified,
   * otherwise false.
   * 
   * @param player    the player that wants to move
   * @param direction the constant that is associated with the direction
   * @return          true if possible, otherwise false
   * @see             Vacuum#getRow()
   * @see             Vacuum#getColumn()
   */
  private boolean canMoveRl(Vacuum player, int direction) {
    int temp = player.getColumn() + direction;
    
    // Checks if there is a Wall or Vacuum in the cell that the player might move to.
    if (player.getColumn() < grid.getNumColumns() 
        && (getSprite(player.getRow(), temp).getSymbol() != Constants.WALL)
        && ((getSprite(player.getRow(), temp).getSymbol() != Constants.P1)
        && (getSprite(player.getRow(), temp).getSymbol() != Constants.P2))) {
      return true;
      
      // Returns false if the above condition is not met.
    } else {
      return false;
    }
  }

  /** Changes the location of Vacuum 1 or Vacuum 2 either up or down. If a Vacuum lands on a Dirt, 
   * Dumpster, or DustBall, the appropriate actions will happen.
   * 
   * @param player       vacuum 1 or vacuum 2 to be moved
   * @param moveConstant key pressed to trigger movement
   * @see                #interactions(Vacuum)
   */
  private void vacuumMoveUd(Vacuum player, int moveConstant) {
    // Sets the object under a vacuum to be a CleanHallway if no object is set under a vacuum.
    if (player.getUnder() == null) {
      player.setUnder(new CleanHallway(Constants.CLEAN, player.getRow(), player.getColumn()));
    }
    
    /* Moves a vacuum to the desired location on the grid and sets the correct object to be under
     * the vacuum.
     */
    Sprite toSetUnder = getSprite(player.getRow() + moveConstant, player.getColumn());
    grid.setCell(player.getRow() + moveConstant, player.getColumn(), player);
    grid.setCell(player.getRow(), player.getColumn(), player.getUnder());
    player.setUnder(toSetUnder);
    
    // Changes player row and column to the new row and column value
    player.moveTo(player.getRow() + moveConstant, player.getColumn());
    
    // Completes the necessary interactions with the items on the grid that the player is over.
    interactions(player);
  }
 
  /** Changes the location of Vacuum 1 or Vacuum 2 either right or left. If a Vacuum lands on a
   * Dirt, Dumpster, or DustBall, the appropriate actions will happen.
   * 
   * @param player       vacuum 1 or vacuum 2 to be moved
   * @param moveConstant key pressed to trigger movement
   * @see                #interactions(Vacuum)
   */
  private void vacuumMoveRl(Vacuum player, int moveConstant) {
    // Sets the object under a vacuum to be a CleanHallway if no object is set under a vacuum.
    if (player.getUnder() == null) {
      player.setUnder(new CleanHallway(Constants.CLEAN, player.getRow(), player.getColumn()));
    }
    
    /* Moves a vacuum to the desired location on the grid and sets the correc to object to be under
     * the the vacuum.
     */
    Sprite toSetUnder = getSprite(player.getRow(), player.getColumn() + moveConstant);
    grid.setCell(player.getRow(), player.getColumn() + moveConstant, player);
    grid.setCell(player.getRow(), player.getColumn(), player.getUnder());
    player.setUnder(toSetUnder);
    
    // Changes player row and column to the new row and column value
    player.moveTo(player.getRow(), player.getColumn() + moveConstant);
    
    // Completes the necessary interactions with the items on the grid that the player is over
    interactions(player);
  }
  
  /** Performs the interaction with the vacuum and the items.
   * 
   * @param player the player that needs to perform the interactions
   * @see   Vacuum#clean(int)
   * @see   #removeFromDirts(Vacuum)
   */
  private void interactions(Vacuum player) {
    // Checks if the player (vacuum 1 or vacuum 2) is over a Dumpster
    if (player.getUnder().getSymbol() == Constants.DUMPSTER) {
      
      // Empties the vacuum so that the vacuum (player) can collect more Dirts and DustBalls
      player.empty();
      
      // Checks if the player (vacuum 1 or vacuum 2) is over a DustBall
    } else if (player.getUnder().getSymbol() == Constants.DUST_BALL) {
      
      /* Increments the fullness and the score of the player and return true iff it's possible,
       * otherwise false is returned.
       */
      boolean isclean = player.clean(Constants.DUST_BALL_SCORE);

      /* If incrementing the fullness and score is successful, it removes Dirt from dirts 
       * (ArrayList) and changes the cell to a CleanHallway.
       */
      if (isclean == true) {
        
        // Removes the dirt from dirts (ArrayList)
        removeFromDirts(player);
        
        // Changes the cell to a CleanHallway
        player.setUnder(new CleanHallway(Constants.CLEAN, player.getRow(), player.getColumn()));
      }
      
      // Checks if the player is over a Dirt object
    } else if (player.getUnder().getSymbol() == Constants.DIRT) {
      
      /* Increments the fullness and the score of the player and return true iff it's possible,
       * otherwise false is returned.
       */
      boolean isClean = player.clean(Constants.DIRT_SCORE);
      
      /* If incrementing the fullness and score is successful, it removes Dirt from dirts 
       * (ArrayList) and changes the cell to a CleanHallway.
       */
      if (isClean == true) {
        
        // Removes the dirt from dirts (ArrayList)
        removeFromDirts(player);
        
        // Changes the cell to a CleanHallway
        player.setUnder(new CleanHallway(Constants.CLEAN, player.getRow(), player.getColumn()));
      }
    }
  }
  
  /** Returns True iff the Sprite is already tracked, otherwise it returns False if the Sprite
   * object is not tracked. 
   * 
   * @param row    row value of the Sprite object
   * @param column column value of the Sprite object
   * @param sprite the symbol that is associated with the Sprite object
   * @return       true if tracked, otherwise false
   */
  private boolean isDirty(int row, int column, char sprite) {
    for (Sprite item: dirts) {
      if (item.getRow() == row && item.getColumn() == column 
          && item.getSymbol() == sprite) {
        return true;
      }
    }
    return false;
  }

  /** Removes the corresponding Dirt or DustBall object from the ArrayList dirts in order to keep
   * track of the number of DustBalls and Dirts that are on the grid.
   * 
   * @param player the player that involved
   * @see   Vacuum#getColumn()
   * @see   Vacuum#getRow()
   */
  private void removeFromDirts(Vacuum player) {
    /* Iterates through dirts and compares the row and column value of every Sprite with the
     * current row and column value of the player. 
     */
    for (int i = 0; i < dirts.size(); i++) {
      
      // Checks if the row and column value matches, iff it does, the item is deleted from dirts.
      if (dirts.get(i).getRow() == player.getRow() 
          && dirts.get(i).getColumn() == player.getColumn()) {
        dirts.remove(i);
      }
    }
  }
  
  /** Returns True iff the DustBall could move in up or down direction.
   * 
   * @param item      the item that is suppose to be a DustBall
   * @param direction the direction that the DustBall is suppose to move in
   * @return          true iff it can move, otherwise false
   */
  private boolean canDustBallMoveUd(Sprite item, int direction) {
    
    // Test if the conditions that the DustBall could move are true
    if ((getSprite(item.getRow() + direction, item.getColumn()).getSymbol() == Constants.DIRT)
        || getSprite(item.getRow() + direction, item.getColumn()).getSymbol() == Constants.CLEAN) {
      return true;
    }
    
    // Returns false if above conditions are true.
    return false;
  }

  /** Returns True iff a move possible for a DustBall on the grid, otherwise false is returned if
   * the move is not possible.
   * 
   * @param item      item to be moved, likely to be a DustBall
   * @param direction direction to be moved in
   * @return          true if move is possible, otherwise false
   */
  private boolean canDustBallMoveRl(Sprite item, int direction) {
    // Checks if conditions to be DustBall to be able to move is true, if true, true is returned
    if ((getSprite(item.getRow(), item.getColumn() + direction).getSymbol() == Constants.DIRT)
        || getSprite(item.getRow(), item.getColumn() + direction).getSymbol() == Constants.CLEAN) {
      return true;
    }
    
    // If the above condition is false, false is returned.
    return false;
  }

  /**
   * Moves the DustBalls on the grid randomly. DustBalls can be moved to any cell other then
   * Wall, Vacuum, Dumpster or on a DustBall itself.
   */
  private void dustBallsMove(Vacuum player) {
    /* Indexed for loop to iterate through the Dirt ArrayList so that it can be modified while in
     * iteration.
     */
    for (int i = 0; i < dirts.size(); i++) {
      
      // Makes the random object in order to generate the random number between 0 and 3.
      int randDirection = random.nextInt(4);
      
      // Checks if the symbol is a BustBall
      if (dirts.get(i).getSymbol() == Constants.DUST_BALL) {
        
        /* If the DustBall is able to move in the up direction, this condition will be true and the
         * method moveDustBallUd will run.
         */
        if (randDirection == 0 && canDustBallMoveUd(dirts.get(i), Constants.UP) == true) {
          moveDustBallUd(player, i, Constants.UP);
          
          /* If the DustBall is able to move in the down direction, this condition will be true and
           * the method moveDustBallUd will run.
           */
        } else if (randDirection == 1 && canDustBallMoveUd(dirts.get(i), Constants.DOWN) == true) {
          moveDustBallUd(player, i, Constants.DOWN);
          
          /* If the Dustball is able to move in the left direction, this condition will be true and
           * the method moveDustBallRl will run. 
           */
        } else if (randDirection == 2 && canDustBallMoveRl(dirts.get(i), Constants.LEFT) == true) {
          moveDustBallRl(player, i, Constants.LEFT);
          
          /* If the DustBall is able to move in the right direction, this condition will be true
           * and the method moveDustBallRl will run. 
           */
        } else if (randDirection == 3 && canDustBallMoveRl(dirts.get(i), Constants.RIGHT) == true) {
          moveDustBallRl(player, i, Constants.RIGHT);
        }
      }
    }
  }
  
  /** Moves the DustBalls up or down if the random direction generated tells it to move up or down.
   * 
   * @param player    the player that triggered the DustBall movement
   * @param index     index in the dirts ArrayList that the DustBall is at
   * @param direction the direction that the DustBall has to move; up or down
   * @see   #moveDustBallRl(Vacuum, int, int)
   */
  private void moveDustBallUd(Vacuum player, int index, int direction) {
    // Checks if the cell has a player on it at the same as DustBall.
    if (getSprite(dirts.get(index).getRow(), dirts.get(index).getColumn()).getSymbol() 
        == player.getSymbol()) {
      
      // If the cell has a player on it, set the Dirt to be under the player
      player.setUnder(new DustBall(Constants.DIRT, dirts.get(index).getRow(), 
          dirts.get(index).getColumn(), Constants.DIRT_SCORE));
      
      /* If the cell doesn't have a player on it, set the cell to contain a Dirt so that if a player
       * lands on it, it can pick up the Dirt.
       */
    } else {
      grid.setCell(dirts.get(index).getRow(), dirts.get(index).getColumn(), 
          new Dirt(Constants.DIRT, dirts.get(index).getRow(), 
              dirts.get(index).getColumn(), Constants.DIRT_SCORE));
    }
    
    /* Checks if the current Dirt is already being tracked, if not add it to the ArrayList dirts so
     * that it is tracked.
     */
    if (isDirty(dirts.get(index).getRow(), dirts.get(index).getColumn(), Constants.DIRT) == false) {
      dirts.add(new Dirt(Constants.DIRT, dirts.get(index).getRow(), dirts.get(index).getColumn(), 
            Constants.DIRT_SCORE));
    }
    
    // Moves the DustBall to the new coordinates in the correct direction.
    ((DustBall) dirts.get(index)).moveTo(dirts.get(index).getRow() + direction, 
        dirts.get(index).getColumn());
    
    // Moves the DustBall to the new coordinate on the grid.
    grid.setCell(dirts.get(index).getRow(), dirts.get(index).getColumn(), 
        new DustBall(Constants.DUST_BALL, dirts.get(index).getRow(), dirts.get(index).getColumn(),
            Constants.DUST_BALL_SCORE));
  }
  
  /** Moves the DustBall either in the right or left direction based on a random direction 
   * generator.
   * 
   * @param index     the index of DustBall in the ArrayList dirts
   * @param direction direction that the DustBall is moving 
   * @see   #moveDustBallUd(Vacuum, int, int)
   */
  private void moveDustBallRl(Vacuum player, int index, int direction) {
    // Checks if the player and DustBall are on the same cell.
    if (getSprite(dirts.get(index).getRow(), dirts.get(index).getColumn()).getSymbol() 
        == player.getSymbol()) {
      
      /* Sets Dirt object to be the object under the vacuum, so that if the player and the DustBall
       * moves away, the cell becomes a Dirt.
       */
      player.setUnder(new DustBall(Constants.DIRT, dirts.get(index).getRow(), 
          dirts.get(index).getColumn(), Constants.DIRT_SCORE));
      
      /* If the player is not on the same cell as the DustBall, sets the cell that the DustBall was
       * just on to a Dirt object
       */
    } else {
      grid.setCell(dirts.get(index).getRow(), dirts.get(index).getColumn(), 
          new Dirt(Constants.DIRT, dirts.get(index).getRow(), 
              dirts.get(index).getColumn(), Constants.DIRT_SCORE));
    }
    
    // Adds the Dirt object to the ArrayList dirts if it is not there.
    if (isDirty(dirts.get(index).getRow(), dirts.get(index).getColumn(), Constants.DIRT) == false) {
      dirts.add(new Dirt(Constants.DIRT, dirts.get(index).getRow(), dirts.get(index).getColumn(), 
            Constants.DIRT_SCORE));
    }
    
    // Moves the DustBall to the new location and movees the DustBall on the grid.
    ((DustBall) dirts.get(index)).moveTo(dirts.get(index).getRow(), 
        dirts.get(index).getColumn() + direction);
    grid.setCell(dirts.get(index).getRow(), dirts.get(index).getColumn(), 
        new DustBall(Constants.DUST_BALL, dirts.get(index).getRow(), dirts.get(index).getColumn(),
        Constants.DUST_BALL_SCORE));
  }
  
  /** Returns true iff the state of the grid has no more dirts and DustBalls,
   * otherwise false.
   * 
   * @return true iff the game is over, otherwise false
   */
  public boolean gameOver() {
    if (dirts.size() == 0) {
      return true;
    } else {
      return false;
    }
  }

  /** Returns the number of the player that is winner.
   * 
   * @return the number of the player that is the winner
   */
  public int getWinner() {
    if (vacuum1.getScore() > vacuum2.getScore()) {
      return Constants.VACUUM_ONE_WINS;
    } else if (vacuum1.getScore() < vacuum2.getScore()) {
      return Constants.VACUUM_TWO_WINS;
    } else {
      return Constants.TIE;
    }
  }
}

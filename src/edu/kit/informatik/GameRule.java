/**
 * 
 */
package edu.kit.informatik;

/**
 * @author Andreas Schmider
 *
 */
public abstract class GameRule implements Connect6GameRules {

  /** checks if the place move is allowed
   * 
   * @param i column
   * @param j row
   * @param gB gameboard
   * @return allowed true, if the move is allowed
   */
  public abstract boolean checkAllowedPlaceRules(int i, int j, GameBoard gB);

  /** checks if six in a row
   * 
   * @param compactArray with both x and y positions
   * @param piece field content (playermark)
   * @param gI gameinfo
   * @param gB gameboard 
   * @return win true, if the player has won
   */
  public abstract boolean checkWin(int[] compactArray, String piece, GameInfo gI, GameBoard gB);

  /** checks if every field is occcupied
   * 
   * @param gB gameboard
   * @param gI gameinfo
   * @return full true, if board is full
   */
  public boolean checkFullBoard(GameBoard gB, GameInfo gI) {
    boolean b = false;
    if (gI.getTurn() == gB.getBoardSize() * gB.getBoardSize()) {
      b = true;
    }
    return b;
  }
}

package edu.kit.informatik.gamerules;

import edu.kit.informatik.Command;
import edu.kit.informatik.models.GameInfo;
import edu.kit.informatik.models.Player;
import edu.kit.informatik.models.RectangularGameBoard;

/**
 * @author Andreas Schmider
 */
public abstract class BoardGameRule implements ConnectGameRules {

    /**
     * checks if the place move is allowed
     *
     * @param row    row
     * @param column column
     * @param gB     gameboard
     * @return allowed true, if the move is allowed
     */
    public abstract boolean checkAllowedPlacement(int row, int column, RectangularGameBoard gB);

    /**
     * checks if six in a row
     *
     * @param compactArray with both x and y positions
     * @param piece        field content (playermark)
     * @param gI           gameinfo
     * @param gB           gameboard
     * @return win true, if the player has won
     */
    public abstract boolean checkWin(int[] compactArray, String piece, GameInfo gI, RectangularGameBoard gB);

    /**
     * checks if every field is occcupied
     *
     * @param gB gameboard
     * @param gI gameinfo
     * @return true, if board is full
     */
    public boolean checkFullBoard(RectangularGameBoard gB, GameInfo gI) {
        return gI.getTurns() == gB.getBoardSize() * gB.getBoardSize();
    }

    public abstract Player checkWin(RectangularGameBoard board, Command command);

    /**
     * Should return the name of the GameBoardRule
     *
     * @return the name of the GameBoardRule
     */
    public abstract String toString();
}

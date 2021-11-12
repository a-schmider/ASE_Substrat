package edu.kit.informatik.gamerules;

import edu.kit.informatik.Command;
import edu.kit.informatik.models.GameInfo;
import edu.kit.informatik.models.Player;
import edu.kit.informatik.models.RectangularGameBoard;

/**
 * @author Andi
 */
public interface ConnectGameRules {

    /**
     * checks if the place move is allowed
     *
     * @param row    row
     * @param column column
     * @param gB     gameboard
     * @return allowed true, if the move is allowed
     */
    boolean checkAllowedPlacement(int row, int column, RectangularGameBoard gB);

    /**
     * checks if six in a row
     *
     * @param compactArray contents the four coordinates
     * @param piece        field content (playermark)
     * @param gI           gameinfo
     * @param gB           gameboard
     * @return win true, if the player has won
     */
    boolean checkWin(int[] compactArray, String piece, GameInfo gI, RectangularGameBoard gB);

    /**
     * checks if every field is occcupied
     *
     * @param gB gameboard
     * @param gI gameinfo
     * @return full true, if board is full
     */
    boolean checkFullBoard(RectangularGameBoard gB, GameInfo gI);

    /**
     * Returns the winning player or null if there is no winner
     *
     * @param command with last set stones to check only the differences from the last turn
     * @return the winning player or null if there is no winner
     */
    Player checkWin(RectangularGameBoard board, Command command);
}

package edu.kit.informatik;

import edu.kit.informatik.models.Connect6GameBoard;
import edu.kit.informatik.models.GameInfo;

/**
 * @author Andi
 */
public interface Connect6GameRules {

    /**
     * checks if the place move is allowed
     *
     * @param i  column
     * @param j  row
     * @param gB gameboard
     * @return allowed true, if the move is allowed
     */
    boolean checkAllowedPlaceRules(int i, int j, Connect6GameBoard gB);

    /**
     * checks if six in a row
     *
     * @param compactArray contents the four coordinates
     * @param piece        field content (playermark)
     * @param gI           gameinfo
     * @param gB           gameboard
     * @return win true, if the player has won
     */
    boolean checkWin(int[] compactArray, String piece, GameInfo gI, Connect6GameBoard gB);

    /**
     * checks if every field is occcupied
     *
     * @param gB gameboard
     * @param gI gameinfo
     * @return full true, if board is full
     */
    boolean checkFullBoard(Connect6GameBoard gB, GameInfo gI);
}

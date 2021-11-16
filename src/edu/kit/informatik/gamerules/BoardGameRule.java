package edu.kit.informatik.gamerules;

import edu.kit.informatik.Command;
import edu.kit.informatik.models.Player;
import edu.kit.informatik.models.RectangularGameBoard;


public abstract class BoardGameRule implements Winnable, PlaceableCheck {

    @Override
    public boolean checkAllowedPlacement(RectangularGameBoard board, int width, int height) {
        return false;
    }

    public abstract Player checkWin(RectangularGameBoard board, Command command);

    /**
     * Should return the name of the GameBoardRule
     *
     * @return the name of the GameBoardRule
     */
    public abstract String toString();
}

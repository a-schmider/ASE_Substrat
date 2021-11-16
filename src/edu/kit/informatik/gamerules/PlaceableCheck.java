package edu.kit.informatik.gamerules;

import edu.kit.informatik.models.RectangularGameBoard;

public interface PlaceableCheck {

    /**
     * checks if the place move is allowed
     *
     * @param width  width
     * @param height height
     * @param board  gameboard
     * @return allowed true, if the move is allowed
     */
    boolean checkAllowedPlacement(RectangularGameBoard board, int width, int height);
}

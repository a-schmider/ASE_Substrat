package dhbw.ase.core.gamerules;


import dhbw.ase.core.misc.SpecificConnect6CommandWithParameters;
import dhbw.ase.core.models.Player;
import dhbw.ase.core.models.RectangularGameBoard;


public abstract class BoardGameRule implements Winnable, PlaceableCheck {

    @Override
    public boolean checkAllowedPlacement(RectangularGameBoard board, int width, int height) {
        return false;
    }

    public abstract Player checkWin(RectangularGameBoard board, SpecificConnect6CommandWithParameters command);

    /**
     * Should return the name of the GameBoardRule
     *
     * @return the name of the GameBoardRule
     */
    public abstract String toString();
}

package dhbw.ase.core.gamerules;


import dhbw.ase.core.misc.SpecificConnect6CommandWithParameters;
import dhbw.ase.core.models.Player;
import dhbw.ase.core.models.RectangularGameBoard;


public interface Winnable {

    /**
     * Returns the winning player or null if there is no winner
     *
     * @param command with last set stones to check only the differences from the last turn
     * @return the winning player or null if there is no winner
     */
    Player checkWin(RectangularGameBoard board, SpecificConnect6CommandWithParameters command);
}

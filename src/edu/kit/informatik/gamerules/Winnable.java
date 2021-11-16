package edu.kit.informatik.gamerules;

import edu.kit.informatik.Command;
import edu.kit.informatik.models.Player;
import edu.kit.informatik.models.RectangularGameBoard;


public interface Winnable {

    /**
     * Returns the winning player or null if there is no winner
     *
     * @param command with last set stones to check only the differences from the last turn
     * @return the winning player or null if there is no winner
     */
    Player checkWin(RectangularGameBoard board, Command command);
}

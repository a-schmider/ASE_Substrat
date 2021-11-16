package edu.kit.informatik.gamerules.connect_four;

import edu.kit.informatik.Command;
import edu.kit.informatik.gamerules.BoardGameRule;
import edu.kit.informatik.models.Player;
import edu.kit.informatik.models.RectangularGameBoard;

public class ConnectFourDropGR extends BoardGameRule {

    @Override
    public boolean checkAllowedPlacement(RectangularGameBoard board, int width, int height) {
        return false;
    }

    @Override
    public Player checkWin(RectangularGameBoard board, Command command) {
        return null;
    }

    @Override
    public String toString() {
        return "Drop Four";
    }
}

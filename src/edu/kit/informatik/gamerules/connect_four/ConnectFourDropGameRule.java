package edu.kit.informatik.gamerules.connect_four;

import edu.kit.informatik.Command;
import edu.kit.informatik.models.Compass;
import edu.kit.informatik.models.Player;
import edu.kit.informatik.models.RectangularGameBoard;

public class ConnectFourDropGameRule extends ConnectFourGameRule {

    @Override
    public boolean checkAllowedPlacement(RectangularGameBoard board, int width, int height) {
        return false;
    }

    @Override
    public String toString() {
        return "Drop Four";
    }

    @Override
    public Player checkWin(RectangularGameBoard board, Command command) {
        return null;
    }

    @Override
    protected int getNextWidth(int width, Compass direction) throws NoSuchFieldException {
        return 0;
    }

    @Override
    protected int getNextHeight(int height, Compass direction) throws NoSuchFieldException {
        return 0;
    }
}

package dhbw.ase.core.gamerules.connect_four;


import dhbw.ase.core.misc.Command;
import dhbw.ase.core.models.Compass;
import dhbw.ase.core.models.Player;
import dhbw.ase.core.models.RectangularGameBoard;

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
    protected int getNextWidth(RectangularGameBoard board, int width, Compass direction) throws NoSuchFieldException {
        return 0;
    }

    @Override
    protected int getNextHeight(RectangularGameBoard board, int height, Compass direction) throws NoSuchFieldException {
        return 0;
    }
}

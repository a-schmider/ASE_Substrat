package dhbw.ase.core.gamerules.connect6;

import dhbw.ase.core.models.Compass;
import dhbw.ase.core.models.RectangularGameBoard;


public class Connect6StandardGameRule extends Connect6GameRule {

    public boolean checkAllowedPlacement(RectangularGameBoard board, int width, int height) {
        if (checkOnBoard(board, width, height)) {
            return board.getGameField(width, height).isFree();
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Standard";
    }

    @SuppressWarnings("DuplicatedCode")
    protected int getNextWidth(RectangularGameBoard board, int width, Compass direction) throws NoSuchFieldException {
        switch (direction) {
            case N, S -> {
                return width;
            }
            case NE, E, SE -> {
                return ++width;
            }
            case NW, W, SW -> {
                return --width;
            }
            default -> throw new NoSuchFieldException("No valid direction specified");
        }
    }

    @SuppressWarnings("DuplicatedCode")
    protected int getNextHeight(RectangularGameBoard board, int height, Compass direction) throws NoSuchFieldException {
        switch (direction) {
            case W, E -> {
                return height;
            }
            case NW, N, NE -> {
                return ++height;
            }
            case SW, S, SE -> {
                return --height;
            }
            default -> throw new NoSuchFieldException("No valid direction specified");
        }
    }
}

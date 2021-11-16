package edu.kit.informatik.gamerules.connect6;

import edu.kit.informatik.models.Compass;
import edu.kit.informatik.models.RectangularGameBoard;


public class Connect6StandardGR extends Connect6GameRule {

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
    protected int getNextWidth(int width, Compass direction) throws NoSuchFieldException {
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
            default -> throw new NoSuchFieldException("No direction specified");
        }
    }

    @SuppressWarnings("DuplicatedCode")
    protected int getNextHeight(int height, Compass direction) throws NoSuchFieldException {
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
            default -> throw new NoSuchFieldException("No direction specified");
        }
    }
}

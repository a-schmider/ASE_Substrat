package dhbw.ase.core.gamerules.connect6;

import dhbw.ase.core.models.Compass;
import dhbw.ase.core.models.RectangularGameBoard;

public class Connect6TorusGameRule extends Connect6GameRule {

    @Override
    protected boolean checkOnBoard(RectangularGameBoard board, int width, int height) {
        return true;
    }

    @Override
    protected int getNextWidth(RectangularGameBoard board, int width, Compass direction) throws NoSuchFieldException {
        switch (direction) {
            case N, S -> {
                return (((width % board.getBoardWidth()) + board.getBoardHeight()) % board.getBoardWidth());
            }
            case NE, E, SE -> {
                return (((++width % board.getBoardWidth()) + board.getBoardHeight()) % board.getBoardWidth());
            }
            case NW, W, SW -> {
                return (((--width % board.getBoardWidth()) + board.getBoardHeight()) % board.getBoardWidth());
            }
            default -> throw new NoSuchFieldException("No direction specified");
        }
    }

    @Override
    protected int getNextHeight(RectangularGameBoard board, int height, Compass direction) throws NoSuchFieldException {
        switch (direction) {
            case W, E -> {
                return (((height % board.getBoardHeight()) + board.getBoardHeight()) % board.getBoardHeight());
            }
            case NW, N, NE -> {
                return (((++height % board.getBoardHeight()) + board.getBoardHeight()) % board.getBoardHeight());
            }
            case SW, S, SE -> {
                return (((--height % board.getBoardHeight()) + board.getBoardHeight()) % board.getBoardHeight());
            }
            default -> throw new NoSuchFieldException("No direction specified");
        }
    }

    @Override
    public boolean checkAllowedPlacement(RectangularGameBoard board, int width, int height) {
        return board.getGameField(width, height).isFree();
    }

    @Override
    public String toString() {
        return "Torus";
    }

}

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
                return normalizeNextWidth(width, 0, board.getBoardWidth());
            }
            case NE, E, SE -> {
                return normalizeNextWidth(width, 1, board.getBoardWidth());
            }
            case NW, W, SW -> {
                return normalizeNextWidth(width, -1, board.getBoardWidth());
            }
            default -> throw new NoSuchFieldException("No valid direction specified");
        }
    }

    private int normalizeNextWidth(int width, int offset, int boardWidth) {
        return ((width + offset % boardWidth) + boardWidth) % boardWidth;
    }

    @Override
    protected int getNextHeight(RectangularGameBoard board, int height, Compass direction) throws NoSuchFieldException {
        switch (direction) {
            case W, E -> {
                return normalizeNextHeight(height, 0, board.getBoardWidth());
            }
            case NW, N, NE -> {
                return normalizeNextHeight(height, 1, board.getBoardWidth());
            }
            case SW, S, SE -> {
                return normalizeNextHeight(height, -1, board.getBoardWidth());
            }
            default -> throw new NoSuchFieldException("No valid direction specified");
        }
    }

    private int normalizeNextHeight(int height, int offset, int boardHeight) {
        return ((height + offset % boardHeight) + boardHeight) % boardHeight;
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

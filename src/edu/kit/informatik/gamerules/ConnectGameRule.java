package edu.kit.informatik.gamerules;

import edu.kit.informatik.Command;
import edu.kit.informatik.models.Compass;
import edu.kit.informatik.models.Player;
import edu.kit.informatik.models.RectangularGameBoard;

import java.util.EnumMap;
import java.util.Map;

public abstract class ConnectGameRule extends BoardGameRule {

    protected static int xInARowToWin = 10;


    protected boolean checkOnBoard(RectangularGameBoard board, int width, int height) {
        return width >= 0 && width < board.getBoardWidth() && height >= 0 && height < board.getBoardHeight();
    }

    public Player checkWin(RectangularGameBoard board, Command command) {
        Player player = board.getGameField(command.getParameters()[0], command.getParameters()[1]).getStone();

        if (hasSurroundingWinner(board, command.getParameters()[0], command.getParameters()[1])
                || hasSurroundingWinner(board, command.getParameters()[2], command.getParameters()[3])) {
            return player;
        }

        return null;
    }

    protected boolean hasSurroundingWinner(RectangularGameBoard board, int width, int height) {
        Map<Compass, Integer> surroundings = new EnumMap<>(Compass.class);
        surroundings.put(Compass.NW, checkNorthWest(board, width, height));
        surroundings.put(Compass.N, checkNorth(board, width, height));
        surroundings.put(Compass.NE, checkNorthEast(board, width, height));
        surroundings.put(Compass.W, checkWest(board, width, height));
        surroundings.put(Compass.E, checkEast(board, width, height));
        surroundings.put(Compass.SW, checkSouthWest(board, width, height));
        surroundings.put(Compass.S, checkSouth(board, width, height));
        surroundings.put(Compass.SE, checkSouthEast(board, width, height));

        return surroundings.get(Compass.NW) + surroundings.get(Compass.SE) + 1 >= xInARowToWin
                || surroundings.get(Compass.N) + surroundings.get(Compass.S) + 1 >= xInARowToWin
                || surroundings.get(Compass.NE) + surroundings.get(Compass.SW) + 1 >= xInARowToWin
                || surroundings.get(Compass.W) + surroundings.get(Compass.E) + 1 >= xInARowToWin;
    }

    protected int checkNorthWest(RectangularGameBoard board, int width, int height) {
        try {
            return checkDirection(board, width, height, 0, Compass.NW);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return -1;
    }

    protected int checkNorth(RectangularGameBoard board, int width, int height) {
        try {
            return checkDirection(board, width, height, 0, Compass.N);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return -1;
    }

    protected int checkNorthEast(RectangularGameBoard board, int width, int height) {
        try {
            return checkDirection(board, width, height, 0, Compass.NE);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return -1;
    }

    protected int checkWest(RectangularGameBoard board, int width, int height) {
        try {
            return checkDirection(board, width, height, 0, Compass.W);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return -1;
    }

    protected int checkEast(RectangularGameBoard board, int width, int height) {
        try {
            return checkDirection(board, width, height, 0, Compass.E);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return -1;
    }

    protected int checkSouthWest(RectangularGameBoard board, int width, int height) {
        try {
            return checkDirection(board, width, height, 0, Compass.SW);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return -1;
    }

    protected int checkSouth(RectangularGameBoard board, int width, int height) {
        try {
            return checkDirection(board, width, height, 0, Compass.S);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return -1;
    }

    protected int checkSouthEast(RectangularGameBoard board, int width, int height) {
        try {
            return checkDirection(board, width, height, 0, Compass.SE);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return -1;
    }

    protected int checkDirection(RectangularGameBoard board, int width, int height, int curInARow, Compass direction) throws NoSuchFieldException {
        Player currentPlayer = board.getGameField(width, height).getStone();

        int nextWidth = getNextWidth(width, direction);
        int nextHeight = getNextHeight(height, direction);

        boolean stillOnBoard = checkOnBoard(board, nextWidth, nextHeight);
        if (curInARow < xInARowToWin - 1 && stillOnBoard) {
            if (board.getGameField(nextWidth, nextHeight).getStone() == currentPlayer) {
                return checkDirection(board, nextWidth, nextHeight, ++curInARow, direction);
            } else {
                return curInARow;
            }
        } else {
            return curInARow;
        }
    }


    protected abstract int getNextWidth(int width, Compass direction) throws NoSuchFieldException;

    protected abstract int getNextHeight(int height, Compass direction) throws NoSuchFieldException;
}

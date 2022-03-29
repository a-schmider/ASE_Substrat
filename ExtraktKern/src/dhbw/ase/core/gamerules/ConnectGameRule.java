package dhbw.ase.core.gamerules;

import dhbw.ase.core.misc.SpecificConnect6CommandWithParameters;
import dhbw.ase.core.models.Compass;
import dhbw.ase.core.models.Player;
import dhbw.ase.core.models.RectangularGameBoard;

import java.util.*;

public abstract class ConnectGameRule extends BoardGameRule {

    protected static int xInARowToWin = 10;


    protected boolean checkOnBoard(RectangularGameBoard board, int width, int height) {
        return width >= 0 && width < board.getBoardWidth() && height >= 0 && height < board.getBoardHeight();
    }

    public Player checkWin(RectangularGameBoard board, SpecificConnect6CommandWithParameters command) {
        Player player = board.getGameField(command.getParameters()[0], command.getParameters()[1]).getPlayer();

        if (hasSurroundingWinner(board, command.getParameters()[0], command.getParameters()[1])
                || hasSurroundingWinner(board, command.getParameters()[2], command.getParameters()[3])) {
            return player;
        }

        return null;
    }

    protected boolean hasSurroundingWinner(RectangularGameBoard board, int width, int height) {
        Map<Compass, Integer> surroundings = countStonesForEightDirections(board, width, height);
        List<Integer> wins = lineUpToFourDirections(surroundings);
        List<Boolean> evaluatedResult = evaluateWin(wins);
        return ifAtLeastOneIsTrueFrom(evaluatedResult);
    }

    protected EnumMap<Compass, Integer> countStonesForEightDirections(RectangularGameBoard board, int width, int height) {
        EnumMap<Compass, Integer> surroundings = new EnumMap<>(Compass.class);
        for (Compass direction : Compass.values()) {
            try {
                surroundings.put(direction, countStonesForDirection(board, width, height, 0, direction));
            } catch (NoSuchFieldException e) {
                surroundings.put(direction, -1);
            }
        }
        return surroundings;
    }

    protected int countStonesForDirection(RectangularGameBoard board, int width, int height, int curInARow, Compass direction) throws NoSuchFieldException {
        Player currentPlayer = board.getGameField(width, height).getPlayer();

        int nextWidth = getNextWidth(board, width, direction);
        int nextHeight = getNextHeight(board, height, direction);

        boolean stillOnBoard = checkOnBoard(board, nextWidth, nextHeight);
        if (curInARow < xInARowToWin - 1 && stillOnBoard) {
            if (board.getGameField(nextWidth, nextHeight).getPlayer() == currentPlayer) {
                return countStonesForDirection(board, nextWidth, nextHeight, ++curInARow, direction);
            } else {
                return curInARow;
            }
        } else {
            return curInARow;
        }
    }

    protected ArrayList<Integer> lineUpToFourDirections(Map<Compass, Integer> surroundings) {
        int northSouthWin = surroundings.get(Compass.N) + surroundings.get(Compass.S) + 1;
        int westEastWin = surroundings.get(Compass.W) + surroundings.get(Compass.E) + 1;
        int southwestNortheastWin = surroundings.get(Compass.N) + surroundings.get(Compass.S) + 1;
        int northwestSoutheastWin = surroundings.get(Compass.N) + surroundings.get(Compass.S) + 1;

        ArrayList<Integer> wins = new ArrayList<>();
        wins.add(northSouthWin);
        wins.add(westEastWin);
        wins.add(southwestNortheastWin);
        wins.add(northwestSoutheastWin);
        return wins;
    }

    private List<Boolean> evaluateWin(Iterable<Integer> wins) {
        ArrayList<Boolean> evaluatedResult = new ArrayList<>();
        for (Integer win : wins) {
            if (win >= xInARowToWin) {
                evaluatedResult.add(true);
            } else {
                evaluatedResult.add(false);
            }
        }
        return evaluatedResult;
    }

    protected boolean ifAtLeastOneIsTrueFrom(Iterable<Boolean> container) {
        for (Boolean value : container) {
            if (value) {
                return true;
            }
        }
        return false;
    }


    protected abstract int getNextWidth(RectangularGameBoard board, int width, Compass direction) throws NoSuchFieldException;

    protected abstract int getNextHeight(RectangularGameBoard board, int height, Compass direction) throws NoSuchFieldException;
}

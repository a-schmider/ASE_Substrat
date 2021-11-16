package edu.kit.informatik;

import edu.kit.informatik.models.GameField;

public abstract class GameBoard {

    protected final GameField[][] board;

    protected GameBoard(int width, int height) {
        board = new GameField[width][height];
    }


    public int getBoardWidth() {
        return board.length;
    }

    public int getBoardHeight() {
        return board[0].length;
    }

    //TODO umstellen auf GameField
    public String getField(int width, int height) {
        return board[width][height].toString();
    }

    public GameField getGameField(int width, int height) {
        return board[width][height];
    }

}

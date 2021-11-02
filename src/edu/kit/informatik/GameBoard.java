package edu.kit.informatik;

import edu.kit.informatik.models.GameField;

public abstract class GameBoard {

    private final GameField[][] board;

    protected GameBoard(int width, int height) {
        this.board = new GameField[width][height];
    }

    public int getBoardSize() {
        return board.length;
    }

    //TODO umstellen auf GameField
    public String getField(int x, int y) {
        return board[x][y].toString();
    }

    public GameField getGameField(int x, int y) {
        return board[x][y];
    }


}

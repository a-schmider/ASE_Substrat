package edu.kit.informatik;

import edu.kit.informatik.models.GameField;

public abstract class GameBoard {

    private final GameField[][] board;

    protected GameBoard(int row, int column) {
        this.board = new GameField[row][column];
    }

    public int getBoardSize() {
        return board.length;
    }

    //TODO umstellen auf GameField
    public String getField(int row, int column) {
        return board[row][column].toString();
    }

    public GameField getGameField(int row, int column) {
        return board[row][column];
    }

}

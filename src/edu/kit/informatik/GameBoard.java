package edu.kit.informatik;

import edu.kit.informatik.models.GameField;

public abstract class GameBoard {

    protected final GameField[][] board;

    protected GameBoard(int rows, int columns) {
        board = new GameField[rows][columns];
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

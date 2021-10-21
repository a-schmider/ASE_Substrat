package edu.kit.informatik;

import edu.kit.informatik.models.GameField;

public abstract class GameBoard {

    private final GameField[][] board;

    protected GameBoard(int width, int height) {
        this.board = new GameField[width][height];
    }
}

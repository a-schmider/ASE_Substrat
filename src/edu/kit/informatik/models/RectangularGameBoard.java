package edu.kit.informatik.models;

public class RectangularGameBoard extends edu.kit.informatik.GameBoard {

    private final GameField[][] board;


    public RectangularGameBoard(int width, int height) {
        super(width, height);
        board = new GameField[width][height];
    }


    public boolean placeStone(int width, int height, Player player) {
        return board[width][height].placeStone(player);
    }
}

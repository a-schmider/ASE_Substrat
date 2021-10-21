package edu.kit.informatik.models;

public class RectangleGameBoard {

    private final GameField[][] board;


    public RectangleGameBoard(int width, int height) {
        board = new GameField[width][height];
    }


    public boolean placeStone(int width, int height, Player player) {
        return board[width][height].placeStone(player);
    }
}

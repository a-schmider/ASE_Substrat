package dhbw.ase.core.models;

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

    public GameField getGameField(int width, int height) {
        return board[width][height];
    }

}

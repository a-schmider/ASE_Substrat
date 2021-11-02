package edu.kit.informatik.models;

public class RectangularGameBoard extends edu.kit.informatik.GameBoard {

    private final GameField[][] board;


    public RectangularGameBoard(int width, int height) {
        super(width, height);
        board = new GameField[width][height];
        initGameBoard();
    }

    /**
     * initializes the board with "**"
     */
    public void initGameBoard() {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                board[row][col] = new GameField();
            }
        }
        board[0][1].placeStone(new Player());
    }


    public boolean placeStone(int width, int height, Player player) {
        return board[width][height].placeStone(player);
    }

    public Player getStateOfField(int width, int height) {
        return board[width][height].getStone();
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (GameField[] row : board) {
            for (GameField field : row) {
                builder.append(field.toString());
                builder.append(" ");
            }
            builder = new StringBuilder(builder.substring(0, builder.length() - 1));
            builder.append("\r\n");
        }

        return builder.toString();
    }

    public String getRowAsString(int row) {
        StringBuilder builder = new StringBuilder();
        for (GameField field : board[row]) {
            builder.append(field.toString());
            builder.append(" ");
        }
        builder = new StringBuilder(builder.substring(0, builder.length() - 1));
        builder.append("\r\n");
        return builder.toString();
    }

    public String getColumnAsString(int column) {
        StringBuilder builder = new StringBuilder();
        for (GameField[] row : board) {
            builder.append(row[column].toString());
            builder.append(" ");
        }
        builder = new StringBuilder(builder.substring(0, builder.length() - 1));
        builder.append("\r\n");
        return builder.toString();
    }
}

package edu.kit.informatik.models;

public class RectangularGameBoard extends edu.kit.informatik.GameBoard {

    private final GameField[][] board;


    public RectangularGameBoard(int row, int column) {
        super(row, column);
        board = new GameField[row][column];
        initGameBoard();
    }

    /**
     * initializes the board with "**"
     */
    public void initGameBoard() {
        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[row].length; column++) {
                board[row][column] = new GameField();
            }
        }
        board[0][1].placeStone(new Player());
    }


    public boolean placeStone(int row, int column, Player player) {
        return board[row][column].placeStone(player);
    }

    public String getFieldAsString(int row, int col) {
        return board[row][col].toString();
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

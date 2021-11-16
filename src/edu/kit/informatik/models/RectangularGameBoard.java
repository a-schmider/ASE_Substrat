package edu.kit.informatik.models;


//TODO bisher quadratisch
public class RectangularGameBoard extends edu.kit.informatik.GameBoard {


    public RectangularGameBoard(int rows, int columns) {
        super(rows, columns);
        initGameBoard();
    }


    public void initGameBoard() {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                board[row][col] = new GameField();
            }
        }
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

package edu.kit.informatik.models;

import edu.kit.informatik.gamerules.BoardGameRule;
import edu.kit.informatik.userinterface.Terminal;

/**
 * @author Andreas Schmider
 */

public class Connect6GameBoard extends RectangularGameBoard {

    private String[][] gameBoard;
    private int boardSize;

    /**
     * creates a two dimensional array, sets the length of the board and initializes
     * the board
     *
     * @param gameBoardSize gameboardsize
     */
    public Connect6GameBoard(int gameBoardSize) {
        super(gameBoardSize, gameBoardSize);//TODO nachschauen ob richtig
        this.boardSize = gameBoardSize;
        this.gameBoard = new String[this.boardSize][boardSize];
        initGameBoard(this.boardSize);
    }

    /**
     * @return boardSize
     */
    public int getBoardSize() {
        return this.boardSize;
    }

    /**
     * @param row    coordinate
     * @param column coordinate
     * @return content of the field
     */
    public String getField(int row, int column) {
        return this.gameBoard[row][column];
    }

    /**
     * @return gameBoard[]
     */
    public String[][] getGameBoard() {
        return this.gameBoard;
    }

    /**
     * initializes the board with "**"
     *
     * @param boardSize boardsize
     */
    public void initGameBoard(int boardSize) {
        for (int i = 0; i < this.boardSize; i++) {
            for (int j = 0; j < this.boardSize; j++) {
                this.gameBoard[i][j] = "**";
            }
        }
    }

    /**
     * prints wanted row
     *
     * @param wantedRow wanted row (int)
     */
    public void rowPrint(int wantedRow) {
        if (wantedRow >= 0 || wantedRow < this.boardSize) {
            String line = "";
            for (int i = 0; i < this.boardSize; i++) {
                if (i != this.boardSize - 1) {
                    line = line + this.gameBoard[wantedRow][i] + " ";
                } else {
                    line = line + this.gameBoard[wantedRow][i];
                }
            }
            Terminal.printLine(line);
        } else {
            Terminal.printError("invalid Row");
        }
    }

    /**
     * prints wanted column
     *
     * @param wantedCol wanted column (int)
     */
    public void colPrint(int wantedCol) {
        if (wantedCol >= 0 || wantedCol < this.boardSize) {
            String line = "";
            for (int i = 0; i < this.boardSize; i++) {
                if (i != this.boardSize - 1) {
                    line = line + this.gameBoard[i][wantedCol] + " ";
                } else {
                    line = line + this.gameBoard[i][wantedCol];
                }
            }
            Terminal.printLine(line);
        } else {
            Terminal.printError("invalid Row");
        }
    }

    /**
     * prints complete board
     */
    public void boardPrint() {
        for (int i = 0; i < this.boardSize; i++) {
            rowPrint(i);
        }
    }

    /**
     * prints content of the field
     *
     * @param i column
     * @param j row
     */
    public void stateField(int i, int j) {
        Terminal.printLine(this.gameBoard[i][j]);
    }

    /**
     * resets the gameBoard
     */
    public void resetGameBoard() {
        initGameBoard(this.boardSize);
        Terminal.printLine("OK");
    }

    /**
     * fills the field with gamingPiece
     *
     * @param i             column
     * @param j             row
     * @param gamingPiece   playermark
     * @param boardGameRule gamerule
     */
    public void place(int i, int j, String gamingPiece, BoardGameRule boardGameRule) {
        this.gameBoard[i][j] = gamingPiece;
    }

}

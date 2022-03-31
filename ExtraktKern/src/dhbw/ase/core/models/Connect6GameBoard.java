package dhbw.ase.core.models;


/**
 * @author Andreas Schmider
 */

public class Connect6GameBoard extends RectangularGameBoard {

    /**
     * creates a squared RectangularGameBoard
     * throws error if gameBoardSize is lower than 7 or greater than 40
     *
     * @param gameBoardSize gameboardsize
     */
    public Connect6GameBoard(int gameBoardSize) {
        super(gameBoardSize, gameBoardSize);
        if (gameBoardSize < 7 || gameBoardSize > 40) {
            throw new IllegalArgumentException("size must be between 7 and 40");
        }
    }

}

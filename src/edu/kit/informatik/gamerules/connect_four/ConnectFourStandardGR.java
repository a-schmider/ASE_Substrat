package edu.kit.informatik.gamerules.connect_four;

import edu.kit.informatik.Command;
import edu.kit.informatik.gamerules.BoardGameRule;
import edu.kit.informatik.models.GameInfo;
import edu.kit.informatik.models.Player;
import edu.kit.informatik.models.RectangularGameBoard;

public class ConnectFourStandardGR extends BoardGameRule {

    @Override
    public boolean checkAllowedPlacement(int row, int column, RectangularGameBoard gB) {
        return false;
    }

    @Override
    public boolean checkWin(int[] compactArray, String piece, GameInfo gI, RectangularGameBoard gB) {
        return false;
    }

    @Override
    public boolean checkFullBoard(RectangularGameBoard gB, GameInfo gI) {
        return gI.getTurns() == gB.getBoardSize() * gB.getBoardSize();
        //TODO schauen wie richtig vererbt wird und implementieren indem auf GameArea zugegriffen wird und nicht null ist
    }

    @Override
    public Player checkWin(RectangularGameBoard board, Command command) {
        return null;
    }

    @Override
    public String toString() {
        return "Standard";
    }
}

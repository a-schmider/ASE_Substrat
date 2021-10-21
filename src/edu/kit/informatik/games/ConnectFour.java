package edu.kit.informatik.games;

import edu.kit.informatik.models.RectangleGameBoard;

public class ConnectFour extends TurnBasedGame {

    private final RectangleGameBoard board = new RectangleGameBoard(7, 6);

    public static void t() {
        System.out.println("c4");
    }

    @Override
    public String getName() {
        return "Connect Four";
    }

    @Override
    public void chooseVariation() {
        System.out.println("Connect4 Choose Variation");
    }

    @Override
    public void prepare() {
        super.prepare();
    }


}

package edu.kit.informatik.games;

import edu.kit.informatik.models.Player;
import edu.kit.informatik.userinterface.GUI;

public abstract class TurnBasedGame {


    private static GUI gui = new GUI();
    //TODO


    private boolean quited;
    private Player winner;


    public boolean isOver() {
        return quited;
    }

    /**
     * Returns the winning player or null if the game is not completed yet or was aborted
     *
     * @return winning player or null
     */
    public Player getWinner() {
        return winner;
    }


    //Diese Methoden sollten nicht einfach so ausfürhbar sein nur von GamePlayer
    void chooseVariation() {
        System.out.println("Choose Variation");
    }

    void prepare() {
        System.out.println("Prepare");
    }

    void makeTurn() {
        System.out.println("Make Turn");
        quited = true;
    }

    Player followUp() {
        gui.print("Returning to main menu\r\n");
        return winner;
    }
}

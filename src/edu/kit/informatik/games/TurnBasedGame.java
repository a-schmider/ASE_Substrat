package edu.kit.informatik.games;

import edu.kit.informatik.models.Player;

public abstract class TurnBasedGame {

    private boolean quited;
    private Player winner;


    public abstract String getName();

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

    //Diese Methoden solltne nicht einfach so ausfürhbar sein nur von GamePlayer
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
        System.out.println("followUp");
        return winner;
    }
}

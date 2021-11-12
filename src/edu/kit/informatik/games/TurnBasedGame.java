package edu.kit.informatik.games;

import edu.kit.informatik.TextRepository;
import edu.kit.informatik.models.Player;
import edu.kit.informatik.userinterface.GUI;

public abstract class TurnBasedGame {


    private static final GUI gui = new GUI();
    //TODO

    protected boolean quited;
    protected boolean finished;
    protected Player winner;


    /**
     * Returns true if the game is not finished yet
     *
     * @return true if the game is not finished yet
     */
    public final boolean isRunning() {
        return !finished;
    }

    public final boolean wasQuited() {
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
    void prepareSettings() {
        System.out.println("Choose Variation");
    }

    void makeTurn() {
        System.out.println("Make Turn");
        finished = true;
    }

    Player followUp() {
        gui.print(TextRepository.RETURN_TO_MAIN_MENU);
        return winner;
    }
}

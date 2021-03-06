package dhbw.ase.plugin.games;

import dhbw.ase.core.misc.TextRepository;
import dhbw.ase.core.models.Player;
import dhbw.ase.plugin.userinterface.GuiInterface;

public abstract class TurnBasedGame {

    protected boolean quited;
    protected boolean finished;
    protected Player winner;
    GuiInterface gui;

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

    protected abstract void prepareSettings();

    protected abstract void makeTurn();

    protected Player followUp() {
        gui.print(TextRepository.RETURN_TO_MAIN_MENU, false);
        return winner;
    }

    public final Player play() {
        prepareSettings();

        while (!wasQuited()) {
            makeTurn();
        }

        return followUp();
    }
}

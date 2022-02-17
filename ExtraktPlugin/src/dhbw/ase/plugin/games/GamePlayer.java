package dhbw.ase.plugin.games;

import dhbw.ase.core.models.Player;

public class GamePlayer {

    public static Player play(TurnBasedGame turnBasedGame) {
        turnBasedGame.prepareSettings();

        while (!turnBasedGame.wasQuited()) {
            turnBasedGame.makeTurn();
        }

        return turnBasedGame.followUp();
    }
}

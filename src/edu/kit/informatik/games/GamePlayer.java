package edu.kit.informatik.games;

import edu.kit.informatik.models.Player;

public class GamePlayer {

    public static Player play(TurnBasedGame turnBasedGame) {
        turnBasedGame.prepareSettings();

        while (!turnBasedGame.wasQuited()) {
            turnBasedGame.makeTurn();
        }

        return turnBasedGame.followUp();
    }
}

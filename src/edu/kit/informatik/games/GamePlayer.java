package edu.kit.informatik.games;

import edu.kit.informatik.models.Player;

public class GamePlayer {

    public static Player play(TurnBasedGame turnBasedGame) {
        turnBasedGame.chooseVariation();
        turnBasedGame.prepare();

        while (!turnBasedGame.isOver()) {
            turnBasedGame.makeTurn();
        }

        return turnBasedGame.followUp();
    }
}

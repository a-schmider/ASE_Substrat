package edu.kit.informatik.models;

public class Player {

    private static int playerCounter = 1;
    private final PlayerStone playerStone;

    /**
     * creates new PlayerStone with : P + playerCounter
     */
    public Player() {
        playerStone = new PlayerStone("P" + Player.playerCounter);
        playerCounter++;
    }

    public PlayerStone getPlayerStone() {
        return playerStone;
    }

}

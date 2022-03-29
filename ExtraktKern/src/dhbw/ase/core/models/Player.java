package dhbw.ase.core.models;

public class Player {

    private static int playerCounter = 0;
    private final PlayerStone playerStone;

    /**
     * creates new PlayerStone with : "P + <playerCounter>" as PlayerStone
     */
    public Player() {
        playerCounter++;
        playerStone = new PlayerStone("P" + Player.playerCounter);
    }

    public PlayerStone getPlayerStone() {
        return playerStone;
    }

}

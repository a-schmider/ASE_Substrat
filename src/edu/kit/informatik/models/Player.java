package edu.kit.informatik.models;

/**
 * @author Andreas Schmider
 */
public class Player {

    private static int playerCounter = 1;
    private String gamingPiece;

    /**
     * creates new Player with gamingPiece: P + playerCounter
     */
    public Player() {
        this.gamingPiece = "P" + Player.playerCounter;
        playerCounter++;
    }

    /**
     * @return the gamingPiece
     */
    public String getGamingPiece() {
        return gamingPiece;
    }

}

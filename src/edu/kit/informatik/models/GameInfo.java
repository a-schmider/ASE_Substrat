package edu.kit.informatik.models;

import edu.kit.informatik.gamerules.BoardGameRule;

/**
 * @author Andi
 */
public class GameInfo {

    //TODO neue Gamerule miteinbeziehen
    private final BoardGameRule gamerule;
    private boolean gRStandard;
    private int gameBoardSize;
    private int amountOfPlayers;
    private int turn;

    /**
     * sets all stats according to the begininput
     *
     * @param gameType type of gamemode
     * @param gBS      gameboard size
     * @param aOP      amount of players
     */
    public GameInfo(String gameType, int gBS, int aOP, BoardGameRule gameRule) {
        this.gRStandard = gameType.equals("standard");
        this.gameBoardSize = gBS;
        this.amountOfPlayers = aOP;
        this.turn = 0;
        this.gamerule = gameRule;
    }

    /**
     * @return i gamemode is standard
     */
    public boolean isGRStandard() {
        return gRStandard;
    }

    /**
     * @return gameBoardSize
     */
    public int getGameBoardSize() {
        return gameBoardSize;
    }

    /**
     * @return amountOfPlayers
     */
    public int getAmountOfPlayers() {
        return amountOfPlayers;
    }

    /**
     * @return turns
     */
    public int getTurn() {
        return this.turn;
    }

    public BoardGameRule getGamerule() {
        return gamerule;
    }

    /**
     * sets turn to 0
     */
    public void resetTurn() {
        this.turn = 0;
    }

    /**
     *
     */
    public void addTwoTurns() {
        this.turn++;
        this.turn++;
    }

}

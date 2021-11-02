package edu.kit.informatik.models;

import edu.kit.informatik.gamerules.BoardGameRule;

import java.util.ArrayList;

/**
 * @author Andi
 */
public class GameInfo {

    //TODO neue Gamerule miteinbeziehen
    private final BoardGameRule gamerule;
    private boolean gRStandard;
    private int gameBoardSize;
    private int amountOfPlayers;
    private int turns;
    private ArrayList<Player> players;

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

        this.turns = 1;
        this.gamerule = gameRule;
        players = new ArrayList<>();
        while (aOP-- > 0) {
            players.add(new Player());
        }
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
    public int getTurns() {
        return this.turns;
    }

    public BoardGameRule getGamerule() {
        return gamerule;
    }

    /**
     * sets turn to 0
     */
    public void resetTurns() {
        this.turns = 1;
    }

    /**
     *
     */
    public void addTwoTurns() {
        addTurns(2);
    }

    public void addTurns(int amount) {
        turns += amount;
    }

    public void addTurn() {
        addTurns(1);
    }

    public Player getActivePlayer() {
        return players.get((turns - 1) % players.size());
    }

}

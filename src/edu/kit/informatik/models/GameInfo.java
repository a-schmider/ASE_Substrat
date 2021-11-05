package edu.kit.informatik.models;

import edu.kit.informatik.gamerules.BoardGameRule;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Andi
 */
public class GameInfo {

    //TODO neue Gamerule miteinbeziehen

    private final ArrayList<Player> players;
    private final BoardGameRule gamerule;
    private final int gameBoardSize;
    private int turns;

    private boolean gRStandard;
    private int amountOfPlayers;

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

    public GameInfo(int boardSize, int playerCount, BoardGameRule gameRule) {
        this.gameBoardSize = boardSize;
        this.amountOfPlayers = playerCount;

        this.turns = 1;
        this.gamerule = gameRule;
        players = new ArrayList<>();
        while (playerCount-- > 0) {
            players.add(new Player());
        }
    }

    public GameInfo(BoardGameRule gameRule, int boardSize, List<Player> players) {
        this.gamerule = gameRule;
        this.gameBoardSize = boardSize;
        this.players = new ArrayList<>(players);
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

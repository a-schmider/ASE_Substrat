package dhbw.ase.core.models;


import dhbw.ase.core.gamerules.BoardGameRule;

import java.util.ArrayList;
import java.util.List;

public class GameInfo {

    private final ArrayList<Player> players;
    private final BoardGameRule gamerule;
    private final int gameBoardSize;
    private int turns = 1;


    public GameInfo(BoardGameRule gameRule, int boardSize, List<Player> players) {
        this.gamerule = gameRule;
        this.gameBoardSize = boardSize;
        this.players = new ArrayList<>(players);
    }


    /**
     * @return gameBoardSize
     */
    public int getGameBoardSize() {
        return gameBoardSize;
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
     * sets turn to 1
     */
    public void resetTurns() {
        this.turns = 1;
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

    public ArrayList<Player> getPlayers() {
        return players;
    }
}

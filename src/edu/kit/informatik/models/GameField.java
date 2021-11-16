package edu.kit.informatik.models;

public class GameField {

    private Player player;

    public boolean isFree() {
        return player == null;
    }

    public void placeStone(Player player) {
        this.player = player;
    }

    public Player getStone() {
        return player;
    }

    public String toString() {
        if (isFree()) {
            return "**";
        } else {
            return player.getPlayerStone().getLabel();
        }
    }
}

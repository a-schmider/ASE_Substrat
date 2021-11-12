package edu.kit.informatik.models;

public class GameField {

    private Player player;

    public boolean isFree() {
        return player == null;
    }

    public boolean placeStone(Player player) {
        if (isFree()) {
            this.player = player;
            return true;
        }
        return false;
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

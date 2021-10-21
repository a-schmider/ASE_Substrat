package edu.kit.informatik.models;

public class GameField {

    private Player stone;


    public boolean isFree() {
        return stone == null;
    }

    public boolean placeStone(Player player) {
        if (isFree()) {
            stone = player;
            return true;
        }
        return false;
    }
}

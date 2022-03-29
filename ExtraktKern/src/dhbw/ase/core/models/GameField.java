package dhbw.ase.core.models;

import dhbw.ase.core.misc.TextRepository;

public class GameField {

    private Player player;

    public boolean isFree() {
        return player == null;
    }

    public void placeStone(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public String toString() {
        if (isFree()) {
            return TextRepository.FIELD_STATE_EMPTY;
        } else {
            return player.getPlayerStone().getLabel();
        }
    }
}

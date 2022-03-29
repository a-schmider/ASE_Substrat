package dhbw.ase.core.models;

import dhbw.ase.core.misc.TextRepository;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class GameFieldTest {

    @Test
    public void newFieldIsFreeTest() {
        GameField field = new GameField();

        assertTrue(field.isFree());
    }

    @Test
    public void placedFieldIsNotFreeTest() {
        //Arrange
        GameField field = new GameField();
        Player player = Mockito.mock(Player.class);

        //Act
        field.placeStone(player);

        //Assert
        assertFalse(field.isFree());
    }

    @Test
    public void getInsertedPlayerTest() {
        //Arrange
        GameField field = new GameField();
        Player player = Mockito.mock(Player.class);

        //Act
        field.placeStone(player);

        //Assert
        assertEquals(player, field.getPlayer());
    }

    @Test
    public void toStringWithFreeFieldTest() {
        //Arrange
        GameField field = new GameField();

        //Assert
        assertEquals(TextRepository.FIELD_STATE_EMPTY, field.toString());
    }

    @Test
    public void toStringWithOccupiedFieldTest() {
        //Arrange
        GameField field = new GameField();
        PlayerStone stone = Mockito.mock(PlayerStone.class);
        Mockito.when(stone.getLabel()).thenReturn("P1");
        Player player = Mockito.mock(Player.class);
        Mockito.when(player.getPlayerStone()).thenReturn(stone);

        //Act
        field.placeStone(player);

        //Assert
        assertEquals("P1", field.toString());
    }
}
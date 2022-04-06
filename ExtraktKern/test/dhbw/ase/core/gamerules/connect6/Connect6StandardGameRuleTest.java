package dhbw.ase.core.gamerules.connect6;

import dhbw.ase.core.models.Compass;
import dhbw.ase.core.models.RectangularGameBoard;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class Connect6StandardGameRuleTest {

    @Test
    public void getNextWidthEast18Test() {
        //Arrange
        Connect6StandardGameRule gameRule = new Connect6StandardGameRule();

        //Act
        int returnedWidth = returnNextWidth(gameRule, 18, Compass.E);

        //Assert
        assertEquals(19, returnedWidth);
    }

    @Test
    public void getNextWidthNorth18Test() {
        //Arrange
        Connect6StandardGameRule gameRule = new Connect6StandardGameRule();

        //Act
        int returnedWidth = returnNextWidth(gameRule, 18, Compass.N);

        //Assert
        assertEquals(18, returnedWidth);
    }

    @Test
    public void getNextWidthWest18Test() {
        //Arrange
        Connect6StandardGameRule gameRule = new Connect6StandardGameRule();

        //Act
        int returnedWidth = returnNextWidth(gameRule, 18, Compass.W);

        //Assert
        assertEquals(17, returnedWidth);
    }

    @Test
    public void getNextWidthEast0est() {
        //Arrange
        Connect6StandardGameRule gameRule = new Connect6StandardGameRule();

        //Act
        int returnedWidth = returnNextWidth(gameRule, 0, Compass.E);

        //Assert
        assertEquals(1, returnedWidth);
    }

    @Test
    public void getNextWidthNorth0Test() {
        //Arrange
        Connect6StandardGameRule gameRule = new Connect6StandardGameRule();

        //Act
        int returnedWidth = returnNextWidth(gameRule, 0, Compass.N);

        //Assert
        assertEquals(0, returnedWidth);
    }

    @Test
    public void getNextWidthWest0Test() {
        //Arrange
        Connect6StandardGameRule gameRule = new Connect6StandardGameRule();

        //Act
        int returnedWidth = returnNextWidth(gameRule, 0, Compass.W);

        //Assert
        assertEquals(-1, returnedWidth);
    }

    private int returnNextWidth(Connect6StandardGameRule gameRule, int width, Compass direction) {
        try {
            return gameRule.getNextWidth(null, width, direction);
        } catch (NoSuchFieldException e) {
            Assertions.fail();
            return -1;
        }
    }
}
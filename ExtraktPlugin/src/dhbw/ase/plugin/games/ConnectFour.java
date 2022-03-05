package dhbw.ase.plugin.games;

import dhbw.ase.core.gamerules.BoardGameRule;
import dhbw.ase.core.gamerules.connect_four.ConnectFourDropGameRule;
import dhbw.ase.core.gamerules.connect_four.ConnectFourStandardGameRule;
import dhbw.ase.core.misc.ConnectFourVariations;
import dhbw.ase.core.misc.TextRepository;
import dhbw.ase.core.models.RectangularGameBoard;
import dhbw.ase.plugin.userinterface.GUI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class ConnectFour extends TurnBasedGame {

    private static final ArrayList<ConnectFourVariations> variations =
            new ArrayList<>(Arrays.asList(ConnectFourVariations.values()));

    private static ConnectFourVariations chosenVariation;
    private RectangularGameBoard board;
    private BoardGameRule boardGameRule;

    public ConnectFour() {
        gui = new GUI();
    }

    @Override
    public String toString() {
        return "Connect Four";
    }

    @Override
    public void prepareSettings() {
        gui.printList(variations);

        int inputNumber;
        try {
            inputNumber = Integer.parseInt(gui.getUserInput()) - 1;
            chosenVariation = variations.get(inputNumber);

        } catch (final IOException e) {
            gui.print(TextRepository.INPUT_ERROR_MSG, false);
        }

    }

    @Override
    public void makeTurn() {
        board = new RectangularGameBoard(7, 6);
        switch (chosenVariation) {
            case standard -> boardGameRule = new ConnectFourStandardGameRule();
            case dropFour -> boardGameRule = new ConnectFourDropGameRule();
        }
    }


}

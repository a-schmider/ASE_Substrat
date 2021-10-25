package edu.kit.informatik.games;

import edu.kit.informatik.ConnectFourVariations;
import edu.kit.informatik.TextRepository;
import edu.kit.informatik.gamerules.BoardGameRule;
import edu.kit.informatik.gamerules.connect_four.ConnectFourDropGR;
import edu.kit.informatik.gamerules.connect_four.ConnectFourStandardGR;
import edu.kit.informatik.models.RectangularGameBoard;
import edu.kit.informatik.userinterface.GUI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class ConnectFour extends TurnBasedGame {

    private static final ArrayList<ConnectFourVariations> variations =
            new ArrayList<>(Arrays.asList(ConnectFourVariations.values()));
    //TODO
    private static GUI gui = new GUI();
    private static ConnectFourVariations chosenVariation;
    private RectangularGameBoard board;
    private BoardGameRule boardGameRule;


    @Override
    public String toString() {
        return "Connect Four";
    }

    @Override
    public void chooseVariation() {
        gui.printList(variations);

        int inputNumber;
        try {
            inputNumber = Integer.parseInt(gui.getUserInput()) - 1;

            chosenVariation = variations.get(inputNumber);
        } catch (final IOException e) {
            gui.print(TextRepository.INPUT_ERROR_MSG);
        }

    }

    @Override
    public void prepare() {
        board = new RectangularGameBoard(7, 6);

        switch (chosenVariation) {
            case standard:
                boardGameRule = new ConnectFourStandardGR();
                break;
            case dropFour:
                boardGameRule = new ConnectFourDropGR();
                break;
        }
    }


}

package edu.kit.informatik;

import edu.kit.informatik.gamerules.connect6.Connect6StandardGR;
import edu.kit.informatik.gamerules.connect6.Connect6TorusGR;
import edu.kit.informatik.games.Connect6;
import edu.kit.informatik.games.ConnectFour;
import edu.kit.informatik.games.GamePlayer;
import edu.kit.informatik.games.TurnBasedGame;
import edu.kit.informatik.userinterface.GUI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class GameSelection {

    private static final ArrayList<TurnBasedGame> gamesList =
            new ArrayList<>(Arrays.asList(
                    new ConnectFour(),
                    new Connect6(
                            new ArrayList<>(Arrays.asList(new Connect6StandardGR(), new Connect6TorusGR())),
                            new ArrayList<>(Arrays.asList(18, 20)),
                            new ArrayList<>(Arrays.asList(2, 3, 4)))));
    //TODO GUI-Methoden static machen? Dependency Inversion? Gui kennt alle, main kennt Gui
    private static final GuiInterface gui = new GUI();
    private static String input;
    private static boolean denied = true;

    public static void main(String[] argv) {

        while (denied) {
            //TODO nachdem ein Spiel beendet wird stürzt das Programm ab
            gui.printList(gamesList);

            try {
                input = gui.getUserInput();
            } catch (final IOException e) {
                denied = true;
            }

            int inputNumber;
            try {
                inputNumber = Integer.parseInt(input) - 1;

                GamePlayer.play(gamesList.get(inputNumber));
            } catch (NumberFormatException e) {
                if ("quit".equals(input)) {
                    System.exit(0);
                } else {
                    gui.print("\r\n" + TextRepository.INPUT_ERROR_MSG);
                }
            }
        }

    }


}

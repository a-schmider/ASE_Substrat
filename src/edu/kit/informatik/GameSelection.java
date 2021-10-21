package edu.kit.informatik;

import edu.kit.informatik.games.Connect6;
import edu.kit.informatik.games.ConnectFour;
import edu.kit.informatik.games.GamePlayer;
import edu.kit.informatik.games.TurnBasedGame;
import edu.kit.informatik.userinterface.GUI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class GameSelection {

    private static final BufferedReader IN = new BufferedReader(new InputStreamReader(System.in));
    private static final ArrayList<TurnBasedGame> gamesList =
            new ArrayList<>(Arrays.asList(new ConnectFour(), new Connect6()));
    //TODO GUI-Methoden static machen? Dependency Inversion? Gui kennt alle, niemand kennt Gui
    private static GuiInterface gui = new GUI();
    private static String input;
    private static boolean denied = true;

    public static void main(String[] argv) {

        while (denied) {
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

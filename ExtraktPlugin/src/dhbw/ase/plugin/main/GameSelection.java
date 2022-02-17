package dhbw.ase.plugin.main;

import dhbw.ase.core.gamerules.connect6.Connect6StandardGameRule;
import dhbw.ase.core.gamerules.connect6.Connect6TorusGameRule;
import dhbw.ase.core.misc.TextRepository;
import dhbw.ase.plugin.games.Connect6;
import dhbw.ase.plugin.games.ConnectFour;
import dhbw.ase.plugin.games.GamePlayer;
import dhbw.ase.plugin.games.TurnBasedGame;
import dhbw.ase.plugin.userinterface.GUI;
import dhbw.ase.plugin.userinterface.GuiInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class GameSelection {

    private static final ArrayList<TurnBasedGame> gamesList =
            new ArrayList<>(Arrays.asList(
                    new ConnectFour(),
                    new Connect6(
                            new ArrayList<>(Arrays.asList(new Connect6StandardGameRule(), new Connect6TorusGameRule())),
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
                    gui.print("\r\n" + TextRepository.INPUT_ERROR_MSG, false);
                }
            }
        }

    }


}

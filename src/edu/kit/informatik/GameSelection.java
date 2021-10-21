package edu.kit.informatik;

import edu.kit.informatik.games.Connect6;
import edu.kit.informatik.games.ConnectFour;
import edu.kit.informatik.games.GamePlayer;
import edu.kit.informatik.games.TurnBasedGame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class GameSelection {

    private static final BufferedReader IN = new BufferedReader(new InputStreamReader(System.in));
    private static final String helloMsg = "Select one of the following games [Enter \"1\", \"2\" ...]:\r\n";
    private static final String deniedMsg = "Could not validate input; try again";
    private static final String helpInfoMsg = "type \"help\" for more info";
    private static final ArrayList<TurnBasedGame> gamesList =
            new ArrayList<>(Arrays.asList(new ConnectFour(), new Connect6()));
    private static String input;
    private static boolean denied = true;

    public static void main(String[] argv) {

        while (denied) {
            printGameList();

            try {
                input = IN.readLine();
            } catch (final IOException e) {
                denied = true;
            }

            int inputNumber;
            try {
                inputNumber = Integer.parseInt(input);

                GamePlayer.play(gamesList.get(inputNumber - 1));
            } catch (NumberFormatException e) {
                switch (input) {
                    case "help":
                        System.out.println(helpInfoMsg);
                        break;
                    case "quit":
                        System.exit(0);
                    default:
                        System.out.println("\r\n" + deniedMsg);
                        break;
                }
            }
        }

    }


    private static void printGameList() {
        System.out.println(helloMsg);
        for (int i = 1; i < gamesList.size() + 1; i++) {
            System.out.println(i + ". " + gamesList.get(i - 1).getName());
        }
        System.out.println();
    }
}

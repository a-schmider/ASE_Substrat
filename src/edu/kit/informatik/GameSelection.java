package edu.kit.informatik;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GameSelection {

    private static final BufferedReader IN = new BufferedReader(new InputStreamReader(System.in));
    private static String input;
    private static boolean denied = false;
    private static final String HelloMsg = "Select one of the following games [Enter \"1\", \"2\" ...]:\r\n";
    private static final String gameList =  "\r\n1. Connect6\r\n" +
            "\r\n2. 4 Gewinnt\r\n";;
    private static final String deniedMsg = "Could not validate input; try again";

    public static void main(String[] argv) {

        while (denied) {
            System.out.println(HelloMsg + gameList);

            try {
                input = IN.readLine();
            } catch (final IOException e) {
                denied = true;
            }

            switch (input) {
                case "1":
                    //start Connect6;
                    break;
                case "2":
                    //start 4 Gewinnt
                    break;
                default:
                    denied = true;
                    break;
            }

            if (denied) {
                System.out.println(deniedMsg);
            }
        }

    }
}

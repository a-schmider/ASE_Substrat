package edu.kit.informatik.userinterface;

import edu.kit.informatik.GuiInterface;
import edu.kit.informatik.TextRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class GUI implements GuiInterface {

    private static final BufferedReader IN = new BufferedReader(new InputStreamReader(System.in));

    public void print(String text) {
        System.out.println(text);
    }

    public void printList(ArrayList<?> gamesList) {
        System.out.println(TextRepository.SELECT_ONE);
        for (int i = 1; i < gamesList.size() + 1; i++) {
            System.out.println(i + ". " + gamesList.get(i - 1).toString());
        }
        System.out.println();
    }

    public String getUserInput() throws IOException {
        String input = IN.readLine();
        System.out.println();
        return input;
    }
}

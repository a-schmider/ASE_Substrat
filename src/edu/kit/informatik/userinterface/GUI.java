package edu.kit.informatik.userinterface;

import edu.kit.informatik.GuiInterface;
import edu.kit.informatik.TextRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GUI implements GuiInterface {

    private static final BufferedReader IN = new BufferedReader(new InputStreamReader(System.in));

    public void print(String text) {
        System.out.println(text);
    }

    public void printList(Iterable<?> list) {
        System.out.println(TextRepository.SELECT_ONE);
        int i = 1;
        for (Object item : list) {
            System.out.println(i++ + ". " + item.toString());
        }
        System.out.println();
    }

    public void printOptions(Iterable<?> options) {
        System.out.println(TextRepository.FOLLOWING_OPTIONS);
        for (Object option : options) {
            System.out.println(option.toString());
        }
        System.out.println();
    }

    public String getUserInput() throws IOException {
        String input = IN.readLine();
        System.out.println();
        return input;
    }
}

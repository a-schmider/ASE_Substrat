package edu.kit.informatik;

import java.io.IOException;
import java.util.ArrayList;

public interface GuiInterface {

    void print(String text);

    void printList(ArrayList<?> gamesList);

    String getUserInput() throws IOException;
}

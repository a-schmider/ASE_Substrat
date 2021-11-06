package edu.kit.informatik;

import edu.kit.informatik.models.GameInfo;

import java.io.IOException;

public interface GuiInterface {

    void print(String text);

    void printList(Iterable<?> list);

    void printOptions(Iterable<?> options);

    String getUserInput() throws IOException;

    void print(GameInfo gameInfo);
}

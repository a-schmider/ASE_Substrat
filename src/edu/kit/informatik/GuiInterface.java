package edu.kit.informatik;

import java.io.IOException;

public interface GuiInterface {

    void print(String text);

    void printList(Iterable<?> list);

    void printOptions(Iterable<?> options);

    String getUserInput() throws IOException;
}

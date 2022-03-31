package dhbw.ase.plugin.userinterface;

import dhbw.ase.core.models.GameInfo;
import dhbw.ase.core.models.RectangularGameBoard;

import java.io.IOException;

public interface GuiInterface {

    void print(String text, boolean emptyEndingLine);

    void printList(Iterable<?> list);

    void printOptions(Iterable<?> options);

    String getUserInput() throws IOException;

    void print(GameInfo gameInfo);

    String readLine();

    void printFieldState(RectangularGameBoard board, int i, int j);
}

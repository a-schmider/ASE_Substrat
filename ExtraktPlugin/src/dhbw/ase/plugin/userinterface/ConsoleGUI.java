package dhbw.ase.plugin.userinterface;

import dhbw.ase.core.misc.TextRepository;
import dhbw.ase.core.models.GameInfo;
import dhbw.ase.core.models.RectangularGameBoard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleGUI implements GuiInterface {

    private static final BufferedReader IN = new BufferedReader(new InputStreamReader(System.in));

    public void print(String text, boolean emptyEndingLine) {
        System.out.println(text);
        if (emptyEndingLine) {
            System.out.println();
        }
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

    @Override
    public void print(GameInfo gameInfo) {
        System.out.println(TextRepository.SETTINGS_USED);
        System.out.println("Gamerule: " + gameInfo.getGamerule().toString());
        System.out.println("Boardsize: " + gameInfo.getGameBoardSize());
        System.out.println("Player count: " + gameInfo.getPlayers().size());
        System.out.println();
    }

    /**
     * Reads a line of text. A line is considered to be terminated by any one of a line feed ('\n'), a carriage return
     * ('\r'), or a carriage return followed immediately by a linefeed.
     *
     * @return a {@code String} containing the contents of the line, not including any line-termination characters, or
     * {@code null} if the end of the stream has been reached
     */
    public String readLine() {
        try {
            String input = IN.readLine();
            System.out.println();
            return input;
        } catch (final IOException e) {
            /*
             * The IOException will not occur during tests executed by the praktomat, therefore the
             * following RuntimeException does not have to get handled.
             */
            throw new RuntimeException(e);
        }
    }

    public void printFieldState(RectangularGameBoard board, int i, int j) {
        String state = board.getFieldAsString(i, j);
        if (state.equals(TextRepository.FIELD_STATE_EMPTY)) {
            state = TextRepository.NOBODY;
        }
        String text = TextRepository.FIELD_STATE_IS + state;
        print(text, true);
    }
}

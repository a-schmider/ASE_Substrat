package dhbw.ase.plugin.userinterface;

import dhbw.ase.core.misc.TextRepository;
import dhbw.ase.core.models.GameInfo;
import dhbw.ase.core.models.RectangularGameBoard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GUI implements GuiInterface {

    private static final BufferedReader IN = new BufferedReader(new InputStreamReader(System.in));

    /**
     * Prints the given error-{@code message} with the prefix "{@code Error, }".
     *
     * <p>More specific, this method behaves exactly as if the following code got executed:
     * <blockquote><pre>
     * Terminal.printLine("Error, " + message);</pre>
     * </blockquote>
     *
     * @param message the error message to be printed
     * @see #printLine(Object)
     */
    public static void printError(final String message) {
        printLine("Error, " + message);
    }

    /**
     * Prints the string representation of an {@code Object} and then terminate the line.
     *
     * <p>If the argument is {@code null}, then the string {@code "null"} is printed, otherwise the object's string
     * value {@code obj.toString()} is printed.
     *
     * @param object the {@code Object} to be printed
     * @see String#valueOf(Object)
     */
    public static void printLine(final Object object) {
        System.out.println(object);
    }

    /**
     * Prints an array of characters and then terminate the line.
     *
     * <p>If the argument is {@code null}, then a {@code NullPointerException} is thrown, otherwise the value of {@code
     * new String(charArray)} is printed.
     *
     * @param charArray an array of chars to be printed
     * @see String#valueOf(char[])
     */
    public static void printLine(final char[] charArray) {
        /*
         * Note: This method's sole purpose is to ensure that the Terminal-class behaves exactly as
         * System.out regarding output. (System.out.println(char[]) calls String.valueOf(char[])
         * which itself returns 'new String(char[])' and is therefore the only method that behaves
         * differently when passing the provided parameter to the System.out.println(Object)
         * method.)
         */
        System.out.println(charArray);
    }

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

    public void rowPrint(RectangularGameBoard board, int wantedRow) {
        if (wantedRow >= 0 && wantedRow < board.getCountOfRows()) {
            StringBuilder line = new StringBuilder();
            for (int i = 0; i < board.getCountOfColumns(); i++) {
                line.append(board.getFieldAsString(wantedRow, i)).append(" ");
            }
            line = new StringBuilder(line.substring(0, line.length() - 1));
            printLine(line.toString());
        } else {
            printError("invalid row");
        }
    }

    public void colPrint(RectangularGameBoard board, int wantedRow) {
        if (wantedRow >= 0 && wantedRow < board.getCountOfColumns()) {
            StringBuilder line = new StringBuilder();
            for (int i = 0; i < board.getCountOfRows(); i++) {
                line.append(board.getFieldAsString(wantedRow, i)).append(" ");
            }
            line = new StringBuilder(line.substring(0, line.length() - 1));
            printLine(line.toString());
        } else {
            printError("invalid column");
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

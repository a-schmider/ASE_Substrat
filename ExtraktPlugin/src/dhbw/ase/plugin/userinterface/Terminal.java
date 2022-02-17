//package dhbw.ase.plugin.userinterface;
//
//import dhbw.ase.core.models.RectangularGameBoard;
//
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//
///**
// * This class provides some simple methods for input/output from and to a terminal as well as a method to read in
// * files.
// *
// * <p><b>Never modify this class, never upload it to Praktomat.</b> This is only for your local use. If an assignment
// * tells you to use this class for input and output never use System.out, System.err or System.in in the same
// * assignment.
// *
// * @author ITI, VeriAlg Group
// * @author IPD, SDQ Group
// * @version 5.03, 2016/05/07
// */
//public final class Terminal {
//
//    /**
//     * Reads text from the "standard" input stream, buffering characters so as to provide for the efficient reading
//     * of characters, arrays, and lines. This stream is already open and ready to supply input data and corresponds
//     * to keyboard input.
//     */
//    private static final BufferedReader IN = new BufferedReader(new InputStreamReader(System.in));
//
//    /**
//     * Private constructor to avoid object generation.
//     *
//     * @deprecated Utility-class constructor.
//     */
//    @Deprecated
//    private Terminal() {
//        throw new AssertionError("Utility class constructor.");
//    }
//
//
//
//
//
//
//    /**
//     * Reads the file with the specified path and returns its content stored in a {@code String} array, whereas the
//     * first array field contains the file's first line, the second field contains the second line, and so on.
//     *
//     * @param path the path of the file to be read
//     * @return the content of the file stored in a {@code String} array
//     */
//    public static String[] readFile(final String path) {
//        try (final BufferedReader reader = new BufferedReader(new FileReader(path))) {
//            return reader.lines().toArray(String[]::new);
//        } catch (final IOException e) {
//            /*
//             * You can expect that the praktomat exclusively provides valid file-paths. Therefore
//             * there will no IOException occur while reading in files during the tests, the
//             * following RuntimeException does not have to get handled.
//             */
//            throw new RuntimeException(e);
//        }
//    }
//
//
//
//
//
//}
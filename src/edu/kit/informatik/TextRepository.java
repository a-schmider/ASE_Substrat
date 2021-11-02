package edu.kit.informatik;

public class TextRepository {

    public static final String SELECT_ONE = "\r\nSelect one of the following [Enter \"1\", \"2\" ...]:\r\n";
    public static final String INPUT_ERROR_MSG = "Command no recognized; try again";
    public static final String HELP_INFO_MSG = "Type \"help\" for more info";
    public static final String PLACEMENT_NOT_ALLOWED = "At least one of the chosen fields is not allowing to place a stone";

    public static final String CONNECT6_HELP = "Available commands:\r\n" +
            "\r\nprint:\r\n Prints the entire board\r\n" +
            "\r\nreset:\r\n Resets the game so it can be played again\r\n" +
            "\r\nquit:\r\n Terminates the program\r\n" +
            "\r\nplace w x y z:\r\n Places stones on the fields w|x and y|z\r\n" +
            "\r\nrowprint z:\r\n Prints the selected row\r\n" +
            "\r\ncolprint z:\r\n Prints the selected column\r\n" +
            "\r\nstate y z:\r\n Prints which stone is placed on the selected field\r\n" +
            "\r\nhelp:\r\n Shows this screen\r\n\r\n";
}

package edu.kit.informatik;

public class TextRepository {

    public static final String SELECT_ONE = "\r\nSelect one of the following [Enter \"1\", \"2\" ...]:\r\n";
    public static final String INPUT_ERROR_MSG = "Could not validate input; try again";
    public static final String HELP_INFO_MSG = "type \"help\" for more info";

    public static final String CONNECT6_HELP = "Available commands:\\r\\n\" +\n" +
            "                            \"\\r\\nprint:\\r\\n Prints the entire board\\r\\n\" +\n" +
            "                            \"\\r\\nreset:\\r\\n Resets the game so it can be played again\\r\\n\" +\n" +
            "                            \"\\r\\nquit:\\r\\n Terminates the program\\r\\n\" +\n" +
            "                            \"\\r\\nplace w x y z:\\r\\n Places stones on the fields w|x and y|z\\r\\n\" +\n" +
            "                            \"\\r\\nrowprint z:\\r\\n Prints the selected row\\r\\n\" +\n" +
            "                            \"\\r\\ncolprint z:\\r\\n Prints the selected column\\r\\n\" +\n" +
            "                            \"\\r\\nstate y z:\\r\\n Prints which stone is placed on the selected field\\r\\n\" +\n" +
            "                            \"\\r\\nhelp:\\r\\n Shows this screen\\r\\n\\r\\n";
}

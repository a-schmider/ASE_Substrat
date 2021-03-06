package dhbw.ase.core.misc;

public class TextRepository {

    public static final String SELECT_ONE = "Select one of the following [Enter \"1\", \"2\" ...]:\r\n";
    public static final String INPUT_ERROR_MSG = "Command/Input not recognized; try again\r\n";
    public static final String HELP_INFO_MSG = "Type \"help\" for more info";
    public static final String PLACEMENT_NOT_ALLOWED = "At least one of the chosen fields is not allowing to place a stone";

    public static final String CONNECT6_HELP = "Available commands:\r\n" +
            "\r\n" +
            "print:\r\n" +
            " Prints the entire board\r\n" +
            "\r\n" +
            "reset:\r\n" +
            " Resets the game so it can be played again\r\n" +
            "\r\n" +
            "quit:\r\n" +
            " Terminates the program\r\n" +
            "\r\n" +
            "place w x y z:\r\n" +
            " Places stones on the fields w|x and y|z\r\n" +
            "\r\n" +
            "rowprint z:\r\n" +
            " Prints the selected row\r\n" +
            "\r\n" +
            "colprint z:\r\n" +
            " Prints the selected column\r\n" +
            "\r\n" +
            "state y z:\r\n" +
            " Prints which stone is placed on the selected field\r\n" +
            "\r\n" +
            "help:\r\n" +
            " Shows this screen\r\n" +
            "\r\n";


    public static final String CHOOSE_BOARD_SIZE = "Choose the board size";
    public static final String CHOOSE_PLAYER_COUNT = "Choose how many players will be playing";
    public static final String CHOOSE_VARIATION = "Choose a variation";
    public static final String FOLLOWING_OPTIONS = "Type one of the following options\r\n";
    public static final String SETTINGS_USED = "Following settings will be used:";
    public static final String CHANGE_SETTINGS = "Do you want to change the settings again?\r\n" +
            "Enter \"yes\" or \"no\"";
    public static final String YES = "yes";
    public static final String WINNER_IS = "Thw Winner is";
    public static final String GAME_ENDING = "The Game is over. You can still use all commands except the place command.";
    public static final String RETURN_TO_MAIN_MENU = "Returning to main menu...\r\n\r\n";
    public static final String FIELD_STATE_IS = "This field belongs to ";
    public static final String FIELD_STATE_EMPTY = "**";
    public static final String NOBODY = "nobody";
}

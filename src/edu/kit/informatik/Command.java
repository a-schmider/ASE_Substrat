package edu.kit.informatik;

public class Command {

    public static final int PRINT_PARAM_LENGTH = 0;
    public static final int ROWPRINT_PARAM_LENGTH = 1;
    public static final int COLPRINT_PARAM_LENGTH = 1;
    public static final int QUIT_PARAM_LENGTH = 0;
    public static final int RESET_PARAM_LENGTH = 0;
    public static final int PLACE_PARAM_LENGTH = 4;
    public static final int STATE_PARAM_LENGTH = 2;
    public static final int HELP_PARAM_LENGTH = 0;


    private Connect6Commands command;
    private String[] parameters;

    public Command(Connect6Commands command, String[] parameters) {
        this.command = command;
        this.parameters = parameters;
    }
}

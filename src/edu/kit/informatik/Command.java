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


    private final Connect6Commands command;
    private final int[] parameters;

    public Command(Connect6Commands command, int parameters) {
        this.command = command;
        this.parameters = new int[]{parameters};
    }

    public Command(Connect6Commands command, int[] parameters) {
        this.command = command;
        this.parameters = parameters;
    }

    public Connect6Commands getCommand() {
        return command;
    }

    public int[] getParameters() {
        return parameters;
    }
}

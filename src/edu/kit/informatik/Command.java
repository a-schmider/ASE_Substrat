package edu.kit.informatik;

import java.util.Arrays;
import java.util.List;

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
    private final List<Object> parameters;

    public Command(Connect6Commands command, Object parameters) {
        this.command = command;
        this.parameters = Arrays.asList(parameters);
    }

    public Connect6Commands getCommand() {
        return command;
    }

    public List<Object> getParameters() {
        return parameters;
    }
}

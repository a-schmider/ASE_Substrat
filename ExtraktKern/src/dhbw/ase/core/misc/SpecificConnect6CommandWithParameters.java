package dhbw.ase.core.misc;

public class SpecificConnect6CommandWithParameters {

    public static final int PRINT_PARAM_LENGTH = 0;
    public static final int ROWPRINT_PARAM_LENGTH = 1;
    public static final int COLPRINT_PARAM_LENGTH = 1;
    public static final int QUIT_PARAM_LENGTH = 0;
    public static final int RESET_PARAM_LENGTH = 0;
    public static final int PLACE_PARAM_LENGTH = 4;
    public static final int STATE_PARAM_LENGTH = 2;
    public static final int HELP_PARAM_LENGTH = 0;


    private final Connect6CommandEnum commandEnum;
    private final int[] parameters;

    public SpecificConnect6CommandWithParameters(Connect6CommandEnum commandEnum, int parameters) {
        this.commandEnum = commandEnum;
        this.parameters = new int[]{parameters};
    }

    public SpecificConnect6CommandWithParameters(Connect6CommandEnum commandEnum, int[] parameters) {
        this.commandEnum = commandEnum;
        this.parameters = parameters;
    }

    public Connect6CommandEnum getCommandEnum() {
        return commandEnum;
    }

    public int[] getParameters() {
        return parameters;
    }
}

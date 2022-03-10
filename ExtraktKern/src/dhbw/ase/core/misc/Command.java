package dhbw.ase.core.misc;

public class Command {

    public static final int PRINT_PARAM_LENGTH = 0;
    public static final int ROWPRINT_PARAM_LENGTH = 1;
    public static final int COLPRINT_PARAM_LENGTH = 1;
    public static final int QUIT_PARAM_LENGTH = 0;
    public static final int RESET_PARAM_LENGTH = 0;
    public static final int PLACE_PARAM_LENGTH = 4;
    public static final int STATE_PARAM_LENGTH = 2;
    public static final int HELP_PARAM_LENGTH = 0;


    private final Connect6CommandEnum command;
    private final int[] parameters;

    public Command(Connect6CommandEnum command, int parameters) {
        this.command = command;
        this.parameters = new int[]{parameters};
    }

    public Command(Connect6CommandEnum command, int[] parameters) {
        this.command = command;
        this.parameters = parameters;
    }

    public Connect6CommandEnum getCommand() {
        return command;
    }

    public int[] getParameters() {
        return parameters;
    }
}

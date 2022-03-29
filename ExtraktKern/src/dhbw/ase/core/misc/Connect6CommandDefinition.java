package dhbw.ase.core.misc;

import dhbw.ase.core.models.Player;

import java.util.function.BiConsumer;

public class Connect6CommandDefinition {

    private final String text;
    private final Connect6CommandEnum commandEnum;
    private final int parameter;
    private final BiConsumer<Player, SpecificConnect6CommandWithParameters> method;

    public Connect6CommandDefinition(String text, Connect6CommandEnum commandEnum, int parameterCount, BiConsumer<Player, SpecificConnect6CommandWithParameters> method) {
        this.text = text;
        this.commandEnum = commandEnum;
        this.parameter = parameterCount;
        this.method = method;
    }

    public String getText() {
        return text;
    }

    public Connect6CommandEnum getEnum() {
        return commandEnum;
    }

    public int getParameter() {
        return parameter;
    }

    public BiConsumer<Player, SpecificConnect6CommandWithParameters> getMethod() {
        return method;
    }
}

package dhbw.ase.core.misc;

import dhbw.ase.core.models.Player;

import java.util.function.BiConsumer;

public class Connect6Command {

    private final String text;
    private final Connect6CommandEnum commandEnum;
    private final int parameter;
    private final BiConsumer<Player, Command> method;

    public Connect6Command(String text, Connect6CommandEnum commandEnum, int parameter, BiConsumer<Player, Command> method) {
        this.text = text;
        this.commandEnum = commandEnum;
        this.parameter = parameter;
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

    public BiConsumer<Player, Command> getMethod() {
        return method;
    }
}

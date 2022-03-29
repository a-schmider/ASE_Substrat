package dhbw.ase.core.misc;

import dhbw.ase.core.models.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class Connect6CommandMap {

    private static List<Connect6CommandDefinition> list = new ArrayList<>();

    public static void addCommand(String text, Connect6CommandEnum enums, int parameter, BiConsumer method) {
        list.add(new Connect6CommandDefinition(text, enums, parameter, method));
    }

    public static Connect6CommandEnum getEnum(String command) {
        for (Connect6CommandDefinition definition : list) {
            if (definition.getText().equals(command)) {
                return definition.getEnum();
            }
        }
        return null;
    }

    public static int getInt(Connect6CommandEnum command) {
        for (Connect6CommandDefinition definition : list) {
            if (definition.getEnum() == command) {
                return definition.getParameter();
            }
        }
        return 0;
    }

    public static BiConsumer<Player, SpecificConnect6CommandWithParameters> getBiConsumer(Connect6CommandEnum command) {
        for (Connect6CommandDefinition definition : list) {
            if (definition.getEnum() == command) {
                return definition.getMethod();
            }
        }
        return null;
    }
}

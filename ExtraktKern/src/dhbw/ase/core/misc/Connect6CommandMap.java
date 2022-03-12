package dhbw.ase.core.misc;

import dhbw.ase.core.models.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class Connect6CommandMap {

    private static List<Connect6Command> list = new ArrayList<>();

    public static void addCommand(String text, Connect6CommandEnum enums, int parameter, BiConsumer method) {
        list.add(new Connect6Command(text, enums, parameter, method));
    }

    public static Connect6CommandEnum getEnum(String command) {
        for (Connect6Command connect6Command : list) {
            if (connect6Command.getText().equals(command)) {
                return connect6Command.getEnum();
            }
        }
        return null;
    }

    public static int getInt(Connect6CommandEnum command) {
        for (Connect6Command connect6Command : list) {
            if (connect6Command.getEnum() == command) {
                return connect6Command.getParameter();
            }
        }
        return 0;
    }

    public static BiConsumer<Player, Command> getBiConsumer(Connect6CommandEnum command) {
        for (Connect6Command connect6Command : list) {
            if (connect6Command.getEnum() == command) {
                return connect6Command.getMethod();
            }
        }
        return null;
    }
}

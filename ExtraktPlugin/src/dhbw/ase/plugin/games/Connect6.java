package dhbw.ase.plugin.games;

import dhbw.ase.core.gamerules.ConnectGameRule;
import dhbw.ase.core.misc.*;
import dhbw.ase.core.models.Connect6GameBoard;
import dhbw.ase.core.models.GameInfo;
import dhbw.ase.core.models.Player;
import dhbw.ase.core.models.RectangularGameBoard;
import dhbw.ase.plugin.userinterface.ConsoleGUI;

import java.io.IOException;
import java.util.*;
import java.util.function.BiConsumer;


public class Connect6 extends TurnBasedGame {

    private final List<ConnectGameRule> possibleGameRules;
    private final List<Integer> possibleBoardSizes;
    private final List<Integer> possiblePlayers;

    private GameInfo gameInfo;
    private RectangularGameBoard gameboard;


    public Connect6(List<ConnectGameRule> possibleGameRules, List<Integer> possibleBoardSizes, List<Integer> possiblePlayersCount) {
        this.possibleGameRules = possibleGameRules;
        this.possibleBoardSizes = possibleBoardSizes;
        this.possiblePlayers = possiblePlayersCount;
        gui = new ConsoleGUI();
        initalizeCommandMap();
    }

    private void initalizeCommandMap() {
        Connect6CommandMap.addCommand("print", Connect6CommandEnum.print, 0,
                (ignored, ignored2) -> executePrint());

        Connect6CommandMap.addCommand("rowprint", Connect6CommandEnum.rowprint, 1,
                (BiConsumer<Player, Command>) (ignored, command) -> executeRowprint(command));

        Connect6CommandMap.addCommand("colprint", Connect6CommandEnum.colprint, 1,
                (BiConsumer<Player, Command>) (ignored, command) -> executeColprint(command));

        Connect6CommandMap.addCommand("quit", Connect6CommandEnum.quit, 0,
                (BiConsumer<Player, Command>) (ignored, ignored2) -> executeQuit());

        Connect6CommandMap.addCommand("reset", Connect6CommandEnum.reset, 0,
                (BiConsumer<Player, Command>) (ignored, ignored2) -> executeReset());

        Connect6CommandMap.addCommand("place", Connect6CommandEnum.place, 4,
                (BiConsumer<Player, Command>) this::executePlace);

        Connect6CommandMap.addCommand("state", Connect6CommandEnum.state, 2,
                (BiConsumer<Player, Command>) (ignored, command) -> executeState(command));

        Connect6CommandMap.addCommand("help", Connect6CommandEnum.help, 0,
                (ignored, ignored2) -> executeHelp());
    }

    private Command getCommand(String input) throws IOException {
        String[] arrayString;
        arrayString = input.split("\\s+");

        Connect6CommandEnum command = Connect6CommandMap.getEnum(arrayString[0]);

        String[] parameter = new String[0];
        if (arrayString.length > 1) {
            parameter = Arrays.copyOfRange(arrayString, 1, arrayString.length);
        }

        if (checkCorrectParametersTypes(command, parameter)) {
            return new Command(command, Arrays.stream(parameter).mapToInt(Integer::parseInt).toArray());
        }

        throw new IOException("Mismatch of command and parameters");
    }

    private boolean checkCorrectParametersTypes(Connect6CommandEnum command, String[] parameters) {
        int[] values;
        try {
            values = Arrays.stream(parameters).mapToInt(Integer::parseInt).toArray();
        } catch (Exception e) {
            return false;
        }

        return values.length == Connect6CommandMap.getInt(command);
    }

    private void executeCommand(Player player, Command command) {
        Connect6CommandMap.getBiConsumer(command.getCommand()).accept(player, command);
    }

    private void executeHelp() {
        gui.print(TextRepository.CONNECT6_HELP, false);
    }

    private void executeState(Command command) {
        gui.printFieldState(gameboard, command.getParameters()[0], command.getParameters()[1]);
    }

    private void executePlace(Player player, Command command) {
        if (checkAllowedPlacement(command)) {
            gameboard.placeStone(command.getParameters()[0], command.getParameters()[1], player);
            gameboard.placeStone(command.getParameters()[2], command.getParameters()[3], player);
            gameInfo.addTurn();

            winner = gameInfo.getGamerule().checkWin(gameboard, command);
            if (winner != null) {
                finished = true;
            }
        } else {
            gui.print(TextRepository.PLACEMENT_NOT_ALLOWED, false);
        }
    }

    private void executeReset() {
        finished = false;
        winner = null;
        gameboard.initGameBoard();
        gameInfo.resetTurns();
    }

    private void executeQuit() {
        quited = true;
    }

    private void executeColprint(Command command) {
        gui.print(gameboard.getColumnAsString(command.getParameters()[0]), false);
    }

    private void executeRowprint(Command command) {
        gui.print(gameboard.getRowAsString(command.getParameters()[0]), false);
    }

    private void executePrint() {
        gui.print(gameboard.toString(), false);
    }

    /**
     * checks if game is not over, placing stones on both coordinates is allowed and if both coordinates are different
     *
     * @param command to executed command
     * @return true, if it is allowed to place both stones
     */
    private boolean checkAllowedPlacement(Command command) {
        return isRunning()
                && gameInfo.getGamerule().checkAllowedPlacement(gameboard, command.getParameters()[0], command.getParameters()[1])
                && gameInfo.getGamerule().checkAllowedPlacement(gameboard, command.getParameters()[2], command.getParameters()[3])
                && (command.getParameters()[0] != command.getParameters()[2] || command.getParameters()[1] != command.getParameters()[3]);

    }

    @Override
    public String toString() {
        return "Connect6";
    }

    @Override
    protected void prepareSettings() {
        //Repeat settings questions until the settings are accepted

        //noinspection StatementWithEmptyBody
        while (chooseSettings());

        gameboard = new Connect6GameBoard(gameInfo.getGameBoardSize());

        gui.print(TextRepository.HELP_INFO_MSG, false);
    }

    @Override
    protected void makeTurn() {
        if (!wasQuited()) {
            Player activePlayer = gameInfo.getActivePlayer();
            gui.print(activePlayer.getPlayerStone().getLabel() + "s turn:", false);
            String input = gui.readLine();
            try {
                Command command = getCommand(input);
                executeCommand(activePlayer, command);
            } catch (IOException e) {
                gui.print(TextRepository.INPUT_ERROR_MSG, false);
            }

            if (!isRunning()) {
                gui.print(TextRepository.WINNER_IS + " " + winner.getPlayerStone().getLabel(), false);
                gui.print(TextRepository.GAME_ENDING, false);
            }
        }
    }

    private boolean chooseSettings() {
        ConnectGameRule gamerule = chooseVariation();
        int boardSize = chooseBoardSize();
        ArrayList<Player> players = choosePlayerCount();

        gameInfo = new GameInfo(gamerule, boardSize, players);

        //show settings again
        gui.print(gameInfo);
        return repeatSettings();
    }

    private boolean repeatSettings() {
        while (true) {
            try {
                gui.print(TextRepository.CHANGE_SETTINGS, true);
                String input = gui.getUserInput();
                return input.equals(TextRepository.YES);
            } catch (IOException e) {
                gui.print(TextRepository.INPUT_ERROR_MSG, false);
            }
        }
    }

    private ConnectGameRule chooseVariation() {
        while (true) {
            try {
                gui.print(TextRepository.CHOOSE_VARIATION, false);
                gui.printList(possibleGameRules);
                String input = gui.getUserInput();

                int selectedVariation = Integer.parseInt(input);
                switch (selectedVariation) {
                    case 1:
                    case 2:
                        return possibleGameRules.get(selectedVariation - 1);
                    default:
                        gui.print(TextRepository.INPUT_ERROR_MSG, false);
                }

            } catch (IOException ignored) {
                gui.print(TextRepository.INPUT_ERROR_MSG, false);
            }
        }
    }

    private int chooseBoardSize() {
        while (true) {
            try {
                gui.print(TextRepository.CHOOSE_BOARD_SIZE, false);
                gui.printOptions(possibleBoardSizes);
                String input = gui.getUserInput();

                int boardsize = Integer.parseInt(input);
                if (possibleBoardSizes.contains(boardsize)) {
                    return boardsize;
                } else {
                    gui.print(TextRepository.INPUT_ERROR_MSG, false);
                }

            } catch (IOException ignored) {
                gui.print(TextRepository.INPUT_ERROR_MSG, false);
            }
        }
    }

    private ArrayList<Player> choosePlayerCount() {
        while (true) {
            try {
                gui.print(TextRepository.CHOOSE_PLAYER_COUNT, false);
                gui.printOptions(possiblePlayers);
                String input = gui.getUserInput();

                int playerCount = Integer.parseInt(input);
                ArrayList<Player> players = new ArrayList<>();
                players.add(new Player());

                if (possiblePlayers.contains(playerCount)) {
                    while (players.size() < playerCount) {
                        players.add(new Player());
                    }
                    return players;
                } else {
                    gui.print(TextRepository.INPUT_ERROR_MSG, false);
                }
            } catch (IOException ignored) {
                gui.print(TextRepository.INPUT_ERROR_MSG, false);
            }
        }
    }
}

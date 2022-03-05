package dhbw.ase.plugin.games;

import dhbw.ase.core.gamerules.ConnectGameRule;
import dhbw.ase.core.misc.Command;
import dhbw.ase.core.misc.Connect6Commands;
import dhbw.ase.core.misc.TextRepository;
import dhbw.ase.core.models.Connect6GameBoard;
import dhbw.ase.core.models.GameInfo;
import dhbw.ase.core.models.Player;
import dhbw.ase.core.models.RectangularGameBoard;
import dhbw.ase.plugin.userinterface.GUI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


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
        gui = new GUI();
    }

    private Command getCommand(String input) throws IOException {
        String[] arrayString;
        arrayString = input.split("\\s+");

        Connect6Commands command = switch (arrayString[0]) {
            case "print" -> Connect6Commands.print;
            case "rowprint" -> Connect6Commands.rowprint;
            case "colprint" -> Connect6Commands.colprint;
            case "quit" -> Connect6Commands.quit;
            case "reset" -> Connect6Commands.reset;
            case "place" -> Connect6Commands.place;
            case "state" -> Connect6Commands.state;
            case "help" -> Connect6Commands.help;
            default -> throw new IOException("Command not recognized");
        };

        String[] parameter = new String[0];
        if (arrayString.length > 1) {
            parameter = Arrays.copyOfRange(arrayString, 1, arrayString.length);
        }

        if (checkCorrectParametersTypes(command, parameter)) {
            return new Command(command, Arrays.stream(parameter).mapToInt(Integer::parseInt).toArray());
        }

        throw new IOException("Mismatch of command and parameters");
    }

    private boolean checkCorrectParametersTypes(Connect6Commands command, String[] parameters) {
        int[] values;
        try {
            values = Arrays.stream(parameters).mapToInt(Integer::parseInt).toArray();
        } catch (Exception e) {
            return false;
        }

        return switch (command) {
            case print -> values.length == Command.PRINT_PARAM_LENGTH;
            case rowprint -> values.length == Command.ROWPRINT_PARAM_LENGTH;
            case colprint -> values.length == Command.COLPRINT_PARAM_LENGTH;
            case quit -> values.length == Command.QUIT_PARAM_LENGTH;
            case reset -> values.length == Command.RESET_PARAM_LENGTH;
            case place -> values.length == Command.PLACE_PARAM_LENGTH;
            case state -> values.length == Command.STATE_PARAM_LENGTH;
            case help -> values.length == Command.HELP_PARAM_LENGTH;
        };
    }

    //TODO jeden Case in Methode auslagern
    private void executeCommand(Player player, Command command) {
        switch (command.getCommand()) {
            case print:
                gui.print(gameboard.toString(), false);
                break;
            case rowprint:
                gui.print(gameboard.getRowAsString(command.getParameters()[0]), false);
                break;
            case colprint:
                gui.print(gameboard.getColumnAsString(command.getParameters()[0]), false);
                break;
            case quit:
                quited = true;
                break;
            case reset:
                finished = false;
                winner = null;
                gameboard.initGameBoard();
                gameInfo.resetTurns();
                break;
            case place:
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
                break;
            case state:
                gui.printFieldState(gameboard, command.getParameters()[0], command.getParameters()[1]);
                break;
            case help:
                gui.print(TextRepository.CONNECT6_HELP, false);
                break;
            default:
                break;
        }
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
    void prepareSettings() {
        //Repeat settings questions until the settings are accepted

        //noinspection StatementWithEmptyBody
        while (chooseSettings()) ;

        gameboard = new Connect6GameBoard(gameInfo.getGameBoardSize());

        gui.print(TextRepository.HELP_INFO_MSG, false);
    }

    @Override
    void makeTurn() {
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

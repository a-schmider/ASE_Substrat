package edu.kit.informatik.games;

import edu.kit.informatik.Command;
import edu.kit.informatik.Connect6Commands;
import edu.kit.informatik.TextRepository;
import edu.kit.informatik.gamerules.connect6.Connect6GameRule;
import edu.kit.informatik.models.Connect6GameBoard;
import edu.kit.informatik.models.GameInfo;
import edu.kit.informatik.models.Player;
import edu.kit.informatik.models.RectangularGameBoard;
import edu.kit.informatik.userinterface.GUI;
import edu.kit.informatik.userinterface.Terminal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Andreas Schmider
 */
public class Connect6 extends TurnBasedGame {

    private static final GUI gui = new GUI();

    private final List<Connect6GameRule> possibleGameRules;
    private final List<Integer> possibleBoardSizes;
    private final List<Integer> possiblePlayers;

    private boolean exit = false;
    private boolean finished = false;
    private GameInfo gameInfo;
    private RectangularGameBoard gameboard;


    public Connect6(List<Connect6GameRule> possibleGameRules, List<Integer> possibleBoardSizes, List<Integer> possiblePlayersCount) {
        this.possibleGameRules = possibleGameRules;
        this.possibleBoardSizes = possibleBoardSizes;
        this.possiblePlayers = possiblePlayersCount;
    }


    public void start() {
        gui.print(TextRepository.HELP_INFO_MSG);

        Player activePlayer;
        gameboard = new Connect6GameBoard(gameInfo.getGameBoardSize());

        // real game
        while (!exit) {
            //TODO neuer Spielverlauf, Gewinner noch testen

            activePlayer = gameInfo.getActivePlayer();
            gui.print(activePlayer.getGamingPiece() + "s turn:");
            String input = Terminal.readLine();
            try {
                Command command = getCommand(input);
                executeCommand(activePlayer, command);
            } catch (IOException e) {
                gui.print(TextRepository.INPUT_ERROR_MSG);
            }
        }

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

    private void executeCommand(Player player, Command command) {
        switch (command.getCommand()) {
            case print:
                gui.print(gameboard.toString());
                break;
            case rowprint:
                gui.print(gameboard.getRowAsString(command.getParameters()[0]));
                break;
            case colprint:
                gui.print(gameboard.getColumnAsString(command.getParameters()[0]));
                break;
            case quit:
                exit = true;
                break;
            case reset:
                finished = false;
                gameboard.initGameBoard();
                gameInfo.resetTurns();
                break;
            case place:
                if (checkAllowedPlacement(command)) {
                    gameboard.placeStone(command.getParameters()[0], command.getParameters()[1], player);
                    gameboard.placeStone(command.getParameters()[2], command.getParameters()[3], player);
                    gameInfo.addTurn();
                    //gameInfo.getGamerule().checkWin();
                } else {
                    gui.print(TextRepository.PLACEMENT_NOT_ALLOWED);
                }
                break;
            case state:
                gui.print(gameboard.getFieldAsString(command.getParameters()[0], command.getParameters()[1]));
                break;
            case help:
                gui.print(TextRepository.CONNECT6_HELP);
                break;
            default:
                break;
        }
    }

    private boolean checkAllowedPlacement(Command command) {
        //checks if game is not over, placing stones on both coordinates is allowed and if both coordinates are different
        return !finished
                && gameInfo.getGamerule().checkAllowedPlacement(command.getParameters()[0], command.getParameters()[1], gameboard)
                && gameInfo.getGamerule().checkAllowedPlacement(command.getParameters()[2], command.getParameters()[3], gameboard)
                && (command.getParameters()[0] != command.getParameters()[2] || command.getParameters()[1] != command.getParameters()[3]);

    }

    @Override
    public String toString() {
        return "Connect6";
    }

    @Override
    void prepareSettings() {
        //Repeat settings questions until the settings are accepted
        while (chooseSettings()) ;
    }

    @Override
    void prepare() {
        //start();
    }

    private boolean chooseSettings() {
        Connect6GameRule gamerule = chooseVariation();
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
                gui.print(TextRepository.CHANGE_SETTINGS);
                String input = gui.getUserInput();
                return input.equals(TextRepository.YES);
            } catch (IOException e) {
                gui.print(TextRepository.INPUT_ERROR_MSG);
            }
        }
    }

    private Connect6GameRule chooseVariation() {
        while (true) {
            try {
                gui.print(TextRepository.CHOOSE_VARIATION);
                gui.printList(possibleGameRules);
                String input = gui.getUserInput();

                int selectedVariation = Integer.parseInt(input);
                switch (selectedVariation) {
                    case 1:
                    case 2:
                        return possibleGameRules.get(selectedVariation - 1);
                    default:
                        gui.print(TextRepository.INPUT_ERROR_MSG);
                }

            } catch (IOException ignored) {
                gui.print(TextRepository.INPUT_ERROR_MSG);
            }
        }
    }

    private int chooseBoardSize() {
        while (true) {
            try {
                gui.print(TextRepository.CHOOSE_BOARD_SIZE);
                gui.printOptions(possibleBoardSizes);
                String input = gui.getUserInput();

                int boardsize = Integer.parseInt(input);
                if (possibleBoardSizes.contains(boardsize)) {
                    return boardsize;
                } else {
                    gui.print(TextRepository.INPUT_ERROR_MSG);
                }

            } catch (IOException ignored) {
                gui.print(TextRepository.INPUT_ERROR_MSG);
            }
        }
    }

    private ArrayList<Player> choosePlayerCount() {
        while (true) {
            try {
                gui.print(TextRepository.CHOOSE_PLAYER_COUNT);
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
                    gui.print(TextRepository.INPUT_ERROR_MSG);
                }
            } catch (IOException ignored) {
                gui.print(TextRepository.INPUT_ERROR_MSG);
            }
        }
    }
}

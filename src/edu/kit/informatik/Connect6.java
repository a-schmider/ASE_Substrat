/**
 * 
 */
package edu.kit.informatik;

import java.util.HashMap;

/** @author Andreas Schmider
 *
 */
public class Connect6 {

  private static boolean finished = false;

  /** checks if the startarguments are allowed
   * requiered parameters are standard/torus, boardsize(18/20) and amount of players
   * @param args
   *          standard
   * @return startallowed true, if the beginrequirements are correct
   */
  public static boolean checkStart(String[] args) {
    boolean start = true;
    boolean error = false;


    if (args.length != 3) {
      error = true;
    } else {
      if (!stdOrTrs(args[0])) {
        error = true;
      } else {
        if (!correctGameBoardSize(args[1])) {
          error = true;
        } else {
          if (!correctNumbers(args[2])) {
            error = true;
          }
        }
      }
    }

    if (error) {
      Terminal.printError("two startarguments needed; (standard/torus) (2/3/4) ");
      start = false;
    }

    return start;
  }

  /** converts a String to Integer
   * 
   * @param s
   *          String which will be converted
   * @return number converted String
   */
  public static int stringToInt(String s) {
    int number;

    try {
      number = Integer.parseInt(s);
    } catch (NumberFormatException e) {
      number = Integer.MIN_VALUE;
    }

    return number;
  }

  /** checks if the game mode is standard or torus
   * 
   * @param s
   *          input
   * @return accepted true, if standard
   */
  public static boolean stdOrTrs(String s) {
    boolean b = false;

    if (s.equals("standard") || s.equals("torus")) {
      b = true;
    }

    return b;
  }

  /** checks if the current Players amount is allowed
   * 
   * @param s
   *          String with number
   * @return allowed true, if the input is 2/3/4
   */
  public static boolean correctNumbers(String s) {
    boolean allowed = true;

    int o = stringToInt(s);
    if (o == Integer.MIN_VALUE) {
      allowed = false;
    } else {

      if (!(o == 2 || o == 3 || o == 4)) {
        allowed = false;
      }
    }

    return allowed;
  }

  /** checks if the current gameBoardSize is allowed
   * 
   * @param s
   *          String with number
   * @return allowed true, if the input is 18/20
   */
  public static boolean correctGameBoardSize(String s) {
    boolean allowed = true;

    int o = stringToInt(s);
    if (o == Integer.MIN_VALUE) {
      allowed = false;
    } else {
      if (!(o == 18 || o == 20)) {
        allowed = false;
      }
    }

    return allowed;
  }

  /** decides which GameRule is active and checks for end state
   * 
   * @param gI
   *          gameinfo
   * @param gB
   *          gameboard
   * @param compactArray 
   *          with both x and y positions
   * @param p
   *          player
   * @param standard
   *          standard gamerule
   * @param torus
   *          torus gamerule
   */
  public static void placeUndefined(GameInfo gI, GameBoard gB, int[] compactArray, Player p, GameRule standard,
      GameRule torus) {
    boolean win = false;
    boolean fullBoard = false;
    boolean error = false;
    String piece = new String(p.getGamingPiece());

    // StandardMode_________________________________________________________________

    if (gI.isGRStandard()) {
      error = placeDefined(gB, compactArray, piece, standard, gI);
      if (!error) {
        gI.addTwoTurns();
        win = standard.checkWin(compactArray, piece, gI, gB);
        fullBoard = standard.checkFullBoard(gB, gI);
      }

    }

    // TorusMode____________________________________________________________________

    else {
      int size = gI.getGameBoardSize();
      int i2 = ((compactArray[0] + size) % size);
      int j2 = ((compactArray[1] + size) % size);
      int k2 = ((compactArray[2] + size) % size);
      int l2 = ((compactArray[3] + size) % size);
      do {
        i2 = ((i2 + size) % size);
        j2 = ((j2 + size) % size);
        k2 = ((k2 + size) % size);
        l2 = ((l2 + size) % size);
      } while (i2 < 0 || j2 < 0 || k2 < 0 || l2 < 0);
      int[] modArray = new int[4];
      modArray[0] = i2;
      modArray[1] = j2;
      modArray[2] = k2;
      modArray[3] = l2;
      error = placeDefined(gB, modArray, piece, torus, gI);
      if (!error) {
        gI.addTwoTurns();
        win = torus.checkWin(modArray, piece, gI, gB);
        fullBoard = torus.checkFullBoard(gB, gI);
      }

    }

    // endBoth______________________________________________________________________

    if (win) {
      Terminal.printLine(p.getGamingPiece() + " wins");
    } else {
      if (fullBoard) {
        Terminal.printLine("draw");
      } else if (!error) {
        Terminal.printLine("OK");
      }
    }
    if (win || fullBoard) {
      preventPlace();
    }

  }

  /** places a gamingPiece at the current Location
   * 
   * @param gB
   *          gameboard
   * @param compactArray
   *          with both x and y positions
   * @param gamingPiece
   *          playermark
   * @param gameRule
   *          correct gamerule
   * @param gI
   *          gameinfo
   * @return error true, if a error occured
   */
  public static boolean placeDefined(GameBoard gB, int[] compactArray, String gamingPiece, GameRule gameRule,
      GameInfo gI) {
    boolean error = false;
    int i = compactArray[0];
    int j = compactArray[1];
    int k = compactArray[2];
    int l = compactArray[3];
    if (gameRule.checkAllowedPlaceRules(i, j, gB) && gameRule.checkAllowedPlaceRules(k, l, gB)) {
      if ((i != k || j != l)) {
        gB.place(i, j, gamingPiece, gameRule);
        gB.place(k, l, gamingPiece, gameRule);
      } else {
        Terminal.printError("already occupied");
        error = true;
      }

    } else {
      error = true;
    }
    return error;
  }

  /** converts String array if Integer array if possible
   * 
   * @param s String with arguments
   * @return integer array
   * @throws NumberFormatException when not convertable
   */
  public static int[] stringArrayToIntArray(String[] s) throws NumberFormatException {
    int[] intArray = new int[s.length];

    for (int i = 0; i < s.length; i++) {
      intArray[i] = stringToInt(s[i]);
    }

    return intArray;
  }

  /** reads a line of text and splits it in command and arguments
   * 
   * @param gI
   *          gameinfo
   * @param gB
   *          gamboard
   * @param player
   *          player
   * @param standard
   *          standard gamerule
   * @param torus
   *          torus gamerule
   * @return quit true, if command was quit
   */
  public static boolean getCommand(GameInfo gI, GameBoard gB, Player player, GameRule standard, GameRule torus) {
    boolean quit = false;
    String command;
    String[] arrayString = new String[2];

    command = Terminal.readLine();

    try {
      arrayString = command.split("\\s+");
    } catch (NullPointerException e) {
      Terminal.printError("invalid input");
    }

    command = arrayString[0];

    byte arguments = checkForCommand(command);
    if (arguments < 0) {
      Terminal.printError("invalid command");
    } else {
      if (arrayString.length > 2) {
        Terminal.printError("too much arguments");
      } else {
        if (arrayString.length == 2) {
          doCommand(command, arrayString[1], gI, gB, player, standard, torus);
        } else {
          quit = doCommand(command, "", gI, gB, player, standard, torus);
        }
      }
    }
    return quit;
  }

  /** checks if it is a valid command
   * 
   * @param s command
   * @return aOA amount of arguments
   */
  public static byte checkForCommand(String s) {
    try {
      byte allowed = 1;
      switch (s) {
        case "print":
        case "reset":
        case "quit":
          allowed = 0;
        case "help":
        case "place":
        case "rowprint":
        case "colprint":
        case "state":
          break;
        default:
          allowed = -1;
      }
      return allowed;
    } catch (NullPointerException e) {
      // TODO Auto-generated catch block
      return -2;
    }
  }

  /** checks which command and executes the correct methods
   * 
   * @param command
   *          command
   * @param input
   *          arguments
   * @param gI
   *          gameinfo
   * @param gB
   *          gameboard
   * @param player
   *          player
   * @param standard
   *          standard gamerule
   * @param torus
   *          torus gamerule
   * @return quit true, if command was quit
   */
  public static boolean doCommand(String command, String input, GameInfo gI, GameBoard gB, Player player,
      GameRule standard, GameRule torus) {
    boolean quit = false;
    if (input.equals("")) {
      switch (command) {
        case "help":
          System.out.println("Available commands:\r\n" +
                  "\r\nprint:\r\n Prints the entire board\r\n" +
                  "\r\nreset:\r\n ???\r\n" +
                  "\r\nquit:\r\n Terminates the program\r\n" +
                  "\r\nplace w x y z:\r\n Places stones on the fields w|x and y|z\r\n" +
                  "\r\nrowprint z:\r\n Prints the selected row\r\n" +
                  "\r\ncolprint z:\r\n Prints the selected column\r\n" +
                  "\r\nstate y z:\r\n Prints which stone is placed on the selected field\r\n\r\n");
          break;
        case "print":
          gB.boardPrint();
          break;
        case "reset":
          gB.resetGameBoard();
          gI.resetTurn();
          finished = false;
          break;
        case "quit":
          quit = true;
          break;
        default:
          Terminal.printError("argument needed");
        }
    } else {
      int[] intArray = stringArrayToIntArray(split(input));
      int mod = gI.getGameBoardSize();
      if (!gI.isGRStandard()) {
        intArray[0] = moduloMod(mod, intArray[0]);
        if (intArray.length > 1) {
          intArray[1] = moduloMod(mod, intArray[1]);
          if (intArray.length > 2) {
            intArray[2] = moduloMod(mod, intArray[2]);
            if (intArray.length > 3) {
              intArray[3] = moduloMod(mod, intArray[3]);
            }
          }
        }
      }

      switch (command) {
      case "place":
        if (intArray.length != 4) {
          Terminal.printError("four integer needed");
        } else {
          if (!finished) {
            int[] compactArray = new int[4];
            compactArray[0] = intArray[0];
            compactArray[1] = intArray[1];
            compactArray[2] = intArray[2];
            compactArray[3] = intArray[3];
            placeUndefined(gI, gB, compactArray, player, standard, torus);
          } else {
            Terminal.printLine("not allowed after a finished Game, type \"reset\"");
          }
        }
        break;

      case "rowprint":
        if (intArray.length != 1) {
          Terminal.printError("one integer needed");
        } else {
          gB.rowPrint(intArray[0]);
        }
        break;
      case "colprint":
        if (intArray.length != 1) {
          Terminal.printError("one integer needed");
        } else {
          gB.colPrint(intArray[0]);
        }
        break;
      case "state":
        if (intArray.length != 2) {
          Terminal.printError("two integer needed");
        } else {
          gB.stateField(intArray[0], intArray[1]);
        }
        break;
      default:
        Terminal.printError("no arguments needed");
      }
    }
    return quit;
  }

  private static int moduloMod(int mod, int i) {
    // TODO Auto-generated method stub
    int i2 = (i + mod) % mod;
    do {
      i2 = (i2 + mod) % mod;
    } while (i2 < 0);
    return i2;
  }

  /** splits a String at ";"
   * 
   * @param s input
   * @return split splits arguments
   */
  public static String[] split(String s) {
    String[] split;
    split = s.split(";");
    return split;
  }

  /** sets finished to true
   * 
   */
  public static void preventPlace() {
    finished = true;
  }

  /**
   * @param args
   *          standard
   */
  public static void main(String[] args) {
    System.out.println("Type \"help\" for available commands");

    boolean checkStart = checkStart(args);
    if (checkStart) {
      // preparations
      boolean quit = false;
      GameInfo gameInfo = new GameInfo(args[0], Integer.parseInt(args[1]), Integer.parseInt(args[2]));
      GameRule standard = new GRStandard();
      GameRule torus = new GRTorus();
      Player p1 = new Player();
      Player p2 = new Player();
      Player p3 = new Player();
      Player p4 = new Player();
      Player activePlayer = p1;
      HashMap<Integer, Player> m = new HashMap<Integer, Player>();
      m.put(0, p1);
      m.put(1, p2);
      m.put(2, p3);
      m.put(3, p4);
      GameBoard gameBoard = new GameBoard(gameInfo.getGameBoardSize());

      // real game
      while (!quit) {
        activePlayer = (Player) m.get((gameInfo.getTurn() / 2) % gameInfo.getAmountOfPlayers());
        quit = getCommand(gameInfo, gameBoard, activePlayer, standard, torus);
      }
    }

  }

}

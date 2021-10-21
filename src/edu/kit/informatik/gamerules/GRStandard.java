package edu.kit.informatik.gamerules;

import edu.kit.informatik.models.Connect6GameBoard;
import edu.kit.informatik.models.GameFieldArea;
import edu.kit.informatik.models.GameInfo;
import edu.kit.informatik.userinterface.Terminal;

/**
 * @author Andreas Schmider
 */
public class GRStandard extends GameRule {

    /**
     * checks if i,j at gameBoard and if its free
     *
     * @param i  column
     * @param j  row
     * @param gB gameboard
     * @return allowed true, if allowed move
     */
    public boolean checkAllowedPlaceRules(int i, int j, Connect6GameBoard gB) {
        boolean allowed = false;
        if (i >= 0 && i < gB.getBoardSize() && j >= 0 && j < gB.getBoardSize()) {
            if (gB.getField(i, j) == "**") {
                allowed = true;
            } else {
                Terminal.printError("already occupied, again");
            }
        } else {
            int help = gB.getBoardSize() - 1;
            Terminal.printError("numbers must be between 0 and " + help + ", again");
        }
        return allowed;
    }

    /**
     * checks if six in a row
     *
     * @param compactArray contents the four coordinates
     * @param piece        field content (playermark)
     * @param gI           gameinfo
     * @param gB           gameboard
     * @return win true, if the player has won
     */
    public boolean checkWin(int[] compactArray, String piece, GameInfo gI, Connect6GameBoard gB) {
        boolean win = false;
        int i = compactArray[0];
        int j = compactArray[1];
        int k = compactArray[2];
        int l = compactArray[3];
        GameFieldArea gFA = new GameFieldArea(i, j, gI);
        GameFieldArea gFA2 = new GameFieldArea(k, l, gI);
        if (checkAround(piece, gFA.getSurrounding(), gB, i, j)) {
            win = true;
        }
        if (checkAround(piece, gFA2.getSurrounding(), gB, k, l)) {
            win = true;
        }
        return win;
    }

    /**
     * checks how many gamePieces of the same Player is in a row
     *
     * @param piece       p
     * @param surrounding s
     * @param gB          gameBoard
     * @param x           x
     * @param y           y
     * @return true, if row > 5
     */
    private boolean checkAround(String piece, int[] surrounding, Connect6GameBoard gB, int x, int y) {
        int[] count = new int[8];
        boolean ret = false;
        count[0] = checkUpLeft(piece, surrounding[0], gB, x, y);
        count[1] = checkUp(piece, surrounding[1], gB, x, y);
        count[2] = checkUpRight(piece, surrounding[2], gB, x, y);
        count[3] = checkRight(piece, surrounding[3], gB, x, y);
        count[4] = checkDownRight(piece, surrounding[4], gB, x, y);
        count[5] = checkDown(piece, surrounding[5], gB, x, y);
        count[6] = checkDownLeft(piece, surrounding[6], gB, x, y);
        count[7] = checkLeft(piece, surrounding[7], gB, x, y);

        for (int i = 0; i < 4; i++) {
            if (count[i] + count[4 + i] > 4) {
                ret = true;
                break;
            }
        }
        return ret;
    }

    /**
     * counts how many gamePieces of the same Player is in the left row
     *
     * @param piece p
     * @param i     i
     * @param gB    gameBoard
     * @param x     x
     * @param y     y
     * @return number
     */
    private int checkLeft(String piece, int i, Connect6GameBoard gB, int x, int y) {
        boolean correct = true;
        int matches = 0;
        int j = 0;
        while (j < i) {
            if (correct) {
                String s = gB.getField(x, y - (j + 1));
                if (s.equals(piece)) {
                    matches++;
                } else {
                    correct = false;
                }
            } else {
                break;
            }
            j++;
        }
        return matches;
    }

    /**
     * counts how many gamePieces of the same Player is in the down-left row
     *
     * @param piece p
     * @param i     i
     * @param gB    gameBoard
     * @param x     x
     * @param y     y
     * @return number
     */
    private int checkDownLeft(String piece, int i, Connect6GameBoard gB, int x, int y) {
        boolean correct = true;
        int matches = 0;
        int j = 0;
        while (j < i) {
            if (correct) {
                String s = gB.getField(x + (j + 1), y - (j + 1));
                if (s.equals(piece)) {
                    matches++;
                } else {
                    correct = false;
                }
            } else {
                break;
            }
            j++;
        }
        return matches;
    }

    /**
     * counts how many gamePieces of the same Player is in the down row
     *
     * @param piece p
     * @param i     i
     * @param gB    gameBoard
     * @param x     x
     * @param y     y
     * @return number
     */
    private int checkDown(String piece, int i, Connect6GameBoard gB, int x, int y) {
        boolean correct = true;
        int matches = 0;
        int j = 0;
        while (j < i) {
            if (correct) {
                String s = gB.getField(x + (j + 1), y);
                if (s.equals(piece)) {
                    matches++;
                } else {
                    correct = false;
                }
            } else {
                break;
            }
            j++;
        }
        return matches;
    }

    /**
     * counts how many gamePieces of the same Player is in the down-right row
     *
     * @param piece p
     * @param i     i
     * @param gB    gameBoard
     * @param x     x
     * @param y     y
     * @return number
     */
    private int checkDownRight(String piece, int i, Connect6GameBoard gB, int x, int y) {
        boolean correct = true;
        int matches = 0;
        int j = 0;
        while (j < i) {
            if (correct) {
                String s = gB.getField(x + (j + 1), y + (j + 1));
                if (s.equals(piece)) {
                    matches++;
                } else {
                    correct = false;
                }
            } else {
                break;
            }
            j++;
        }
        return matches;
    }

    /**
     * counts how many gamePieces of the same Player is in the right row
     *
     * @param piece p
     * @param i     i
     * @param gB    gameBoard
     * @param x     x
     * @param y     y
     * @return number
     */
    private int checkRight(String piece, int i, Connect6GameBoard gB, int x, int y) {
        boolean correct = true;
        int matches = 0;
        int j = 0;
        while (j < i) {
            if (correct) {
                String s = gB.getField(x, y + (j + 1));
                if (s.equals(piece)) {
                    matches++;
                } else {
                    correct = false;
                }
            } else {
                break;
            }
            j++;
        }
        return matches;
    }

    /**
     * counts how many gamePieces of the same Player is in the up-right row
     *
     * @param piece p
     * @param i     i
     * @param gB    gameBoard
     * @param x     x
     * @param y     y
     * @return number
     */
    private int checkUpRight(String piece, int i, Connect6GameBoard gB, int x, int y) {
        boolean correct = true;
        int matches = 0;
        int j = 0;
        while (j < i) {
            if (correct) {
                String s = gB.getField(x - (j + 1), y + (j + 1));
                if (s.equals(piece)) {
                    matches++;
                } else {
                    correct = false;
                }
            } else {
                break;
            }
            j++;
        }
        return matches;
    }

    /**
     * counts how many gamePieces of the same Player is in the up row
     *
     * @param piece p
     * @param i     i
     * @param gB    gameBoard
     * @param x     x
     * @param y     y
     * @return number
     */
    private int checkUp(String piece, int i, Connect6GameBoard gB, int x, int y) {
        boolean correct = true;
        int matches = 0;
        int j = 0;
        while (j < i) {
            if (correct) {
                String s = gB.getField(x - (j + 1), y);
                if (s.equals(piece)) {
                    matches++;
                } else {
                    correct = false;
                }
            } else {
                break;
            }
            j++;
        }
        return matches;
    }

    /**
     * counts how many gamePieces of the same Player is in the up-left row
     *
     * @param piece p
     * @param i     i
     * @param gB    gameBoard
     * @param x     x
     * @param y     y
     * @return number
     */
    private int checkUpLeft(String piece, int i, Connect6GameBoard gB, int x, int y) {
        boolean correct = true;
        int matches = 0;
        int j = 0;
        while (j < i) {
            if (correct) {
                String s = gB.getField(x - (j + 1), y - (j + 1));
                if (s.equals(piece)) {
                    matches++;

                } else {
                    correct = false;
                }
            } else {
                break;
            }
            j++;
        }
        return matches;
    }

}

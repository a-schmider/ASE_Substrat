/**
 * 
 */
package edu.kit.informatik;

/**
 * @author Andreas Schmider
 *
 */

public class GRTorus extends GameRule {

  /**
   * checks if the field is empty
   * 
   * @param i
   *          column
   * @param j
   *          row
   * @param gB
   *          gameboard
   * @return allowed
   */
  public boolean checkAllowedPlaceRules(int i, int j, GameBoard gB) {
    boolean allowed = false;
    if (gB.getField(i, j) == "**") {
      allowed = true;
    } else {
      Terminal.printError("already occupied, again");
    }
    return allowed;
  }

  /**
   * checks if six in a row
   * 
   * @param compactArray 
   *          contents the four coordinates
   * @param piece
   *          field content (playermark)
   * @param gI
   *          gameinfo
   * @param gB
   *          gameboard
   * @return win true, if player has won
   */
  public boolean checkWin(int[] compactArray, String piece, GameInfo gI, GameBoard gB) {
    boolean win = false;
    int x1 = compactArray[0];
    int y1 = compactArray[1];
    int x2 = compactArray[2];
    int y2 = compactArray[3];
    GameFieldArea gFA = new GameFieldArea(x1, y1, gI, 5);
    GameFieldArea gFA2 = new GameFieldArea(x2, y2, gI, 5);
    if (checkAround(piece, gFA.getSurrounding(), gB, x1, y1, gI.getGameBoardSize())) {
      win = true;
    }
    if (checkAround(piece, gFA2.getSurrounding(), gB, x2, y2, gI.getGameBoardSize())) {
      win = true;
    }
    return win;
  }

  /**
   * checks how many gamePieces of the same Player is in a row
   * 
   * @param piece
   * @param surrounding
   * @param gB
   * @param x1
   * @param y1
   * @param size
   * @return true, if row > 5
   */
  private boolean checkAround(String piece, int[] surrounding, GameBoard gB, int x1, int y1, int size) {
    // TODO Auto-generated method stub
    int[] count = new int[8];
    boolean ret = false;

    count[0] = checkUpLeft(piece, surrounding[0], gB, x1, y1, size);
    count[1] = checkUp(piece, surrounding[1], gB, x1, y1, size);
    count[2] = checkUpRight(piece, surrounding[2], gB, x1, y1, size);
    count[3] = checkRight(piece, surrounding[3], gB, x1, y1, size);
    count[4] = checkDownRight(piece, surrounding[4], gB, x1, y1, size);
    count[5] = checkDown(piece, surrounding[5], gB, x1, y1, size);
    count[6] = checkDownLeft(piece, surrounding[6], gB, x1, y1, size);
    count[7] = checkLeft(piece, surrounding[7], gB, x1, y1, size);

    for (int i = 0; i < 4; i++) {
      if (count[i] + count[4 + i] > 4) {
        ret = true;
      }
    }
    return ret;
  }

  /**
   * counts how many gamePieces of the same Player is in the left row
   * 
   * @param piece
   * @param i
   * @param gB
   * @param x
   * @param y
   * @param mod
   * @return number
   */
  private int checkLeft(String piece, int i, GameBoard gB, int x, int y, int mod) {
    // TODO Auto-generated method stub
    boolean correct = true;
    int matches = 0;
    int j = 0;
    int x1;
    int y1;
    while (j < i) {
      if (correct) {
        x1 = x;
        y1 = (y - (j + 1));
        do {
          x1 = ((x1 + mod) % mod);
          y1 = ((y1 + mod) % mod);
        } while (x1 < 0 || y1 < 0);
        String s = gB.getField(x1, y1);
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
   * @param piece
   * @param i
   * @param gB
   * @param x
   * @param y
   * @param mod
   * @return number
   */
  private int checkDownLeft(String piece, int i, GameBoard gB, int x, int y, int mod) {
    // TODO Auto-generated method stub
    boolean correct = true;
    int matches = 0;
    int j = 0;
    int x1;
    int y1;
    while (j < i) {
      if (correct) {
        x1 = (x + (j + 1));
        y1 = (y - (j + 1));
        do {
          x1 = ((x1 + mod) % mod);
          y1 = ((y1 + mod) % mod);
        } while (x1 < 0 || y1 < 0);
        String s = gB.getField(x1, y1);
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
   * @param piece
   * @param i
   * @param gB
   * @param x
   * @param y
   * @param mod
   * @return number
   */
  private int checkDown(String piece, int i, GameBoard gB, int x, int y, int mod) {
    // TODO Auto-generated method stub
    boolean correct = true;
    int matches = 0;
    int j = 0;
    int x1;
    int y1;
    while (j < i) {
      if (correct) {
        x1 = (x + (j + 1));
        y1 = y;
        do {
          x1 = ((x1 + mod) % mod);
          y1 = ((y1 + mod) % mod);
        } while (x1 < 0 || y1 < 0);
        String s = gB.getField(x1, y1);
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
   * @param piece
   * @param i
   * @param gB
   * @param x
   * @param y
   * @param mod
   * @return number
   */
  private int checkDownRight(String piece, int i, GameBoard gB, int x, int y, int mod) {
    // TODO Auto-generated method stub
    boolean correct = true;
    int matches = 0;
    int j = 0;
    int x1;
    int y1;
    while (j < i) {
      if (correct) {
        x1 = x + j + 1;
        y1 = y + j + 1;
        do {
          x1 = ((x1 + mod) % mod);
          y1 = ((y1 + mod) % mod);
        } while (x1 < 0 || y1 < 0);
        String s = gB.getField(x1, y1);
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
   * @param piece
   * @param i
   * @param gB
   * @param x
   * @param y
   * @param mod
   * @return number
   */
  private int checkRight(String piece, int i, GameBoard gB, int x, int y, int mod) {
    // TODO Auto-generated method stub
    boolean correct = true;
    int matches = 0;
    int j = 0;
    int x1;
    int y1;
    while (j < i) {
      if (correct) {
        x1 = x;
        y1 = (y + (j + 1));
        do {
          x1 = ((x1 + mod) % mod);
          y1 = ((y1 + mod) % mod);
        } while (x1 < 0 || y1 < 0);
        String s = gB.getField(x1, y1);
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
   * @param piece
   * @param i
   * @param gB
   * @param x
   * @param y
   * @param mod
   * @return number
   */
  private int checkUpRight(String piece, int i, GameBoard gB, int x, int y, int mod) {
    // TODO Auto-generated method stub
    boolean correct = true;
    int matches = 0;
    int j = 0;
    int x1;
    int y1;
    while (j < i) {
      if (correct) {
        x1 = (x - (j + 1));
        y1 = (y + (j + 1));
        do {
          x1 = ((x1 + mod) % mod);
          y1 = ((y1 + mod) % mod);
        } while (x1 < 0 || y1 < 0);
        String s = gB.getField(x1, y1);
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
   * @param piece
   * @param i
   * @param gB
   * @param x
   * @param y
   * @param mod
   * @return number
   */
  private int checkUp(String piece, int i, GameBoard gB, int x, int y, int mod) {
    // TODO Auto-generated method stub
    boolean correct = true;
    int matches = 0;
    int j = 0;
    int x1;
    int y1;
    while (j < i) {
      if (correct) {
        x1 = (x - (j + 1));
        y1 = y;
        do {
          x1 = ((x1 + mod) % mod);
          y1 = ((y1 + mod) % mod);
        } while (x1 < 0 || y1 < 0);
        String s = gB.getField(x1, y1);
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
   * @param piece
   * @param i
   * @param gB
   * @param x
   * @param y
   * @param mod
   * @return number
   */
  private int checkUpLeft(String piece, int i, GameBoard gB, int x, int y, int mod) {
    // TODO Auto-generated method stub
    boolean correct = true;
    int matches = 0;
    int j = 0;
    int x1;
    int y1;
    while (j < i) {
      if (correct) {
        x1 = (x - (j + 1));
        y1 = (y - (j + 1));
        do {
          x1 = ((x1 + mod) % mod);
          y1 = ((y1 + mod) % mod);
        } while (x1 < 0 || y1 < 0);
        String s = gB.getField(x1, y1);
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

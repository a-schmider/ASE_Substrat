/**
 * 
 */
package edu.kit.informatik;

/**
 * @author Andi
 *
 */
public class GameFieldArea {
  private int x;
  private int y;
  private int[] surrounding = new int[8];

  /**
   * sets x,y and surrounding
   * 
   * @param i
   *          column
   * @param j
   *          row
   * @param gI
   *          gameinfo
   */
  public GameFieldArea(int i, int j, GameInfo gI) {
    this.x = i;
    this.y = j;
    this.setSurrounding(gI);
  }

  /**
   * sets x,y and fills complete surrounding with range
   * 
   * @param i
   *          column
   * @param j
   *          row
   * @param gI
   *          gameinfo
   * @param range
   *          how many fields have to be checked in one direction
   */
  public GameFieldArea(int i, int j, GameInfo gI, int range) {
    this.x = i;
    this.y = j;
    this.setSurrounding(range);
  }

  /**
   * fills surrounding with j
   * 
   * @param j
   */
  private void setSurrounding(int j) {
    for (int i = 0; i < surrounding.length; i++) {
      this.surrounding[i] = j;
    }
  }

  /**
   * sets surrounding to maximum range to boardedge
   * 
   * @param gI
   */
  private void setSurrounding(GameInfo gI) {
    int[] stages = new int[2];
    stages = this.getStage(gI);

    this.surrounding[0] = compare5(min(stages[0], stages[1]));
    this.surrounding[1] = compare5(stages[0]);
    this.surrounding[2] = compare5(min(stages[0], 10 - stages[1]));
    this.surrounding[3] = compare5(10 - stages[1]);
    this.surrounding[4] = compare5(min(10 - stages[0], 10 - stages[1]));
    this.surrounding[5] = compare5(10 - stages[0]);
    this.surrounding[6] = compare5(min(10 - stages[0], stages[1]));
    this.surrounding[7] = compare5(stages[1]);
  }

  /**
   * 
   * @param gI
   * @return stages (horizontal,vertical)
   */
  private int[] getStage(GameInfo gI) {
    int[] stages = new int[2];
    stages[0] = this.getStageVertical(gI);
    stages[1] = this.getStageHorizontal(gI);
    return stages;
  }

  /**
   * 
   * @param gI
   * @return stage of the field (vertical)
   */
  private int getStageVertical(GameInfo gI) {
    // TODO Auto-generated method stub
    int ret;
    boolean eighteen = false;
    int size = gI.getGameBoardSize();
    if (size == 18) {
      eighteen = true;
    }
    if (this.x > 4 && this.x < size - 5) {
      ret = 5;
    } else if (eighteen) {
      ret = this.getLvl18(this.x);
    } else {
      ret = this.getLvl20(this.x);
    }
    return ret;
  }

  /**
   * sort categories for field with size 20
   * 
   * @param x
   * @return
   */
  private int getLvl20(int x) {
    int ret = 5;
    switch (x) {
    case 0:
      ret = 0;
      break;
    case 1:
      ret = 1;
      break;
    case 2:
      ret = 2;
      break;
    case 3:
      ret = 3;
      break;
    case 4:
      ret = 4;
      break;
    case 15:
      ret = 6;
      break;
    case 16:
      ret = 7;
      break;
    case 17:
      ret = 8;
      break;
    case 18:
      ret = 9;
      break;
    case 19:
      ret = 10;
      break;
    default:
    }
    return ret;

  }

  /**
   * sort categories for field with size 18
   * 
   * @param x
   * @return stage
   */
  private int getLvl18(int x) {
    int ret = 5;
    switch (x) {
    case 0:
      ret = 0;
      break;
    case 1:
      ret = 1;
      break;
    case 2:
      ret = 2;
      break;
    case 3:
      ret = 3;
      break;
    case 4:
      ret = 4;
      break;
    case 13:
      ret = 6;
      break;
    case 14:
      ret = 7;
      break;
    case 15:
      ret = 8;
      break;
    case 16:
      ret = 9;
      break;
    case 17:
      ret = 10;
      break;
    default:
    }
    return ret;
  }

  /**
   * 
   * @param gI
   * @return stage of the field (vertical)
   */
  private int getStageHorizontal(GameInfo gI) {
    // TODO Auto-generated method stub
    int ret;
    boolean eighteen = false;
    int size = gI.getGameBoardSize();
    if (size == 18) {
      eighteen = true;
    }
    if (this.y > 4 && this.y < size - 5) {
      ret = 5;
    } else if (eighteen) {
      ret = this.getLvl18(this.y);
    } else {
      ret = this.getLvl20(this.y);
    }
    return ret;
  }

  /**
   * 
   * @param i
   * @return i or 5 if i > 5
   */
  private int compare5(int i) {
    int ret;
    if (i > 5) {
      ret = 5;
    } else {
      ret = i;
    }
    return ret;
  }

  /**
   * returns minimum of x and y
   * 
   * @param x
   * @param y
   * @return
   */
  private int min(int x, int y) {
    int ret;
    if (x < y) {
      ret = x;
    } else {
      ret = y;
    }
    return ret;
  }

  /**
   * 
   * @return surrounding
   */
  public int[] getSurrounding() {
    // TODO Auto-generated method stub
    return this.surrounding;
  }
}

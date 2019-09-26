/**
 * 
 */
//package gomuku;

import java.awt.Color;

/**
 * @author sharl
 * Sorry!
 */
class DesperateAgent_180621084  extends GomokuPlayer {
	  public Move chooseMove(Color[][] paramArrayOfColor, Color paramColor)
	  {
	    for (int i = 3; i < 8; i++) {
	      for (int j = 3; j < 8; j++) {
	        if (paramArrayOfColor[i][j] == null) {
	          return new Move(i, j);
	        }
	      }
	    }
	    return null;
	  }
	}

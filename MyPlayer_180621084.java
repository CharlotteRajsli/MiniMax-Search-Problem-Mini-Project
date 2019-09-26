//package gomuku;

import java.awt.Color;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @author sharl
 *How to sort Color var?
 */
public class MyPlayer_180621084 extends GomokuPlayer {
	public Color [][] board= new Color [8][8];
	public Color[][] boardCopy= new Color [8][8];
	
	
	public Color[][] copyBoard(Color[][] board) {
		
		//this.boardCopy = new Color [8][8];
		//this.board = new Color [8][8];
			
		int j, i;
		for ( i = 0; i < 8; i++) {
			for ( j = 0; j < 8; j++) {
				this.boardCopy [i][j] = board[i][j];
			}
		}
		System.out.println("board print" + boardCopy);
		return boardCopy;
	
	}
	
	public boolean isBoardEmpty() {		 
	//first default move
	//if board empty
	boolean isEmpty = true;
	//this.board = copyBoard(board);
	for (int i = 0; i < 8; i++) {
		for (int j = 0; j < 8; j++) {
			if (this.board [i][j] != null) {
				
				System.out.println("I should break now or do something different from first move, bc I'm not an empty board");
				isEmpty =false;
				//break;
			
			}
			
		}	
		
	}
	if	(isEmpty == true) {
		System.out.println("board empty, my first move middle of the board(4,3)");
	}
	return isEmpty;
	}
	
		
	

	/**
	 * Get opponent's turn and update board
	 * @param  move opponent's move
	 * @return opponent's move
	 */
	/*String receiveTurn(String move) {
		//>>>>??  am I white or black? empty board?
		BoardReader_180621084 b = new BoardReader_180621084();
		if (isBoardEmpty() == true) {
			Color p = Color.white;
		}
		b.placeMove(Color.black, move, false);
		return move;
	}
	*/
	
	/**
	 * Execute a turn
	 * @param  move to execute
	 * @return agent's move
	 */
	/*String takeTurn(Color p) {
		BoardReader_180621084 b = new BoardReader_180621084();
		b.boardLocal = this.copyBoard(board);
		String move = pickMove();
		b.placeMove(p, move, false);
		//System.out.println("takeTurn()" +board);
		return move;
	}
	*/

	
	String pickMove() {
		// use minimax
		MiniMax_180621084 mm = new MiniMax_180621084();
		Object[] move = mm.mmab(board, 1, Double.NEGATIVE_INFINITY,
				Double.POSITIVE_INFINITY);
		return (String) move[1];
	}
	
	
	
	
	
	
	
	public Move chooseMove(Color[][] inputBoard, Color p) {
		board = copyBoard(inputBoard);
		
		if (isBoardEmpty() == true) {
			return new Move (4, 3);
		}
		
			else {
		
		//pickMove() already being called by takeTurn
			
		String finalmove = pickMove();
		System.out.println(finalmove);
		
		
		int row = Integer.valueOf(finalmove.charAt(0));
		int col = Integer.valueOf(finalmove.charAt(2));

		
		return new Move(row,col);
		}
		
		
	}	
}


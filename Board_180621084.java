package gomuku;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * @author sharl
 *Board class to define look around methods (row, coloumn, left and right diagonal
 * ?and legal moves
 * Move (int row, int column)
 */
public class Board_180621084 {
	Color [][] board;
	int r; //row DELETE if not used
	int c; //coloumn DELETE 
	int n = 8; //dimension of the board
	
	char winner;
	char nextP; //next player
	char previousP; //?? previous player
	
	String lastMove; //current last move during the game

	/**
	 * board constructor
	 * 
	 */
	public Board_180621084() {
		this.board = new char [8][8]; //board with dimensions 8x8
		
		//iterate through the board and fill it with empty spaces
		for (int i = 0; i<8; i++) {  //i as rows 0-7
			for (int j = 0; j<8; j++) { //j as coloums 0-7
				this.board [i][j] = '.';  //. as empty space
			}
		}
		this.winner = '.';
	}
	
	/**
	 * copy constructor for board, for during the game
	 * so any changes made by MiniMax are not reflected on the actual board
	 * @param other - board to copy
	 */
	public Board_180621084(Board_180621084 other) {
		this.board = new char [8][8];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				this.board[i][j] = other.board[i][j];
			}
		}
		this.nextP = other.nextP;
		this.previousP = other.previousP;		
	}
	
	/**
	 * Get set of empty space on board
	 * @return empty  set of empty locations
	 */
	Set<String> getEmpties() {
		Set<String> empties = new HashSet<String>();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (board[i][j] == '.') {
					empties.add[i] [j];
				}

			}
		}
		return empties;
	}
}

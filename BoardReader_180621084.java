//package gomuku;


import java.awt.Color;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 *Board class to define look around methods (row, coloumn, left and right diagonal
 * ?and legal moves
 * Move (int row, int column)
 * 
 * ????>>>>help how to fix issues with variable types, array, Color, int??? grrr try with String and parseing??
 */
public class BoardReader_180621084 {
	Color [][] boardLocal;
	
	Color nextPlayer;
	Color prevPlayer;
	Color winner;
	String lastMove;

	/**
	 * Board constructor
	 */
	public BoardReader_180621084() {
		
		
		
		//?? here insert an instance of copyboard
		//board = MyPlayer.copyBoard();
		
		//this.board = new Color [8][8];
		
		// fill char[][] with empty spaces
		/*for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				this.board[i][j] = null;
			}
		}
		this.winner = null;
	}
	*/
	/**
	 * Copy constructor for Board
	 * @param  other board to copy
	 */
	/*public void Board(Board other) {
		
		this.board = new Color[8][8];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				this.board[i][j] = other.board[i][j];
			}
		} */
		//this.nextPlayer = other.nextPlayer;
		//this.prevPlayer = other.prevPlayer;
	}

	/**
	 * Get set of empty spots on board
	 * 
	 * @return empties set of empty locations
	 */
	Set<String> getEmpties() {
		Set<String> empties = new HashSet<String>();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (boardLocal[i][j] == null) {
					empties.add(i+ " "+ j);
					//??>>>Set int can't do, only objects, Integer will take only one int, not (i, j)
					//don't know how to do this, solved it with String and parse-ing
				}

			}
		}
		return empties;
	}

	/**
	 * Place a move on the board
	 * @param  p    player's Color representation
	 * @param  move location
	 * @param  out  if true, the move gets printed to System.out
	 * @return move
	 */
	String placeMove(Color p, String move, boolean out) {
		int[] ij = parseMove(move);
		boardLocal[ij[0]][ij[1]] = p;
		if (out)
			System.out.println(move);
		prevPlayer = p;
		// sets next and prev player
		if (p == null)
			nextPlayer = Color.white;
		else
			nextPlayer = Color.black;
		winner = setWinner(p, ij);
		return move;
	}

	/**
	 * Get set of all player's pieces
	 * @param  p player
	 * @return ArrayList of locations
	 */
	ArrayList <String> getPlayerPlaces(Color p) {
		ArrayList<String> places = new ArrayList<String>();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (boardLocal[i][j] == p) {
					places.add(i + " " + j);
				}
			}
		}
		return places;
	}

	/**
	 * Look for empty spots around a location
	 * @param  pos location to look around
	 * @return ArrayList of empty locations
	 */
	ArrayList<String> lookAround(String pos) {
		ArrayList<String> adjacent = new ArrayList<String>();
		int[] coords = parseMove(pos);
		int i = coords[0];
		int j = coords[1];
		String x;
		if (i - 1 >= 0) {
			if (boardLocal[i - 1][j] == null) {
				x = strMove(i - 1, j);
				adjacent.add(x);
			}
			if (j - 1 >= 0) {
				if (boardLocal[i - 1][j - 1] == null) {
					x = strMove(i - 1, j - 1);
					adjacent.add(x);
				}
			}
		}
		if (j + 1 < 8) {
			if (boardLocal[i][j + 1] == null) {
				x = strMove(i, j + 1);
				adjacent.add(x);
			}
			if (i - 1 >= 0) {
				if (boardLocal[i - 1][j + 1] == null) {
					x = strMove(i - 1, j + 1);
					adjacent.add(x);
				}
			}
		}
		if (i + 1 < 8) {
			if (boardLocal[i + 1][j] == null) {
				x = strMove(i + 1, j);
				adjacent.add(x);
			}
			if (j + 1 < 8) {
				if (boardLocal[i + 1][j + 1] == null) {
					x = strMove(i + 1, j - 1);
					adjacent.add(x);
				}
			}
		}
		return adjacent;
	}

	/**
	 * Set the board's winner
	 * @param  p  player
	 * @param  ij location
	 * @return a player  or 'd' if the board is in a draw state (no empty spots)
	 */
	Color setWinner(Color p, int[] ij) {
		if (isRow(p, ij) || isColumn(p, ij) || isLeftToRight(p, ij)
				|| isRightToLeft(p, ij)) {
			return p;
		}
		if (getEmpties().isEmpty()) {
			return Color.blue;
		}
		return null;
	}

	/**
	 * Check row of last move for win
	 * @param  p
	 * @param  ij
	 * @return true if row has win
	 */
	boolean isRow(Color p, int[] ij) {
		String row = this.colArrayToString(boardLocal[ij[0]]);
		if (row.contains(strMatch(p)))
			return true;
		return false;
	}

	/**
	 * Check column of last move for win
	 * @param  p
	 * @param  ij
	 * @return true if column has win
	 */
	boolean isColumn(Color p, int[] ij) {
		String column = "";
		for (int i = 0; i < 8; i++) {
			column += boardLocal[i][ij[1]];
		}
		if (column.contains(strMatch(p)))
			return true;
		return false;
	}

	/**
	 * Check diagonal of last move for win, looking top left to bottom right
	 * @param  p
	 * @param  ij
	 * @return true if diagonal has win
	 */
	boolean isLeftToRight(Color p, int[] ij) {
		int i = ij[0];
		int j = ij[1];
		String match = strMatch(p, 5);
		String diag = "";
		// going up and left
		while (i >= 0 && j >= 0) {
			diag = String.valueOf(boardLocal[i][j]) + diag;
			i--;
			j--;
		}
		i = ij[0] + 1;
		j = ij[1] + 1;
		// going down and right
		while (i < 8 && j < 8) {
			diag += String.valueOf(boardLocal[i][j]);
			i++;
			j++;
		}
		if (diag.contains(match))
			return true;
		return false;
	}

	/**
	 * Check diagonal of last move for win, looking top right to bottom left
	 * @param  p
	 * @param  ij
	 * @return true if diagonal has win
	 */
	boolean isRightToLeft(Color p, int[] ij) {
		int i = ij[0];
		int j = ij[1];
		String match = strMatch(p, 5);
		String diag = "";
		// going up and right
		while (i >= 0 && j < 8) {
			diag = String.valueOf(boardLocal[i][j]) + diag;
			i--;
			j++;
		}
		i = ij[0] + 1;
		j = ij[1] - 1;
		// going down and left
		while (i < 8 && j >= 0) {
			diag += String.valueOf(boardLocal[i][j]);
			i++;
			j--;
		}
		if (diag.contains(match))
			return true;
		return false;
	}

	/**
	 * Calculate number of near win chains
	 * @param  p
	 * @param  away from winning chain
	 * @return sum of all nearWin methods
	 */
	int nearWins(Color p, int away) {
		return nearWinRows(p, away) + nearWinCols(p, away);
	}

	/**
	 * Calculate number of near win chains in rows
	 * @param  p
	 * @param  away
	 * @return count
	 */
	int nearWinRows(Color p, int away) {
		int count = 0;
		int length = 5 - away;
		String match1 = strMatch(p, length);
		String match2 = '.' + match1;
		match1 += '.';
		for (int i = 0; i < 8; i++) {
			String row = this.colArrayToString(boardLocal[i]);
			if (row.contains(match1)) {
				int x = row.indexOf(match1);
				while (x >= 0) {
					count++;
					x = row.indexOf(match1, match1.length() + x);
				}
			}
			if (row.contains(match2)) {
				int x = row.indexOf(match2);
				while (x >= 0) {
					count++;
					x = row.indexOf(match2, match2.length() + x);
				}
			}
		}
		return count;
	}

	/**
	 * Calculate number of near win chains in columns
	 * @param  p
	 * @param  away
	 * @return count
	 */
	int nearWinCols(Color p, int away) {
		int count = 0;
		int length = 5 - away;
		String match1 = strMatch(p, length);
		String match2 = '.' + match1;
		match1 += '.';
		for (int j = 0; j < 8; j++) {
			String column = "";
			for (int i = 0; i < 8; i++) {
				column += boardLocal[i][j];
			}
			if (column.contains(match1)) {
				int x = column.indexOf(match1);
				while (x >= 0) {
					count++;
					x = column.indexOf(match1, match1.length() + x);
				}
			}
			if (column.contains(match2)) {
				int x = column.indexOf(match2);
				while (x >= 0) {
					count++;
					x = column.indexOf(match2, match2.length() + x);
				}
			}
		}

		return count;
	}

	/**
	 * Generate string to match for win and nearWin methods
	 * @param  p
	 * @return match
	 */
	String strMatch(Color p) {
		String match = "";
		for (int i = 0; i < 5; i++) {
			if (p == Color.white) {
				match += "White,";
			}
			else {
				match += "Black,";
			}
		}
		return match;
	}

	/**
	 * Generate string to match for win and nearWin methods
	 * @param  p
	 * @param  length of match string
	 * @return match
	 */
	String strMatch(Color p, int length) {
		String match = "";
		for (int i = 0; i < length; i++) {
			if (p == Color.white) {
					match += "White,";
				}
				else {
					match += "Black,";
				}
		}
		return match;
	}

	/**
	 * Parse move from a String into int[]
	 */
	int[] parseMove(String move) {
		String[] ss = move.split(" ");
		int[] ij = { Integer.parseInt(ss[0]), Integer.parseInt(ss[1]) };
		return ij;
	}

	/**
	 * Create string move from coordinates
	 * @param  i row index
	 * @param  j column index
	 * @return move string
	 */
	String strMove(int i, int j) {
		return i + " " + j;
	}

	/**
	 * Create string move from int[] coordinates
	 * @param  ij coordinates
	 * @return move string
	 */
	String strMove(int[] ij) {
		return ij[0] + " " + ij[1];
	}

	/**
	 * Print a board to see what's happening
	 * @return String representation of board
	 */
	@Override
	public String toString() {
		String str = "";
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				str += boardLocal[i][j] + " ";
			}
			str += "\n";
		}
		return str;
	}
	
	//Turn array of color into String
	public String colArrayToString(Color[] array) {
		
		String output = "";
		
		for (int i = 0; i < array.length; i++) {
			
			if (array[i] == Color.white) {
				output += "White,";
			}
			else if (array[i] == Color.black){
				output += "Black,";
			}
			else if (array[i] == null) {
				output += "Null,";
			}
		
		}
		
		return output;
		
	}
	}


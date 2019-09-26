//package gomuku;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * @author sharl
 * MiniMax class -minimax algorithm with evaulation function and alpha-beta pruning to determine best moves in timely fashion
 * This class is meant to bring together the board look around methods and calculate and return the best posible move it could find. 
 */
public class MiniMax_180621084 {

	/**
	 * Evaluation function
	 * @param  board node to evaluate
	 * @return score of board
	 */
	double evaluate(BoardReader_180621084 b) {
		int oneAway = b.nearWins(b.nextPlayer ,1);
		int twoAway = b.nearWins(b.nextPlayer,2);
		int threeAway = b.nearWins(b.nextPlayer, 3);
		double score = oneAway * 100.0 + twoAway * 5.0 + threeAway * 1.0;
		return score;
	}

	/**
	 * Minimax algorithm with alpha-beta pruning.
	 * @param  depth lookahead
	 * @param  myBest alpha
	 * @param  theirBest beta
	 * @return Object[0] contains (Double) score and Object[1] contains (String) move
	 */
	Object[] mmab(Color[][] board, int d, double myBest, double theirBest) {
		BoardReader_180621084 b = new BoardReader_180621084();
		
		ArrayList<String> moveList;
		Set<String> moves = new HashSet<String>(); // no duplicates collection
		ArrayList<String> places = b.getPlayerPlaces(b.nextPlayer);
		for (int i = 0; i < places.size(); i++) {
			moves.addAll(b.lookAround(places.get(i)));
		}
		moves.retainAll(b.getEmpties()); 
		// make sure that moves is not empty
		// otherwise, pick from list of empty locations
		if (moves.isEmpty())
			moveList = new ArrayList<String>(b.getEmpties());
		else
			moveList = new ArrayList<String>(moves);

		Double bestScore;
		Object[] temp;
		Double tempScore;
		String bestMove = "";

		// evaluate at leaf
		if (d == 0) {
			Object[] x = { evaluate(b), moveList.get(0) };
			return x;
		}
		bestScore = myBest;
		while (moveList.size() > 0) {
			//Board newBoard = new Board(board);
			String newMove = moveList.get(0);
			b.placeMove(b.nextPlayer, newMove, false);
			temp = mmab(board, d - 1, -theirBest, -bestScore);
			tempScore = -(Double) temp[0];
			if (tempScore > bestScore) {
				bestScore = tempScore;
				bestMove = newMove;
			}
			if (bestScore > theirBest) {
				Object[] x = { bestScore, bestMove };
				return x;
			}
			moveList.remove(0);
		}
		Object[] x = { bestScore, bestMove };
		return x;
	}
}
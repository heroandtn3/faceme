/**
 * Copyright 2013 heroandtn3 (@sangnd.info)
 */
/*
This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package control;

import java.util.List;

import model.ChessPosition;
import model.Level;
import model.Match;
import model.Side;

/**
 * @author heroandtn3
 * @date Jan 7, 2013
 */
public class ComputerMinmax implements Computer {

	private Match match;
	private Side mySide; // computer side - max
	private Side oppSide; // opponent side - min
	private MoveGenerator moveGenerator;
	private Evaluator evaluator;
	private ChessPosition[] bestMove;
	/**
	 * 
	 */
	public ComputerMinmax(Match match, Side side) {
		this.match = match;
		mySide = side;
		oppSide = (mySide == Side.BLACK) ? Side.RED : Side.BLACK;
		this.bestMove = null;
		moveGenerator = new MoveGeneratorNormal(match);
		evaluator = new EvaluatorNormal();
	}

	@Override
	public ChessPosition[] getBestMove(Level level) {
		/*List<ChessPosition[]> allMoves = moveGenerator.getMoves(side);
		int x = (int) (Math.random() * allMoves.size());  
		return moveGenerator.getMoves(side).get(x);*/
		minmax(3, mySide);
		return bestMove;
	}
	
	private int minmax(int depth, Side side) {
		int currentScore;
		int bestScore = (side == mySide) ? 
				Integer.MIN_VALUE : Integer.MAX_VALUE;
		
		List<ChessPosition[]> nextMoves = moveGenerator.getMoves(side);
		
		// check
		if (nextMoves.isEmpty() || depth <= 0) { 
			// dat toi do sau hoac het nuoc di
			bestScore = evaluator.evaluate(match.getBoard());
		} else {
			for (ChessPosition[] pos : nextMoves) {
				// try move
				int row1 = pos[0].getRow();
				int col1 = pos[0].getCol();
				int row2 = pos[1].getRow();
				int col2 = pos[1].getCol();
				// backup
				int oldValue = match.getBoard().getTable()[row1][col1];
				int newValue = match.getBoard().getTable()[row2][col2];
				// move
				match.getBoard().getTable()[row1][col1] = 0;
				match.getBoard().getTable()[row2][col2] = oldValue;
				
				if (side == mySide) {
					//find max
					currentScore = minmax(depth - 1, oppSide);
					if (currentScore > bestScore) {
						bestScore = currentScore;
						ChessPosition oldPos = new ChessPosition(row1, col1);
						ChessPosition newPos = new ChessPosition(row2, col2);
						bestMove = new ChessPosition[] {oldPos, newPos};
					}
				} else {
					// find min
					currentScore = minmax(depth - 1, mySide);
					if (currentScore < bestScore) {
						bestScore = currentScore;
						ChessPosition oldPos = new ChessPosition(row1, col1);
						ChessPosition newPos = new ChessPosition(row2, col2);
						bestMove = new ChessPosition[] {oldPos, newPos};
					}
				}
				// undo move
				match.getBoard().getTable()[row1][col1] = oldValue;
				match.getBoard().getTable()[row2][col2] = newValue;
			}
		}
		return bestScore;
	}
}

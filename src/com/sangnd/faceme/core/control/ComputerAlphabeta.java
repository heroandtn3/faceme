/*

Copyright (c) 2013 heroandtn3 (@sangnd.info), khanhoatink4, igisik

This file is part of Faceme.

Faceme is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

Faceme is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.sangnd.faceme.core.control;

import java.util.List;

import com.sangnd.faceme.core.model.ChessPosition;
import com.sangnd.faceme.core.model.Match;
import com.sangnd.faceme.core.model.Side;

/**
 * @author heroandtn3
 * @date Jul 27, 2013
 */
public class ComputerAlphabeta extends BaseComputer {

	private ChessPosition[] bestMove;
	private int count;
	

	/**
	 * 
	 */
	public ComputerAlphabeta(Match match, Side side) {
		super(match, side);
	}

	@Override
	protected ChessPosition[] getBestMove() {
		bestMove = null;
		count = 0;
		alphabeta(Integer.MIN_VALUE + 1, Integer.MAX_VALUE, 4, side);
		System.out.println("So lan goi: " + count);
		//bestMove = new ChessPosition[] {new ChessPosition(0, 0), new ChessPosition(1, 0)};
		return bestMove;
	
	}
	
	private int alphabeta(int alpha, int beta, int depth, Side side) {
		count++;
		if (depth == 0) {
			return -board.value();
		} else {
			int best = Integer.MIN_VALUE + 1;
			
			List<ChessPosition[]> moves = board.getMoves(side);
			for (ChessPosition[] move : moves) {
				if (best >= beta) {
					break;
				}
				if (best > alpha) {
					alpha = best;
				}
				board.move(move[0], move[1]);
				int value = -alphabeta(
						-beta, -alpha, depth - 1, 
						(side == Side.ENERMY) ? Side.FRIEND : Side.ENERMY);
				board.undo(1, false);
				if (value > best) {
					best = value;
					if (depth == 4) {
						bestMove = move;
					}
				}
			}
			return best;
		}
	}

}

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
package com.sangnd.faceme.core.control;

import java.util.List;

import com.sangnd.faceme.core.model.Match;
import com.sangnd.faceme.core.model.Side;

/**
 * @author heroandtn3
 * @date Jan 7, 2013
 */
public class ComputerMinmax extends BaseComputer {
	

	private ChessMove bestMove;
	private int count;

	/**
	 * 
	 */
	public ComputerMinmax(Match match, Side side) {
		super(match, side);
	}

	@Override
	protected ChessMove getBestMove() {
		bestMove = null;
		count = 0;
		minimax(3, side);
		System.out.println("So lan goi: " + count);
		return bestMove;
	}
	
	private int minimax(int depth, Side side) {
		count++;
		if (depth == 0) {
			return board.value();
		} else {
			int best = Integer.MIN_VALUE;
			List<ChessMove> moves = board.getMoves(side);
			System.out.println("So nuoc di: " + moves.size());
			for (ChessMove move : moves) {
				board.move(move.getOldPos(), move.getNewPos());
				int value = -minimax(depth - 1, (side == Side.ENERMY) ? Side.FRIEND : Side.ENERMY);
				board.undo(1, false);
				if (value > best) {
					System.out.println("Value: " + value + ", Best: " + best);
					best = value;
					if (depth == 3) {
						bestMove = move;
					}
				}
			}
			return best;
		}
	}

}

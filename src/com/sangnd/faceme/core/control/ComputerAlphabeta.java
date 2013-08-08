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

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.sangnd.faceme.core.model.Match;
import com.sangnd.faceme.core.model.Side;

/**
 * @author heroandtn3
 * @date Jul 27, 2013
 */
public class ComputerAlphabeta extends BaseComputer {

	private ChessMove bestMove;
	private int count;
	

	/**
	 * 
	 */
	public ComputerAlphabeta(Match match, Side side) {
		super(match, side);
	}

	@Override
	protected ChessMove getBestMove() {
		bestMove = null;
		count = 0;
		long begin = new Date().getTime();
		alphabeta(Integer.MIN_VALUE + 1, Integer.MAX_VALUE, 4, side);
		long end = new Date().getTime();
		System.out.println("Done in: " + (end - begin));
		System.out.println("So lan goi: " + count);
		
		return bestMove;
	
	}
	
	private int alphabeta(int alpha, int beta, int depth, Side side) {
		count++;
		if (depth == 0) {
			return -board.value();
		} else {
			List<ChessMove> moves = getSortedMove(side);
			for (ChessMove move : moves) {
				int pie = board.getPiece(move.getNewPos()); 
				if (pie == -1) {
					return 100000 + depth;
				}
				
				board.move(move.getOldPos(), move.getNewPos());
				int value = -alphabeta(
						-beta, -alpha, depth - 1, 
						(side == Side.ENERMY) ? Side.FRIEND : Side.ENERMY);
				board.undo(1, false);
				if (value > alpha) {
					alpha = value;
					if (depth == 4) {
						bestMove = move;
					}
				}
				
				if (alpha >= beta) {
					break;
				}
			}
			return alpha;
		}
	}
	
	private List<ChessMove> getSortedMove(final Side side) {
		List<ChessMove> moves = board.getMoves(side);
		for (ChessMove move : moves) {
			move.setScore(value(board.getPiece(move.getNewPos())));
		}
		Collections.sort(moves, new Comparator<ChessMove>() {

			@Override
			public int compare(ChessMove o1, ChessMove o2) {
				return o2.getScore() - o1.getScore();
			}
		});
		return moves;
	}
	
	private int value(int code) {
		if (code < 0) {
			code = -code;
		}
		return EvaluatorNormal.VALUE[code];
	}

}

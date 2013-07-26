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

import com.sangnd.faceme.core.model.Board;
import com.sangnd.faceme.core.model.ChessPosition;
import com.sangnd.faceme.core.model.Level;
import com.sangnd.faceme.core.model.Match;
import com.sangnd.faceme.core.model.Side;
import com.sangnd.faceme.event.SelectChessEvent;
import com.sangnd.faceme.event.SelectChessListener;

/**
 * @author heroandtn3
 * @date Jan 7, 2013
 */
public class ComputerMinmax implements Computer {
	
	private Side side;
	private SelectChessListener listener;
	private Board board;
	private ChessPosition[] bestMove;
	private int count;

	/**
	 * 
	 */
	public ComputerMinmax(Match match, Side side) {
		this.board = match.getBoard();
		this.side = side;
	}

	private ChessPosition[] getBestMove(Level level) {
		bestMove = null;
		count = 0;
		minimax(3, side);
		System.out.println("So lan goi: " + count);
		//bestMove = new ChessPosition[] {new ChessPosition(0, 0), new ChessPosition(1, 0)};
		return bestMove;
	}
	
	private int minimax(int depth, Side side) {
		count++;
		if (depth == 0) {
			return board.value();
		} else {
			int best = Integer.MIN_VALUE;
			List<ChessPosition[]> moves = board.getMoves(side);
			System.out.println("So nuoc di: " + moves.size());
			for (ChessPosition[] move : moves) {
				board.move(move[0], move[1]);
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

	@Override
	public Side getSide() {
		return this.side;
	}

	@Override
	public void move() {
		ChessPosition[] mv = getBestMove(null);
		if (mv == null) throw new NullPointerException("Nuoc di tra ve la null");
		System.out.println(mv[0].getRow() + " - " + mv[0].getCol());
		System.out.println(mv[1].getRow() + " - " + mv[1].getCol());
		listener.onSelect(new SelectChessEvent(mv[0]));
		listener.onSelect(new SelectChessEvent(mv[1]));
	}

	@Override
	public void addSelectChessListener(SelectChessListener listener) {
		this.listener = listener;
	}

}

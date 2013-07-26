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

	private Match match;
	private Side side;
	private MoveGenerator moveGenerator;
	private SelectChessListener listener;
	/**
	 * 
	 */
	public ComputerMinmax(Match match, Side side) {
		this.match = match;
		this.side = side;
		moveGenerator = new MoveGeneratorNormal();
	}

	private ChessPosition[] getBestMove(Level level) {
		List<ChessPosition[]> allMoves = moveGenerator.getMoves(match.getBoard(), side);
		int x = (int) (Math.random() * allMoves.size());  
		return moveGenerator.getMoves(match.getBoard(), side).get(x);
	}

	@Override
	public Side getSide() {
		return this.side;
	}


	@Override
	public void move() {
		ChessPosition[] mv = getBestMove(null);
		listener.onSelect(new SelectChessEvent(mv[0]));
		listener.onSelect(new SelectChessEvent(mv[1]));
	}

	@Override
	public void addSelectChessListener(SelectChessListener listener) {
		this.listener = listener;
	}


}

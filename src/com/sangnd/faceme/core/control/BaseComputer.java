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

import com.sangnd.faceme.core.model.Board;
import com.sangnd.faceme.core.model.ChessPosition;
import com.sangnd.faceme.core.model.Level;
import com.sangnd.faceme.core.model.Match;
import com.sangnd.faceme.core.model.Side;
import com.sangnd.faceme.event.SelectChessEvent;
import com.sangnd.faceme.event.SelectChessListener;

/**
 * @author heroandtn3
 * @date Jul 31, 2013
 */
public abstract class BaseComputer implements Computer {
	
	private SelectChessListener listener;
	protected Board board;
	protected Side side;
	protected Level level;

	/**
	 * 
	 */
	public BaseComputer(Match match, Side side) {
		this.side = side;
		this.board = match.getBoard();
	}
	
	public Level getLevel() {
		return level;
	}
	
	public void setLevel(Level level) {
		this.level = level;
	}

	@Override
	public void addSelectChessListener(SelectChessListener listener) {
		this.listener = listener;
		
	}

	@Override
	public Side getSide() {
		return side;
	}

	@Override
	public void move() {
		ChessPosition[] mv = getBestMove();
		if (mv == null) throw new NullPointerException("Nuoc di tra ve la null");
		System.out.println(mv[0].getRow() + " - " + mv[0].getCol());
		System.out.println(mv[1].getRow() + " - " + mv[1].getCol());
		listener.onSelect(new SelectChessEvent(mv[0]));
		listener.onSelect(new SelectChessEvent(mv[1]));
		
	}

	protected abstract ChessPosition[] getBestMove();

}

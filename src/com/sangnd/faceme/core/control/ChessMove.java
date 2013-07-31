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

import com.sangnd.faceme.core.model.ChessPosition;

/**
 * @author heroandtn3
 * @date Jul 31, 2013
 */
public class ChessMove {

	private ChessPosition newPos;
	private ChessPosition oldPos;
	private int score;

	/**
	 * 
	 */
	public ChessMove(ChessPosition oldPos, ChessPosition newPos) {
		this.oldPos = oldPos;
		this.newPos = newPos;
	}
	
	public ChessPosition getNewPos() {
		return newPos;
	}
	
	public ChessPosition getOldPos() {
		return oldPos;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

}

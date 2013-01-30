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

import model.ChessPosition;
import model.Level;
import model.Match;

/**
 * @author heroandtn3
 * @date Jan 7, 2013
 */
public class ComputerMinmax implements Computer {

	private Match match;
	/**
	 * 
	 */
	public ComputerMinmax(Match match) {
		this.match = match;
	}

	@Override
	public ChessPosition[] getBestMove(Level level) {
		// TODO Auto-generated method stub
		return null;
	}

}

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
package control;

import java.util.ArrayList;
import java.util.List;

import model.ChessPosition;
import model.GameState;
import model.Match;
import model.Side;

/**
 * @author heroandtn3
 * @date Apr 9, 2013
 */
public class CheckFinishMatch implements CheckerFinish {

	/**
	 * 
	 */
	public CheckFinishMatch() {
		super();
	}

	@Override
	public GameState getState(Match match) {
		List<ChessPosition> allPosCanMove = getAllPosCanMove(match, match.getCurrentSide());
		if (allPosCanMove.isEmpty()) {
			return ((match.getCurrentSide() == Side.BLACK) ? 
					GameState.RED_WON : GameState.BLACK_WON); 
		}
		/*if (match.isCheckmate()) {
			if (CanNotStopCheckMate(match)) {
				return ((match.getCurrentSide() == Side.BLACK) ? 
						GameState.RED_WON : GameState.BLACK_WON); 
			}
		}*/
		
		return GameState.PLAYING;
	}
	
	private boolean CanNotStopCheckMate(Match match) {
		int tuong = (match.getCurrentSide() == Side.BLACK) ? -7 : 7;
		int[][] table = match.getBoard().getTable();
		for (int row = 0; row < 10; row++) {
			for (int col = 0; col < 9; col++) {
				if (table[row][col] == tuong) {
					ChessPosition tuongPos = new ChessPosition(row, col);
					if (match.getChess()[7].getPosCanMove(tuongPos).isEmpty()) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	private List<ChessPosition> getAllPosCanMove(Match match, Side player) {
		List<ChessPosition> result = new ArrayList<ChessPosition>();
		int[][] table = match.getBoard().getTable();
		for (int row = 0; row < 10; row++) {
			for (int col = 0; col < 9; col++) {
				int value = table[row][col];
				if (isChessOf(player, value)) {
					ChessPosition current = new ChessPosition(row, col);
					result.addAll(match.getChess()[Math.abs(value)].getPosCanMove(current));
				}
			}
		}
		return result;
	}
	
	private boolean isChessOf(Side player, int value) {
		if ((player == Side.RED && value > 0) ||
			player == Side.BLACK && value < 0) {
			return true;
		} else {
			return false;
		}
	}

}

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
	private MoveGenerator moveGenerator = null;

	/**
	 * 
	 */
	public CheckFinishMatch() {
		super();
	}

	@Override
	public GameState getState(Match match) {
		if (moveGenerator == null) {
			moveGenerator = new MoveGeneratorNormal(match);
		}
		Side oppSide = (match.getCurrentSide() == Side.BLACK) ?
				Side.RED : Side.BLACK;
		
		// lay toan bo cac nuoc di ma `oppSide` co the di
		List<ChessPosition[]> allPosCanMove = moveGenerator.getMoves(oppSide);
		
		// neu `oppSide` khong co nuoc di nao thi `CurrentSide` thang
		if (allPosCanMove.isEmpty()) {
			return ((match.getCurrentSide() == Side.BLACK) ? 
					GameState.BLACK_WON : GameState.RED_WON);
		}
		
			if (CanNotStopCheckMate(match, allPosCanMove)) {
				return ((match.getCurrentSide() == Side.BLACK) ? 
						GameState.BLACK_WON : GameState.RED_WON);
			}
		

		return GameState.PLAYING;
	}

	private boolean CanNotStopCheckMate(Match match,
			List<ChessPosition[]> allPosCanMove) {
		int[][] cloneTable = match.getBoard().getTable();
		//int[][] cloneTable = new int[10][9];
		// copy to cloneTable
		/*for (int row = 0; row < 10; row++) {
			for (int col = 0; col < 9; col++) {
				cloneTable[row][col] = oriTable[row][col];
			}
		}*/
		
		for (ChessPosition[] move : allPosCanMove) {
			int row1 = move[0].getRow();
			int col1 = move[0].getCol();
			int row2 = move[1].getRow();
			int col2 = move[1].getCol();
			
			// make a move
			int tmp = cloneTable[row2][col2]; // backup
			cloneTable[row2][col2] = cloneTable[row1][col1];
			cloneTable[row1][col1] = 0;
			// if still checkmate,  return true
			if (match.CheckingMate()) {
				return true;
			}
			// roll back
			cloneTable[row1][col1] = cloneTable[row2][col2];
			cloneTable[row2][col2] = tmp;
		}
		return false;
	}

}

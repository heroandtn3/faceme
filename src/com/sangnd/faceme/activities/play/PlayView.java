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
package com.sangnd.faceme.activities.play;

import java.util.List;

import javax.swing.JButton;

import model.Board;
import model.ChessPosition;
import model.GameState;

import com.sangnd.faceme.event.HasSelectChessListener;
import com.sangnd.mvp.activityplace.View;

/**
 * @author heroandtn3
 * @date Jul 23, 2013
 */
public interface PlayView extends View {
	
	JButton getHomeButton();
	
	HasSelectChessListener getBoardView();

	void renderBoard(Board board);
	
	void renderMoving(ChessPosition oldPos, ChessPosition newPos);
	
	void renderChessSelect(ChessPosition selectedPos, List<ChessPosition> posCanMove);

	void renderWarnKing(boolean warnKing);

	void renderMatchFinish(GameState state);
}

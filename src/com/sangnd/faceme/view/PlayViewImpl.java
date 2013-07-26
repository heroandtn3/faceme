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
package com.sangnd.faceme.view;

import java.awt.Container;
import java.util.List;

import javax.swing.JButton;


import com.sangnd.faceme.activities.play.PlayView;
import com.sangnd.faceme.core.model.Board;
import com.sangnd.faceme.core.model.ChessPosition;
import com.sangnd.faceme.core.model.GameState;
import com.sangnd.faceme.event.HasSelectChessListener;

/**
 * @author heroandtn3
 * @date Jul 23, 2013
 */
public class PlayViewImpl implements PlayView {
	
	private BoardView boardView;

	public PlayViewImpl() {
		boardView = new BoardViewImpl();
	}

	@Override
	public Container asPanel() {
		return boardView.asPanel();
	}

	@Override
	public void clearContent() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void renderBoard(Board board) {
		boardView.renderBoard(board);
	}

	@Override
	public void renderMoving(ChessPosition oldPos, ChessPosition newPos) {
		boardView.renderMoving(oldPos, newPos);
	}

	@Override
	public void renderChessSelect(ChessPosition selectedPos,
			List<ChessPosition> posCanMove) {
		boardView.renderChessSelect(selectedPos, posCanMove);
	}

	@Override
	public JButton getHomeButton() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HasSelectChessListener getBoardView() {
		return (HasSelectChessListener) boardView;
	}

	@Override
	public void renderWarnKing(boolean warnKing) {
		boardView.renderWarnKing(warnKing);
	}

	@Override
	public void renderMatchFinish(GameState state) {
		boardView.renderMatchFinish(state);
	}

}

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

import com.sangnd.faceme.activities.ClientFactory;
import com.sangnd.faceme.core.model.ChessPosition;
import com.sangnd.faceme.core.model.GameMode;
import com.sangnd.faceme.core.model.Match;
import com.sangnd.faceme.event.MoveCompleteEvent;
import com.sangnd.faceme.event.MoveCompleteListener;
import com.sangnd.faceme.event.SelectChessEvent;
import com.sangnd.faceme.event.SelectChessListener;
import com.sangnd.mvp.activityplace.AcceptOnePanel;
import com.sangnd.mvp.activityplace.Activity;

/**
 * @author heroandtn3
 * @date Jul 23, 2013
 */
public class PlayActivity extends Activity {

	private ClientFactory clientFactory;
	private Match match;

	public PlayActivity(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
	}

	@Override
	public void start(AcceptOnePanel container) {
		final PlayView view = clientFactory.getPlayView();

		match = new Match();
		match.setGameMode(GameMode.PLAY_WITH_COMPUTER);

		view.renderBoard(match.getBoard());

		view.getBoardView().addSelectChessListener(new SelectChessListener() {

			@Override
			public void onSelect(SelectChessEvent event) {
				ChessPosition pos = (ChessPosition) event.getSource();
				doChessSelectEvent(pos, view);
			}
		});

		view.getMoveChessPanel().addMoveCompleteListener(
				new MoveCompleteListener() {

					@Override
					public void onComplete(MoveCompleteEvent event) {
						if (match.getGameMode() == GameMode.PLAY_WITH_COMPUTER) {
							if (match.getCurrentSide() == match.getComputer()
									.getSide()) {
								match.getComputer().move();
							}
						}
					}
				});

		match.getComputer().addSelectChessListener(new SelectChessListener() {

			@Override
			public void onSelect(SelectChessEvent event) {
				ChessPosition pos = (ChessPosition) event.getSource();
				doChessSelectEvent(pos, view);
			}
		});

		container.setPanel(view.asPanel());
	}

	private void doChessSelectEvent(ChessPosition pos, PlayView view) {
		match.setPos(pos);

		// This must be before moving chess!!!
		view.renderWarnKing(match.isWarnKing());
		view.renderMatchFinish(match.getState());

		if (match.getNewPos() != null) {
			view.renderMoving(match.getOldPos(), match.getNewPos());
		} else {
			view.renderChessSelect(match.getOldPos(), match.getPosCanMove());
		}
	}

}

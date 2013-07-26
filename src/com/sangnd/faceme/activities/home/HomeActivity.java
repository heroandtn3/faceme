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
package com.sangnd.faceme.activities.home;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.sangnd.faceme.activities.ClientFactory;
import com.sangnd.faceme.activities.play.PlayPlace;
import com.sangnd.mvp.activityplace.AcceptOnePanel;
import com.sangnd.mvp.activityplace.Activity;

/**
 * @author heroandtn3
 * @date Jul 23, 2013
 */
public class HomeActivity extends Activity {

	private ClientFactory clientFactory;

	/**
	 * 
	 */
	public HomeActivity(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
	}

	@Override
	public void start(AcceptOnePanel container) {
		final HomeView view = clientFactory.getHomeView();
		
		view.getPlayButton().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				clientFactory.getPlaceController().goTo(new PlayPlace());
			}
		});
		
		container.setPanel(view.asPanel());
	}

}

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
package com.sangnd.faceme.activities;

import com.sangnd.faceme.activities.home.HomeView;
import com.sangnd.faceme.activities.play.PlayView;
import com.sangnd.faceme.view.HomeViewImpl;
import com.sangnd.faceme.view.PlayViewImpl;
import com.sangnd.mvp.activityplace.ActivityMapper;
import com.sangnd.mvp.activityplace.PlaceController;

/**
 * @author heroandtn3
 * @date Jul 23, 2013
 */
public class ClientFactoryImpl implements ClientFactory {

	private PlaceController placeController;
	private ActivityMapper activityMapper;
	private PlayView playView;
	private HomeView homeView;

	/**
	 * 
	 */
	public ClientFactoryImpl() {
		activityMapper = new AppActivityMapper(this);
	}

	@Override
	public PlaceController getPlaceController() {
		if (placeController == null) {
			placeController = new PlaceController(activityMapper);
		}
		return placeController;
	}

	@Override
	public HomeView getHomeView() {
		if (homeView == null) {
			homeView = new HomeViewImpl();
			homeView.clearContent();
		}
		return homeView;
	}

	@Override
	public PlayView getPlayView() {
		if (playView == null) {
			playView = new PlayViewImpl();
			playView.clearContent();
		}
		return playView;
	}

}

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

import com.sangnd.faceme.activities.home.HomeActivity;
import com.sangnd.faceme.activities.home.HomePlace;
import com.sangnd.faceme.activities.play.PlayActivity;
import com.sangnd.faceme.activities.play.PlayPlace;
import com.sangnd.mvp.activityplace.Activity;
import com.sangnd.mvp.activityplace.ActivityMapper;
import com.sangnd.mvp.activityplace.Place;

/**
 * @author heroandtn3
 * @date Jul 23, 2013
 */
public class AppActivityMapper implements ActivityMapper {

	private ClientFactory clientFactory;

	/**
	 * 
	 */
	public AppActivityMapper(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
	}

	@Override
	public Activity getActivity(Place place) {
		if (place instanceof HomePlace) {
			return new HomeActivity(clientFactory);
		}
		
		if (place instanceof PlayPlace) {
			return new PlayActivity(clientFactory);
		}
		return null;
	}

}

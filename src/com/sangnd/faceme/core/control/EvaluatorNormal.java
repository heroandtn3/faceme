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

import com.sangnd.faceme.core.model.Board;

/**
 * @author heroandtn3
 * @date Feb 1, 2013
 */
public class EvaluatorNormal implements Evaluator {

	/**
	 * 
	 */
	public EvaluatorNormal() {
	}

	@Override
	public int evaluate(Board board) {
		int[][] table = board.getTable();
		int value = 0;
		for (int row = 0; row < 10; row++) {
			for (int col = 0; col < 9; col++) {
				value += value(table[row][col]);
			}
		}
		//if (value < 0) value = -value;
		System.out.println("Valuate return: " + value);
		return value;
	}
	
	private int value(int code) {
		if (code < 0) {
			switch (code) {
				case -1:
					// tuong
					return -6000;
				case -2:
					// sy
					return -120;
				case -3:
					// tuong
					return -120;
				case -4:
					// xe
					return -600;
				case -5:
					// phao
					return -285;
				case -6:
					// ma
					return -270;
				case -7:
					// tot
					return -30;
			}
		} else if (code > 0) {
			switch (code) {
				case 1:
					// tuong
					return 6000;
				case 2:
					// sy
					return 120;
				case 3:
					// tuong
					return 120;
				case 4:
					// xe
					return 600;
				case 5:
					// phao
					return 285;
				case 6:
					// ma
					return 270;
				case 7:
					// tot
					return 30;
			}
		}
		return 0;
	}
}

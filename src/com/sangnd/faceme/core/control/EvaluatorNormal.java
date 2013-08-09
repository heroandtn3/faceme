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
	
	public final static int[] BASE_VALUE = new int[] {0, 9000, 200, 250, 1000, 500, 500, 100};
	
	private final static int POS_VALUE[][][] = {
			{ 	{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },  //zero 
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 } 
			},
			
			{ 	{ 0, 0, 0,  0,  0,  0, 0, 0, 0 },  //king 
				{ 0, 0, 0,  0,  0,  0, 0, 0, 0 },
				{ 0, 0, 0,  0,  0,  0, 0, 0, 0 },
				{ 0, 0, 0,  0,  0,  0, 0, 0, 0 },
				{ 0, 0, 0,  0,  0,  0, 0, 0, 0 },
				{ 0, 0, 0,  0,  0,  0, 0, 0, 0 },
				{ 0, 0, 0,  0,  0,  0, 0, 0, 0 },
				{ 0, 0, 0, -5, -4, -5, 0, 0, 0 },
				{ 0, 0, 0, -4, -3, -4, 0, 0, 0 },
				{ 0, 0, 0, -3,  2, -3, 0, 0, 0 } 
			},
			
			{ 	{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },  //advisor
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0,-1, 0,-1, 0, 0, 0 },
				{ 0, 0, 0, 0, 3, 0, 0, 0, 0 },
				{ 0, 0, 0, 1, 0, 1, 0, 0, 0 } 
			},
			
			{ 	{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },  //bishop 
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0,-1, 0, 0, 0,-1, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{-2, 0, 0, 0, 3, 0, 0, 0,-2 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 1, 0, 0, 0, 1, 0, 0 } 
			},
			
			{ 	{ 14, 14, 12, 18, 16, 18, 12, 14, 14 },  //rook
				{ 16, 20, 18, 24, 26, 24, 18, 20, 16 },
				{ 12, 12, 12, 18, 18, 18, 12, 12, 12 },
				{ 12, 18, 16, 22, 22, 22, 16, 18, 12 },
				{ 12, 14, 12, 18, 18, 18, 12, 14, 12 },
				{ 12, 16, 14, 20, 20, 20, 14, 16, 12 },
				{  6, 10,  8, 14, 14, 14,  8, 10, 06 },
				{  4,  8,  6, 14, 12, 14,  6,  8,  4 },
				{  8,  4,  8, 16,  8, 16,  8,  4,  8 },
				{ -2, 10,  6, 14, 12, 14,  6, 10, -2 }, 
			},
			
			{ 	{ 6, 4, 0, -10, -12, -10,  0, 4,  6 },  //cannon
				{ 2, 2, 0,  -4, -14,  -4,  0, 2,  2 },
				{ 2, 2, 0, -10,  -8, -10,  0, 2,  2 },
				{ 0, 0,-2,   4,  10,   4, -2, 0,  0 },
				{ 0, 0, 0,   2,   8,   2,  0, 0,  0 },
				{-2, 0, 4,   2,   6,   2,  4, 0, -2 },
				{ 0, 0, 0,   2,   4,   2,  0, 0,  0 },
				{ 4, 0, 8,   6,  10,   6,  8, 0,  4 },
				{ 0, 2, 4,   6,   6,   6,  4, 2,  0 },
				{ 0, 0, 2,   6,   6,   6,  2, 0,  0 }, 
			},
			
			{ 	{  4,  8, 16, 12,  4, 12, 16,  8,  4 },  //knight
				{  4, 10, 28, 16,  8, 16, 28, 10,  4 },
				{ 12, 14, 16, 20, 18, 20, 16, 14, 12 },
				{  8, 24, 18, 24, 20, 24, 18, 24,  8 },
				{  6, 16, 14, 18, 16, 18, 14, 16,  6 },
				{  4, 12, 16, 14, 12, 14, 16, 12,  4 },
				{  2,  6,  8,  6, 10,  6,  8,  6,  2 },
				{  4,  2,  8,  8,  4,  8,  8,  2,  4 },
				{  0,  2,  4,  4, -2,  4,  4,  2,  0 },
				{  0, -4,  0,  0,  0,  0,  0, -4,  0 }, 
			},
			{ 	{  0,  3,  6,  9,  12,   9,   6,   3,  0 },//pawn
				{ 18, 36, 56, 80, 120,  80,  56,  36, 18 },
				{ 14, 26, 42, 60,  80,  60,  42,  26, 14 },
				{ 10, 20, 30, 34,  40,  34,  30,  20, 10 },
				{  6, 12, 18, 18,  20,  18,  18,  12,  6 },
				{  2,  0,  8,  0,   8,   0,   8,   0,  2 },
				{  0,  0, -2,  0,   4,   0,  -2,   0,  0 },
				{  0,  0,  0,  0,   0,   0,   0,   0,  0 },
				{  0,  0,  0,  0,   0,   0,   0,   0,  0 },
				{  0,  0,  0,  0,   0,   0,   0,   0,  0 }, 
			}
	  };

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
				value += value(table[row][col], row, col);
			}
		}
		//if (value < 0) value = -value;
		//System.out.println("Valuate return: " + value);
		return value;
	}
	
	public static int value(int code, int row, int col) {
		int value = 0;
		if (code < 0) {
			row = 9 - row;
			col = 8 - col;
			value -= BASE_VALUE[-code];
			value -= POS_VALUE[-code][row][col];
		} else {
			value += BASE_VALUE[code];
			value += POS_VALUE[code][row][col];
		}
		return value;
	}
}

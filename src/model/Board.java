/**
 * Copyright 2013 heroandtn3 (@sangnd.info)
 */
/*
 This program is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package model;

/**
 * @author heroandtn3
 * @date Jan 7, 2013
 */
public class Board {

	private int[][] table;
	private int[][] cloneTable;

	/**
	 * 
	 */
	public Board() {
		table = new int[10][9];
		cloneTable = new int[10][9];
	}
	
	public Board(int[][] table) {
		this.table = table;
		cloneTable = new int[10][9];
		makeCloneTable();
	}
	
	/**
	 * Clone a board from `oriBoard`
	 * @param oriBoard
	 */
	public Board(Board oriBoard) {
		// copy table contents
		this.table = new int[10][9];
		for (int row = 0; row < 10; row++) {
			for (int col = 0; col < 9; col++) {
				this.table[row][col] = oriBoard.getTable()[row][col];
			}
		}
		cloneTable = new int[10][9];
		makeCloneTable();
	}
	
	public void makeCloneTable() {
		for (int row = 0; row < 10; row++) {
			for (int col = 0; col < 9; col++) {
				cloneTable[row][col] = table[row][col];
			}
		}
	}

	public int[][] getTable() {
		return table;
	}

	public void setTable(int[][] table) {
		this.table = table;
	}

	public int[][] getCloneTable() {
		return cloneTable;
	}
}

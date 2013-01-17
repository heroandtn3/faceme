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

import java.util.Observable;

/**
 * @author heroandtn3
 * @date Jan 7, 2013
 */
public class Match extends Observable {

	private Level level;
	private GameState state;
	private Board board;
	private Chess[] chess;
	private Player playerBlack, playerRed;

	/**
	 * 
	 */
	public Match() {

	}
	
	/**
	 * Ham thuc hien di chuyen quan co tu vi tri cu sang vi tri moi
	 * @param oldPos
	 * @param newPos
	 */
	public void move(ChessPosition oldPos, ChessPosition newPos) {
		int tmp = board.getTable()[oldPos.getRow()][oldPos.getCol()];
		board.getTable()[newPos.getRow()][newPos.getCol()] = tmp;
		
		// danh dau la da thay doi va gui yeu cau update den cac observers
		setChanged();
		notifyObservers();
	}

	/* get, set -------------------------------------------------------------*/
	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public GameState getState() {
		return state;
	}

	public void setState(GameState state) {
		this.state = state;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public Chess[] getChess() {
		return chess;
	}

	public void setChess(Chess[] chess) {
		this.chess = chess;
	}

	public Player getPlayerBlack() {
		return playerBlack;
	}

	public void setPlayerBlack(Player playerBlack) {
		this.playerBlack = playerBlack;
	}

	public Player getPlayerRed() {
		return playerRed;
	}

	public void setPlayerRed(Player playerRed) {
		this.playerRed = playerRed;
	}

}

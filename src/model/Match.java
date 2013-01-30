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

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import model.chess.Advisor;
import model.chess.Bishop;
import model.chess.Cannon;
import model.chess.Chess;
import model.chess.King;
import model.chess.Knight;
import model.chess.Pawn;
import model.chess.Rook;

/**
 * @author heroandtn3
 * @date Jan 7, 2013
 */
public class Match extends Observable {

	// game attributes
	private Level level;
	private GameState state;
	private Board board;
	private Chess[] chess;
	private Player playerBlack, playerRed;
	
	private int[][] table = {
			{6,  4,  3,  2,  7,  2,  3,  4,  6},
			{0,  0,  0,  0,  0,  0,  0,  0,  0},
			{0,  5,  0,  0,  0,  0,  0,  5,  0},
			{1,  0,  1,  0,  1,  0,  1,  0,  1},
			{0,  0,  0,  0,  0,  0,  0,  0,  0},
			{0,  0,  0,  0,  0,  0,  0,  0,  0},
			{-1, 0, -1,  0, -1,  0, -1,  0, -1},
			{0, -5,  0,  0,  0,  0,  0, -5,  0},
			{0,  0,  0,  0,  0,  0,  0,  0,  0},
			{-6,-4, -3, -2, -7, -2, -3, -4, -6} 
		};
	
	// game state attribues
	private List<ChessPosition> posCanMove;
	private int[] posSelected;

	/**
	 * 
	 */
	public Match() {
		board = new Board(table);
		initChess();
		posSelected = null;
		posCanMove = new ArrayList<ChessPosition>();
		state = GameState.BLACK_THINKING;
	}
	
	private void initChess() {
		chess = new Chess[8];
		chess[0] = null;
		chess[1] = new Pawn(board);
		chess[2] = new Advisor(board);
		chess[3] = new Bishop(board);
		chess[4] = new Knight(board);
		chess[5] = new Cannon(board);
		chess[6] = new Rook(board);
		chess[7] = new King(board);
		
		
	}
	
	/**
	 * Ham thuc hien di chuyen quan co tu vi tri cu sang vi tri moi
	 * @param oldPos
	 * @param newPos
	 */
	public void move(ChessPosition oldPos, ChessPosition newPos) {
		int[][] mt = board.getTable();
		
		int tmp = mt[oldPos.getRow()][oldPos.getCol()];
		mt[oldPos.getRow()][oldPos.getCol()] = 0; // vi tri cu chuyen ve 0
		mt[newPos.getRow()][newPos.getCol()] = tmp;
		// xoa vet
		this.posSelected = null;
		
		// clear posCanMove
		this.posCanMove.clear();
		
		// switch player
		state = (state == GameState.BLACK_THINKING) ? 
				GameState.RED_THINKING : GameState.BLACK_THINKING;
		
		// danh dau la da thay doi va gui yeu cau update den cac observers
		setChanged();
		notifyObservers();
	}
	
	private void updatePosCanMove() {
		if (posSelected == null) {
			posCanMove.clear();
		} else {
			int value = Math.abs(table[posSelected[0]][posSelected[1]]);
			ChessPosition currentPos = 
					new ChessPosition(posSelected[0], posSelected[1]);
			posCanMove = chess[value].getPosCanMove(currentPos);
		}
		// thong bao cho view update
		setChanged();
		notifyObservers();
	}
	
	private boolean isChooseForMove(int[] pos) {
		int value = table[pos[0]][pos[1]];
		if ((state == GameState.RED_THINKING && value > 0) ||
			(state == GameState.BLACK_THINKING && value < 0)) {
			return true;
		} else {
			return false;
		}
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

	public List<ChessPosition> getPosCanMove() {
		return posCanMove;
	}

	public void setPosCanMove(List<ChessPosition> posCanMove) {
		this.posCanMove = posCanMove;
	}

	public int[] getPosSelected() {
		return posSelected;
	}

	public void setPosSelected(int[] pos) {
		if (isChooseForMove(pos)) { // chon quan de di chuyen
			// loai bo pos khong hop le
			if (table[pos[0]][pos[1]] == 0) return;
			
			// neu hop le thi danh dau pos da chon
			this.posSelected = pos;
			
			// update pos can move
			updatePosCanMove();
		} else if (this.posSelected != null){ // neu da co pos duoc chon
			// thi di chuyen quan den vi tri moi
			
			// vi tri di chuyen den phai nam trong posCanMove
			boolean validPos = false;
			for (ChessPosition posCM : posCanMove) {
				if (posCM.getRow() == pos[0] &&
					posCM.getCol() == pos[1]) {
					validPos = true;
					break;
				}
			}
			if (validPos == false) return;
		
			// vi tri hop le thi tien hanh di chuyens
			ChessPosition oldPos = 
					new ChessPosition(posSelected[0], posSelected[1]);
			ChessPosition newPos = new ChessPosition(pos[0], pos[1]);
			
			// di chuyen
			this.move(oldPos, newPos);
		}
		
	}

}

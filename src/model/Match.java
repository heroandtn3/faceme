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
import control.CheckFinishMatch;
import control.CheckerFinish;
import control.Computer;
import control.ComputerMinmax;

/**
 * @author heroandtn3
 * @date Jan 7, 2013
 */
public class Match extends Observable {

	// game attributes
	private Level level;
	private GameState state;
	private Side currentSide;
	private Board board;
	private Chess[] chess;
	private Computer computer;
	private CheckerFinish checkerFinish = new CheckFinishMatch();
	
	private boolean checkmate = false; // chieu tuong
	private final boolean playWithCom = true; // co dinh
	
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
	// vi tri cu va moi, dung de luu vet, undo,redo,...
	private ChessPosition oldPos, newPos;
	
	// place AI computer in separate Thread 
	private Thread aiThread;

	/**
	 * 
	 */
	public Match() {
		board = new Board(table);
		initChess();
		oldPos = newPos = null;
		posCanMove = new ArrayList<ChessPosition>();
		state = GameState.PLAYING;
		currentSide = Side.BLACK;
		initComputer();
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
	
	private void initComputer() {
		if (playWithCom) {
			computer = new ComputerMinmax(this,
					(currentSide == Side.BLACK) ? Side.RED : Side.BLACK);
			aiThread = new Thread(new AIRun());
			aiThread.start(); // start AI thread
		}
	}
	
	/* For update game status -----------------------------------------------*/
	
	private void updateGameState() {
		// kiem tra chieu tuong
		checkmate = CheckingMate();
		if (checkmate) {
			System.out.println("Chieu tuong");
		}
		
		// kiem tra het co
		state = checkerFinish.getState(this);
		if (state == null) {
			System.out.println("`state` is null");
			return;
		}
		switch (state) {
			case NONE:
				System.out.println("Game is not stated yet!");
				break;
			case PLAYING:
				System.out.println(currentSide + " is thinkink...");
				break;
			case DRAW:
				System.out.println("Game draw!");
				break;
			case BLACK_WON:
				System.out.println("Black won!");
				break;
			case RED_WON:
				System.out.println("Red won!");
				break;
			default:
				System.out.println("`state` is unexpected result!");
				break;
				
		}
	}
	
	/**
	 * Kiem tra chieu tuong
	 * @return true neu `currentPlayer` dang bi chieu tuong
	 */
	private boolean CheckingMate() {
		int tuong;
		Side oppSide;
		if (currentSide == Side.BLACK) {
			tuong = -7;
			oppSide = Side.RED;
		} else {
			tuong = 7;
			oppSide = Side.BLACK;
		}
		
		for (int row = 0; row < 10; row++) {
			for (int col = 0; col < 9; col++) {
				int value = table[row][col];
				if (isChessOf(oppSide, value)) {
					ChessPosition current = new ChessPosition(row, col);
					for (ChessPosition pos : 
						chess[Math.abs(value)].getPosCanMove(current)) {
						if (table[pos.getRow()][pos.getCol()] == tuong) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	private boolean isChessOf(Side player, int value) {
		if ((player == Side.RED && value > 0) ||
			player == Side.BLACK && value < 0) {
			return true;
		} else {
			return false;
		}
	}

	private void updatePosCanMove() {
		if (oldPos == null) {
			posCanMove.clear();
		} else {
			int row = oldPos.getRow();
			int col = oldPos.getCol();
			int value = Math.abs(table[row][col]);
			ChessPosition currentPos = 
					new ChessPosition(row, col);
			posCanMove = chess[value].getPosCanMove(currentPos);
		}
		// thong bao cho view update
		setChanged();
		notifyObservers();
	}
	
	/* For moving chess------------------------------------------------------*/
	
	/**
	 * Ham thuc hien di chuyen quan co tu vi tri cu sang vi tri moi
	 * @param oldPos
	 * @param newPos
	 */
	public void move(ChessPosition oldPos, ChessPosition newPos) {
		if (state == GameState.PLAYING) {
			int[][] mt = board.getTable();
			
			// thay gia tri newPos bang gia tri o oldPos
			int tmp = mt[oldPos.getRow()][oldPos.getCol()];
			mt[oldPos.getRow()][oldPos.getCol()] = 0; // vi tri cu chuyen ve 0
			mt[newPos.getRow()][newPos.getCol()] = tmp;
			
			// clear posCanMove
			this.posCanMove.clear();
			
			// switch player
			currentSide = (currentSide == Side.BLACK) ?
					Side.RED : Side.BLACK;
			
			updateGameState();
			
			// danh dau la da thay doi va gui yeu cau update den cac observers
			setChanged();
			notifyObservers();
		}
	}
	
	public void setPos(ChessPosition pos) {
		if (isChooseForMove(pos)) { // chon quan de di chuyen
			// loai bo pos khong hop le
			if (table[pos.getRow()][pos.getCol()] == 0) return;
			
			oldPos = pos; // neu hop le thi danh dau oldPos da chon
			newPos = null; // va bo danh dau newPos
			
			// update pos can move
			updatePosCanMove();
		} else if (oldPos != null){ // neu da co pos duoc chon
			// thi di chuyen quan den vi tri moi
			
			// vi tri di chuyen den phai nam trong posCanMove
			boolean validPos = false;
			for (ChessPosition posCM : posCanMove) {
				if (posCM.getRow() == pos.getRow() &&
					posCM.getCol() == pos.getCol()) {
					validPos = true;
					break;
				}
			}
			if (validPos == false) return;
		
			// vi tri hop le thi tien hanh di chuyens
			newPos = pos;
			this.move(oldPos, newPos);
			
			// may tinh di chuyen
			if (playWithCom) {
				// ai thread
				synchronized (computer) {
					computer.notify();
				}
			}
		}
		
	}
	
	private boolean isChooseForMove(ChessPosition pos) {
		int value = table[pos.getRow()][pos.getCol()];
		if ((currentSide == Side.RED && value > 0) ||
			(currentSide == Side.BLACK && value < 0)) {
			return true;
		} else {
			return false;
		}
	}
	
	/*ai code--------------------------------------------------------------*/
	class AIRun implements Runnable {
		private volatile boolean stopped = false;
		@Override
		public void run() {
			while (!stopped) {
				synchronized (computer) {
					try {
						computer.wait(); // wait until computer.notify 
					} catch (InterruptedException e) {
						Thread.currentThread().interrupt();
						return;
					}
				}

				ChessPosition[] comMove = computer.getBestMove(level);
				oldPos = comMove[0];
				newPos = comMove[1];
				move(oldPos, newPos);
			}
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

	public List<ChessPosition> getPosCanMove() {
		return posCanMove;
	}

	public ChessPosition getOldPos() {
		return oldPos;
	}

	public ChessPosition getNewPos() {
		return newPos;
	}

	public Side getCurrentSide() {
		return currentSide;
	}

	public boolean isCheckmate() {
		return checkmate;
	}
}

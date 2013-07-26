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
package com.sangnd.faceme.view;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;


import com.sangnd.faceme.core.model.Board;
import com.sangnd.faceme.core.model.ChessPosition;
import com.sangnd.faceme.core.model.Constant;
import com.sangnd.faceme.core.model.GameState;
import com.sangnd.faceme.event.HasMoveCompleteListener;
import com.sangnd.faceme.event.HasSelectChessListener;
import com.sangnd.faceme.event.MoveCompleteListener;
import com.sangnd.faceme.event.SelectChessEvent;
import com.sangnd.faceme.event.SelectChessListener;

/**
 * @author heroandtn3
 * @date Jul 23, 2013
 */
public class BoardViewImpl extends JPanel implements BoardView, MouseListener, HasSelectChessListener, HasMoveCompleteListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image imgBoard;
	private Board board;
	private Image imgTuongDo;
	private Image imgSyDo;
	private Image imgTinhDo;
	private Image imgXeDo;
	private Image imgPhaoDo;
	private Image imgMaDo;
	private Image imgTotDo;
	private Image imgTuongDen;
	private Image imgSyDen;
	private Image imgTinhDen;
	private Image imgXeDen;
	private Image imgPhaoDen;
	private Image imgMaDen;
	private Image imgTotDen;
	private SelectChessListener selectChessListener;
	private Image imgSelect;
	private Image imgCanMove;
	private Image imgCanKill;
	private ChessPosition oldPos;
	private List<ChessPosition> posCanMove;
	private ChessPosition newPos;
	private boolean warnKing;
	private Image imgWarnKing;
	private GameState state;
	private Image imgFinish;
	private MoveCompleteListener moveCompleteListener;

	/**
	 * 
	 */
	public BoardViewImpl() {
		addMouseListener(this);
		setPreferredSize(new Dimension(483, 500));
		
		imgBoard = new ImageIcon(Constant.BOARD_DIR + "board.png").getImage();

		imgSelect = new ImageIcon(Constant.IMAGE_DIR + "select.png").getImage();
		imgCanMove = new ImageIcon(
				Constant.IMAGE_DIR + "canmove.png").getImage();
		imgCanKill = new ImageIcon(
				Constant.IMAGE_DIR + "cankill.png").getImage();
		
		imgWarnKing = new ImageIcon(Constant.IMAGE_DIR + "chieutuong.png").getImage();
		imgFinish = new ImageIcon(Constant.IMAGE_DIR + "hetco.png").getImage();
		
		imgTuongDo = new ImageIcon(Constant.CHESS_DIR + "tuongdo.png").getImage();
		imgSyDo = new ImageIcon(Constant.CHESS_DIR + "sydo.png").getImage();
		imgTinhDo = new ImageIcon(Constant.CHESS_DIR + "tinhdo.png").getImage();
		imgXeDo = new ImageIcon(Constant.CHESS_DIR + "xedo.png").getImage();
		imgPhaoDo = new ImageIcon(Constant.CHESS_DIR + "phaodo.png").getImage();
		imgMaDo = new ImageIcon(Constant.CHESS_DIR + "mado.png").getImage();
		imgTotDo = new ImageIcon(Constant.CHESS_DIR + "totdo.png").getImage();
		
		imgTuongDen = new ImageIcon(Constant.CHESS_DIR + "tuongden.png").getImage();
		imgSyDen = new ImageIcon(Constant.CHESS_DIR + "syden.png").getImage();
		imgTinhDen = new ImageIcon(Constant.CHESS_DIR + "tinhden.png").getImage();
		imgXeDen = new ImageIcon(Constant.CHESS_DIR + "xeden.png").getImage();
		imgPhaoDen = new ImageIcon(Constant.CHESS_DIR + "phaoden.png").getImage();
		imgMaDen = new ImageIcon(Constant.CHESS_DIR + "maden.png").getImage();
		imgTotDen = new ImageIcon(Constant.CHESS_DIR + "totden.png").getImage();
	}

	@Override
	public Container asPanel() {
		return this;
	}

	@Override
	public void clearContent() {
		// TODO Auto-generated method stub
		
	}
	

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawBoard(g);
		drawChess(g);
		
		drawPos(g, oldPos);
		drawPos(g, newPos);
		
		drawPosCanMove(g);
		
		drawGameStatus(g);
	}

	private void drawGameStatus(Graphics g) {
		if (warnKing) {
			g.drawImage(imgWarnKing, 30, 225, null);
		}
		
		if (state == GameState.FRIEND_WON || state == GameState.ENERMY_WON) {
			g.drawImage(imgFinish, 30, 225, null);
		}
	}

	private void drawPos(Graphics g, ChessPosition pos) {
		if (pos != null) {
			int[] posXY = convertToXY(pos);
			g.drawImage(imgSelect, posXY[0] - 21, posXY[1] - 21, 42, 42, null);
		}
	}
	
	private void drawPosCanMove(Graphics g) {
		// verify posCanMove
		if (posCanMove == null) return;
		
		for (ChessPosition posCM : posCanMove) {
			int[] pos = convertToXY(posCM);
			if (posCM.isKillable()) {
				g.drawImage(imgCanKill, pos[0] - 15, pos[1] - 15, null);
			} else {
				g.drawImage(imgCanMove, pos[0] - 15, pos[1] - 15, null);
			}
		}
	}

	private void drawBoard(Graphics g) {
		System.out.println("draw board");
		
		g.drawImage(imgBoard, 0, 0, null);
	}

	private void drawChess(Graphics g) {
		if (board == null) return;
		System.out.println("draw chess");
		int[][] table = board.getTable();
		for (int row = 0; row < 10; row++) {
			for (int col = 0; col < 9; col++) {
				int value = table[row][col];
				if (value != 0) {
					Image img = getShape(value);
					if (img != null) {
						int[] pos = convertToXY(new ChessPosition(row, col));
						g.drawImage(img, pos[0] - 21, pos[1] - 21, null);
					}
				}
			}
		}
		
	}
	
	private Image getShape(int code) {
		if (code < 0) {
			switch (code) {
				case -1:
					return imgTuongDen;
				case -2:
					return imgSyDen;
				case -3:
					return imgTinhDen;
				case -4:
					return imgXeDen;
				case -5:
					return imgPhaoDen;
				case -6:
					return imgMaDen;
				case -7:
					return imgTotDen;
			}
		} else if (code > 0) {
			switch (code) {
				case 1:
					return imgTuongDo;
				case 2:
					return imgSyDo;
				case 3:
					return imgTinhDo;
				case 4:
					return imgXeDo;
				case 5:
					return imgPhaoDo;
				case 6:
					return imgMaDo;
				case 7:
					return imgTotDo;
			}
		}
		return null;
	}
	
	/*some utilities---------------------------------------------------------*/

	/**
	 * Chuyen ChessPosition sang toa do x, y
	 * @param row
	 * @param col
	 * @return: mang 1 chieu chua 2 phan tu: 
	 * [0]: x
	 * [1]: y 
	 */
	private int[] convertToXY(ChessPosition pos) {
		int x = 30 + pos.getCol() * 53;
		int y = 25 + pos.getRow() * 50;
		return (new int[] { x, y });
	}
	
	/**
	 * Ham convert tu x, y sang ChessPosition
	 * @param x
	 * @param y
	 * @return: Neu x, y hop le thi tra ve 1 ChessPosition
	 * 			Neu khong thi tra ve: null
	 */
	private ChessPosition convertToChessPos(int x, int y) {
		int row = (y - 25 + 21) / 50;
		int col = (x - 30 + 21) / 53;
		if (row >= 0 && row < 10 && col >= 0 && col < 9) {
			return (new ChessPosition(row, col));
		} else {
			return null;
		}
	}

	@Override
	public void renderBoard(Board board) {
		this.board = board;
		this.repaint();
		System.out.println("Render board");
	}

	@Override
	public void renderMoving(ChessPosition oldPos, ChessPosition newPos) {
		this.oldPos = oldPos;
		this.newPos = newPos;
		this.repaint();
		moveCompleteListener.onComplete(null);
	}

	@Override
	public void renderChessSelect(ChessPosition selectedPos,
			List<ChessPosition> posCanMove) {
				this.oldPos = selectedPos;
				this.posCanMove = posCanMove;
				this.repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		ChessPosition pos = convertToChessPos(x, y);
		
		// kiem tra tinh hop le
		// loai bo neu la null
		if (pos == null) return;
		
		selectChessListener.onSelect(new SelectChessEvent(pos));
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addSelectChessListener(SelectChessListener listener) {
		this.selectChessListener = listener;
	}

	@Override
	public void renderWarnKing(boolean warnKing) {
		this.warnKing = warnKing;
	}

	@Override
	public void renderMatchFinish(GameState state) {
		this.state = state;
	}

	@Override
	public void addMoveCompleteListener(MoveCompleteListener listener) {
		moveCompleteListener = listener;
		
	}
}

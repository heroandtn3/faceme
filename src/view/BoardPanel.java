/*
Copyright 2013 heroandtn3 (@sangnd.info)

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
package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;

import model.ChessPosition;
import model.Constant;
import model.Match;

/**
 * @author heroandtn3
 * @date Jan 17, 2013
 */
public class BoardPanel extends CardPanel implements MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public final static String KEY = "BoardPanel";

	private Match match;
	private int[][] table;
	private int[] posSelected;
	private List<ChessPosition> posCanMove;

	private Image imgBoard;
	private Image imgSelect;
	private Image imgCanMove;
	private Image imgCanKill;

	/**
	 * 
	 */
	public BoardPanel(GamePanel gamePanel) {
		super(gamePanel);
		this.match = gamePanel.getMatch();
		init();
		initGUI();
	}

	private void init() {
		table = match.getBoard().getTable();
		
		// cai dat observer cho match
		match.addObserver(new Observer() {
			@Override
			public void update(Observable o, Object arg) {
				repaint();
			}
		});
		
		posSelected = null;
		posCanMove = new ArrayList<ChessPosition>();
	}

	private void initGUI() {
		setPreferredSize(new Dimension(483, 500));
		loadImages();
		addMouseListener(this);

	}

	private void loadImages() {
		/**
		 * Kich thuoc anh: 483 x 500 
		 * Goc ban co ben trai: 30 x 25 
		 * Kich thuoc o: 55 x 50
		 */
		imgBoard = new ImageIcon(Constant.BOARD_DIR + "board.png").getImage();
		imgSelect = new ImageIcon(Constant.IMAGE_DIR + "select.png").getImage();
		imgCanMove = new ImageIcon(
				Constant.IMAGE_DIR + "canmove.png").getImage();
		imgCanKill = new ImageIcon(
				Constant.IMAGE_DIR + "cankill.png").getImage();
	}

	/*draw method------------------------------------------------------------*/
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		drawBoard(g);
		drawChess(g);
		drawSelected(g);
		drawPosCanMove(g);
	}

	/**
	 * Ve ban co
	 * @param g
	 */
	private void drawBoard(Graphics g) {
		g.drawImage(imgBoard, 0, 0, null);
	}

	/**
	 * Ve cac quan co
	 * @param g
	 */
	private void drawChess(Graphics g) {
		for (int row = 0; row < 10; row++) {
			for (int col = 0; col < 9; col++) {
				int value = table[row][col];
				if (value != 0) { // kiem tra xem co quan co khong
					// neu co thi tien hanh ve
					Image img = match.getChess()[Math.abs(value)]
							.getShape(value); // lay hinh anh cua ban co de ve
					if (img != null) {
						int[] pos = convertToXY(row, col); // convert row, col 
														// sang x, y de ve
						g.drawImage(img, pos[0] - 21, pos[1] - 21, null);
					}
				}
			}
		}
	}
	
	private void drawSelected(Graphics g) {
		if (posSelected != null) {
			int[] pos = convertToXY(posSelected[0], posSelected[1]);
			g.drawImage(imgSelect, pos[0] - 21, pos[1] - 21, 42, 42, null);
		}
	}
	
	private void drawPosCanMove(Graphics g) {
		for (ChessPosition posCM : posCanMove) {
			int[] pos = convertToXY(posCM.getRow(), posCM.getCol());
			if (posCM.isKillable()) {
				g.drawImage(imgCanKill, pos[0] - 15, pos[1] - 15, null);
			} else {
				g.drawImage(imgCanMove, pos[0] - 15, pos[1] - 15, null);
			}
		}
		posCanMove.clear();
	}
	
	/*some utilities---------------------------------------------------------*/

	/**
	 * Chuyen toa do ban co row, col sang toa do x, y
	 * @param row
	 * @param col
	 * @return: mang 1 chieu chua 2 phan tu: 
	 * [0]: x
	 * [1]: y 
	 */
	private int[] convertToXY(int row, int col) {
		int x = 30 + col * 53;
		int y = 25 + row * 50;
		return (new int[] { x, y });
	}
	
	/**
	 * Ham convert tu x, y sang row, col
	 * @param x
	 * @param y
	 * @return: Neu x, y hop le thi tra ve mang int:
	 * [0]: row
	 * [1]: col
	 * 			Neu khong thi tra ve: null
	 */
	private int[] convertToRowCol(int x, int y) {
		int row = (y - 25 + 21) / 50;
		int col = (x - 30 + 21) / 53;
		if (row >= 0 && row < 10 && col >= 0 && col < 9) {
			return (new int[] {row, col});
		} else {
			return null;
		}
	}
	
	/*mouse event------------------------------------------------------------*/
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		int[] pos = convertToRowCol(x, y);
		
		// kiem tra tinh hop le
		// loai bo neu la null
		if (pos == null) return; 
		
		System.out.println(pos[0] + " - " + pos[1]);
		if (posSelected != null) {
			ChessPosition oldPos = new ChessPosition(posSelected[0], posSelected[1]);
			ChessPosition newPos = new ChessPosition(pos[0], pos[1]);
			match.move(oldPos, newPos); // di chuyen quan
			posSelected = null; // xoa vet
			
		} else if (table[pos[0]][pos[1]] != 0) {
			// loai bo o trong, chi luu cac vet la o co quan co
			posSelected = pos; // luu vet
			int value = Math.abs(table[pos[0]][pos[1]]);
			ChessPosition currentPos = new ChessPosition(pos[0], pos[1]);
			setPosCanMove(match.getChess()[value].getPosCanMove(currentPos));
			setPosSelected(pos);
		}
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
	
	/*get, set --------------------------------------------------------------*/

	public int[] getPosSelected() {
		return posSelected;
	}

	public void setPosSelected(int[] posSelected) {
		this.posSelected = posSelected;
		repaint();
	}

	public List<ChessPosition> getPosCanMove() {
		return posCanMove;
	}

	public void setPosCanMove(List<ChessPosition> posCanMove) {
		this.posCanMove = posCanMove;
		repaint();
	}

}
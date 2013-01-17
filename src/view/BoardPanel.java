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
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;

import model.Constant;
import model.Match;
import control.BoardEvent;

/**
 * @author heroandtn3
 * @date Jan 17, 2013
 */
public class BoardPanel extends CardPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public final static String KEY = "BoardPanel";

	private Match match;
	private int[][] table;

	private Image imgBoard;

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
		match.addObserver(new Observer() {

			@Override
			public void update(Observable o, Object arg) {
				repaint();
			}

		});
	}

	private void initGUI() {
		setPreferredSize(new Dimension(483, 500));
		loadImages();
		addMouseListener(new BoardEvent(match));

	}

	private void loadImages() {
		/**
		 * Kich thuoc anh: 483 x 500 
		 * Goc ban co ben trai: 30 x 25 
		 * Kich thuoc o: 55 x 50
		 */
		imgBoard = new ImageIcon(Constant.BOARD_DIR + "board.png").getImage();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		drawBoard(g);
		drawChess(g);
	}

	private void drawBoard(Graphics g) {
		g.drawImage(imgBoard, 0, 0, null);
	}

	public void drawChess(Graphics g) {
		for (int row = 0; row < 10; row++) {
			for (int col = 0; col < 9; col++) {
				int[] pos = convert(row, col);
				int value = table[row][col];
				if (value != 0) { // co quan co o do
					Image img = match.getChess()[Math.abs(value)]
							.getShape(value);
					if (img != null) {
						g.drawImage(img, pos[0], pos[1], null);
					}
				}
			}
		}
	}

	private int[] convert(int row, int col) {
		int x = 30 + col * 53 - 21;
		int y = 25 + row * 50 - 21;
		return (new int[] { x, y });
	}

}
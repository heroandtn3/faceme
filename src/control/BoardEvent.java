/**
 * Lop xu ly su kien click chuot vao BoardPanel
 */

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
package control;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import model.ChessPosition;
import model.Match;
import model.chess.Chess;
import view.BoardPanel;

/**
 * @author heroandtn3
 * @date Jan 17, 2013
 */
public class BoardEvent implements MouseListener {

	private Match match;
	private Chess[] chess;
	private int[][] table;
	
	/**
	 * Mang luu vi tri da chon
	 * [0]: row
	 * [1]: col
	 */
	private int[] posSelected;
	
	/**
	 * 
	 */
	public BoardEvent(Match match) {
		this.match = match;
		chess = match.getChess();
		table = match.getBoard().getTable();
		posSelected = null;
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
	private int[] convert(int x, int y) {
		int row = (y - 25 + 21) / 50;
		int col = (x - 30 + 21) / 53;
		if (row >= 0 && row < 10 && col >= 0 && col < 9) {
			return (new int[] {row, col});
		} else {
			return null;
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		int[] pos = convert(x, y);
		
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
			BoardPanel board = (BoardPanel) (e.getSource());
			int value = table[pos[0]][pos[1]];
			ChessPosition currentPos = new ChessPosition(pos[0], pos[1]);
			board.setPosCanMove(chess[Math.abs(value)].getPosCanMove(currentPos));
			board.setPosSelected(pos);
			
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
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

	public void setPosSelected(int[] posSelected) {
		this.posSelected = posSelected;
	}

}

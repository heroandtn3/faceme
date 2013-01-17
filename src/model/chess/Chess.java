package model.chess;

import java.awt.Image;
import java.util.List;

import model.Board;
import model.ChessPosition;

public abstract class Chess {

	protected Board board;
	protected Image imgRed, imgBlack;

	/**
	 * 
	 * @param board
	 */
	public Chess(Board board) {
		this.board = board;
	}

	/**
	 * tra ve mang chua vi tri co the di
	 * @param current
	 */
	public abstract List<ChessPosition> getPosCanMove(ChessPosition current);

	/**
	 * Hàm này lấy những vị trí mà quân cờ có thể ăn, hoặc quân cờ mà nó bảo vệ
	 * 
	 * @param current
	 * @return
	 */
	public abstract List<ChessPosition> getTargetPos(ChessPosition current);
	
	
	public Image getShape(int value) {
		if (value > 0) {
			return imgRed;
		} else {
			return imgBlack;
		}
	}
}

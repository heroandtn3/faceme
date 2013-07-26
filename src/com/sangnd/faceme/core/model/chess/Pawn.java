/**
 * Quan tot
 */
package com.sangnd.faceme.core.model.chess;

import java.util.ArrayList;
import java.util.List;

import com.sangnd.faceme.core.model.Board;
import com.sangnd.faceme.core.model.ChessPosition;




public class Pawn implements Chess {

	private boolean passedRiver = false;
	private Board board;

	public Pawn(Board board) {
		this.board = board;
	}

	private boolean CheckPassRiver(ChessPosition current, int side) {
		if (side * board.getTable()[current.getRow()][current.getCol()] < 0)
			return true;
		return false;
	}

	public List<ChessPosition> getPosCanMove(ChessPosition current) {
		List<ChessPosition> pos = new ArrayList<ChessPosition>();
		ChessPosition CpTemp;
		int x, y, value, omg, tmpX, tmpY;
		x = current.getCol();
		y = current.getRow();
		if (y <= 4)
			omg = -1;
		else
			omg = 1;
		passedRiver = CheckPassRiver(current, omg);
		value = board.getTable()[y][x];
		if (passedRiver) {
			
			// go forward
			tmpX = x;
			tmpY = y - omg;
			
			if ((tmpY >= 0)
					&& (tmpY <= 9)
					&& ((board.getTable()[tmpY][tmpX] == 0) || (board
							.getTable()[tmpY][tmpX] * value < 0))) {
				if (board.getTable()[tmpY][tmpX] * value < 0) {
					CpTemp = new ChessPosition(tmpX, tmpY, true);
				} else {
					CpTemp = new ChessPosition(tmpX, tmpY, false);
				}
				pos.add(CpTemp);
				
			}
			tmpX = x - 1;
			tmpY = y;
			if ((tmpX >= 0)
					&& ((board.getTable()[tmpY][tmpX] == 0) || (board
							.getTable()[tmpY][tmpX] * value < 0))) {

				if (board.getTable()[tmpY][tmpX] * value < 0) {
					CpTemp = new ChessPosition(tmpX, tmpY, true);
				} else {
					CpTemp = new ChessPosition(tmpX, tmpY, false);
				}
				pos.add(CpTemp);
			}

			tmpX = x + 1;
			tmpY = y;
			if ((tmpX <= 8)
					&& ((board.getTable()[tmpY][tmpX] == 0) || (board
							.getTable()[tmpY][tmpX] * value < 0))) {
				if (board.getTable()[tmpY][tmpX] * value < 0) {
					CpTemp = new ChessPosition(tmpX, tmpY, true);
				} else {
					CpTemp = new ChessPosition(tmpX, tmpY, false);
				}
				pos.add(CpTemp);
			}
		} else {
			tmpX = x;
			tmpY = y - omg;
			if ((tmpY >= 0)
					&& ((board.getTable()[tmpY][tmpX] == 0) || (board
							.getTable()[tmpY][tmpX] * value < 0))) {
				if (board.getTable()[tmpY][tmpX] * value < 0) {
					CpTemp = new ChessPosition(tmpX, tmpY, true);
				} else {
					CpTemp = new ChessPosition(tmpX, tmpY, false);
				}
				pos.add(CpTemp);
			}

		}

		return pos;
	}

	@Override
	public List<ChessPosition> getTargetPos(ChessPosition current) {
		List<ChessPosition> pos = new ArrayList<ChessPosition>();
		ChessPosition CpTemp;
		int x, y, value, omg, tmpX, tmpY;
		x = current.getCol();
		y = current.getRow();
		if (y <= 4)
			omg = 1;
		else
			omg = -1;
		passedRiver = CheckPassRiver(current, omg);
		value = board.getTable()[y][x];
		if (passedRiver) {
			tmpX = x;
			tmpY = y - omg;
			if ((tmpY >= 0) && (tmpY <= 9)
					&& (board.getTable()[tmpY][tmpX] != 0)) {
				if (board.getTable()[tmpY][tmpX] * value < 0) {
					CpTemp = new ChessPosition(tmpX, tmpY, true);
				} else {
					CpTemp = new ChessPosition(tmpX, tmpY, false);
				}
				pos.add(CpTemp);
			}
			tmpX = x - 1;
			tmpY = y;
			if ((tmpX >= 0) && (board.getTable()[tmpY][tmpX] != 0)) {

				if (board.getTable()[tmpY][tmpX] * value < 0) {
					CpTemp = new ChessPosition(tmpX, tmpY, true);
				} else {
					CpTemp = new ChessPosition(tmpX, tmpY, false);
				}
				pos.add(CpTemp);
			}

			tmpX = x + 1;
			tmpY = y;
			if ((tmpX <= 8) && (board.getTable()[tmpY][tmpX] != 0)) {
				if (board.getTable()[tmpY][tmpX] * value < 0) {
					CpTemp = new ChessPosition(tmpX, tmpY, true);
				} else {
					CpTemp = new ChessPosition(tmpX, tmpY, false);
				}
				pos.add(CpTemp);
			}
		} else {
			tmpX = x;
			tmpY = y + omg;
			if ((tmpY >= 0) && (board.getTable()[tmpY][tmpX] != 0)) {
				if (board.getTable()[tmpY][tmpX] * value < 0) {
					CpTemp = new ChessPosition(tmpX, tmpY, true);
				} else {
					CpTemp = new ChessPosition(tmpX, tmpY, false);
				}
				pos.add(CpTemp);
			}

		}

		return pos;
	}
}

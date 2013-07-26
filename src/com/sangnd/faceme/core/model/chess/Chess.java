package com.sangnd.faceme.core.model.chess;

import java.util.List;

import com.sangnd.faceme.core.model.ChessPosition;




public interface Chess {

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

}

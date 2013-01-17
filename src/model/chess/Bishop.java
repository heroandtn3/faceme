package model.chess;

import java.util.ArrayList;
import java.util.List;

import model.ChessPosition;
import model.Match;

public class Bishop extends Chess {
	//Match Match = new Match();
	public Bishop(String img, int row, int col) {
		super(img, row, col);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<ChessPosition> getPosCanMove(Match match, ChessPosition current,int side) {
		List<ChessPosition> pos = new ArrayList<ChessPosition>();
		int x,y,upBound,lowBound,leftBound,rightBound,value;
		int dx[] = {0,2,2,-2,-2};
		int dy[] = {0,2,-2,2,-2};
		int cdx[] = {0,1,1,-1,-1};
		int cdy[] = {0,1,-1,1,-1};
		ChessPosition CpTemp  ;
		int tempX=0,tempY=0;
		/*
		 * khoi tao gioi han di chuyen cho quan sy
		 */
		
		x = current.getCol() ;
		y = current.getRow() ;
		value = match.getTablePos()[y][x];
		if (y<=4) {
			upBound = 0;
			lowBound= 4;
		} else {
			upBound = 5;
			lowBound = 9 ;
		}
		leftBound = 0 ;
		rightBound = 8;
		/*
		 * Xet 4 o quanh o tinh, kiem tra hop le, neu hop le thi cho di
		 */
		for (int i=1 ; i<=4 ; i++){
			x=current.getCol()+dx[i];
			y=current.getRow()+dy[i];
			tempX=current.getCol() + cdx[i];
			tempY=current.getRow() + cdy[i];
			if (((x>=leftBound)&&(x<=rightBound))&&((y>=upBound)&&(y<=lowBound))){
				if ((match.getTablePos()[y][x]==0)||(match.getTablePos()[y][x]*value < 0)){
					if (match.getTablePos()[tempY][tempX]==0) {
						if (match.getTablePos()[y][x]*value < 0) {
							CpTemp = new ChessPosition(x,y,true);
						} else {
							CpTemp = new ChessPosition(x,y,false);
						}
						pos.add(CpTemp);
					}
					
				}
			}
		}
		// TODO Auto-generated method stub
		return pos;
	}
	
	@Override
	public List<ChessPosition> getTargetPos(Match match, ChessPosition current,int side) {
		List<ChessPosition> pos = new ArrayList<ChessPosition>();
		int x,y,upBound,lowBound,leftBound,rightBound,value;
		int dx[] = {0,2,2,-2,-2};
		int dy[] = {0,2,-2,2,-2};
		int cdx[] = {0,1,1,-1,-1};
		int cdy[] = {0,1,-1,1,-1};
		ChessPosition CpTemp  ;
		int tempX=0,tempY=0;
		/*
		 * khoi tao gioi han di chuyen cho quan sy
		 */
		
		x = current.getCol() ;
		y = current.getRow() ;
		value = match.getTablePos()[y][x];
		if (y<=4) {
			upBound = 0;
			lowBound= 4;
		} else {
			upBound = 5;
			lowBound = 9 ;
		}
		leftBound = 0 ;
		rightBound = 8;
		/*
		 * Xet 4 o quanh o tinh, kiem tra hop le, neu hop le thi cho di
		 */
		for (int i=1 ; i<=4 ; i++){
			x=current.getCol()+dx[i];
			y=current.getRow()+dy[i];
			tempX=current.getCol() + cdx[i];
			tempY=current.getRow() + cdy[i];
			if (((x>=leftBound)&&(x<=rightBound))&&((y>=upBound)&&(y<=lowBound))){
				if (match.getTablePos()[y][x]!=0){
					if (match.getTablePos()[tempY][tempX]==0) {
						if (match.getTablePos()[y][x]*value < 0) {
							CpTemp = new ChessPosition(x,y,true);
						} else {
							CpTemp = new ChessPosition(x,y,false);
						}
						pos.add(CpTemp);
					}
					
				}
			}
		}
		// TODO Auto-generated method stub
		return pos;
	}

}

/*
 * Author : HoangNv
 */
package model.chess;

import java.util.ArrayList;
import java.util.List;

import model.ChessPosition;
import model.Match;

public class Knight extends Chess {

	public Knight(String img, int row, int col) {
		// TODO Auto-generated constructor stub
		super(img, row, col);
	}

	@Override
	public List<ChessPosition> getPosCanMove(Match match, ChessPosition current,  int type) {
		List<ChessPosition> pos = new ArrayList<ChessPosition>();
		int x,y,upBound,lowBound,leftBound,rightBound,value,cy,cx;
		int Tdx[] = {0,-1,1,2,2,1,-1,-2,-2} ;
		int Tdy[] = {0,-2,-2,-1,1,2,2,1,-1} ;
		int dx[] = {0,0,1,0,-1};
		int dy[] = {0,-1,0,1,0};
		ChessPosition CpTemp ;
		x = current.getCol() ;
		y = current.getRow() ;
		value = match.getTablePos()[y][x];

		//khoi tao gioi han di chuyen cho quan sy
		 
		x = current.getCol() ;
		y = current.getRow() ;
		value = match.getTablePos()[y][x];

		upBound = 0; lowBound = 9;
		leftBound = 0 ; rightBound = 8;
		
		//Xet 4 o quanh o sy, kiem tra hop le, neu hop le thi cho di
		
		for (int i=1 ; i<=8 ; i++){
			x=current.getCol()+Tdx[i];
			y=current.getRow()+Tdy[i];
			cx=current.getCol() + dx[(i+1)/2];
			cy = current.getRow() + dy[(i+1)/2];
			if (((x>=leftBound)&&(x<=rightBound))&&((y>=upBound)&&(y<=lowBound))){
				if (((match.getTablePos()[y][x]==0)||(match.getTablePos()[y][x]*value < 0))&&(match.getTablePos()[cy][cx]==0)){
					if (match.getTablePos()[y][x]*value < 0) {
						CpTemp = new ChessPosition(x,y,true);
					} else {
						CpTemp = new ChessPosition(x,y,false);
					}
					pos.add(CpTemp);
				}
				
			}
		}
		return pos;
	}
	
	@Override
	public List<ChessPosition> getTargetPos(Match match, ChessPosition current,  int type) {
		List<ChessPosition> pos = new ArrayList<ChessPosition>();
		int x,y,upBound,lowBound,leftBound,rightBound,value,cy,cx;
		int Tdx[] = {0,-1,1,2,2,1,-1,-2,-2} ;
		int Tdy[] = {0,-2,-2,-1,1,2,2,1,-1} ;
		int dx[] = {0,0,1,0,-1};
		int dy[] = {0,-1,0,1,0};
		ChessPosition CpTemp ;
		x = current.getCol() ;
		y = current.getRow() ;
		value = match.getTablePos()[y][x];

		//khoi tao gioi han di chuyen cho quan sy
		 
		x = current.getCol() ;
		y = current.getRow() ;
		value = match.getTablePos()[y][x];

		upBound = 0; lowBound = 9;
		leftBound = 0 ; rightBound = 8;
		
		//Xet 4 o quanh o sy, kiem tra hop le, neu hop le thi cho di
		
		for (int i=1 ; i<=8 ; i++){
			x=current.getCol()+Tdx[i];
			y=current.getRow()+Tdy[i];
			cx=current.getCol() + dx[(i+1)/2];
			cy = current.getRow() + dy[(i+1)/2];
			if (((x>=leftBound)&&(x<=rightBound))&&((y>=upBound)&&(y<=lowBound))){
				if ((match.getTablePos()[y][x]!=0)&&(match.getTablePos()[cy][cx]==0)){
					if (match.getTablePos()[y][x]*value < 0) {
						CpTemp = new ChessPosition(x,y,true);
					} else {
						CpTemp = new ChessPosition(x,y,false);
					}
					pos.add(CpTemp);
				}
				
			}
		}
		return pos;
	}
}

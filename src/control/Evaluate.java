package control;

import java.util.ArrayList;
import java.util.List;

import model.ChessPosition;
import model.Match;

public class Evaluate {
	public int PosValue[][][] = {
			{ 		{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },  //zero 
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0 } 
			}, 
			{ 		{ 0,  3,  6,  9,  12,  9,  6,  3,  0 },//pawn
					{ 18, 36, 56, 80, 120, 80, 56, 36, 18 },
					{ 14, 26, 42, 60, 80,  60, 42, 26, 14 },
					{ 10, 20, 30, 34, 40,  34, 30, 20, 10 },
					{ 6,  12, 18, 18, 20,  18, 18, 12, 6 },
					{ 2,  0,  8,  0,  8,   0,  8,  0,  2 },
					{ 0, 0, -2, 0, 4, 0, -2, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 
			},		
			{ 		{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },  //advisor
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, -1, 0, -1, 0, 0, 0 },
					{ 0, 0, 0, 0, 3, 0, 0, 0, 0 },
					{ 0, 0, 0, 1, 0, 1, 0, 0, 0 } 
			},
			{ 		{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },  //bishop 
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, -1, 0, 0, 0, -1, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ -2, 0, 0, 0, 3, 0, 0, 0, -2 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 1, 0, 0, 0, 1, 0, 0 } 
			},
			{ 		{ 4, 8, 16, 12, 4, 12, 16, 8, 4 },  //knight
					{ 4, 10, 28, 16, 8, 16, 28, 10, 4 },
					{ 12, 14, 16, 20, 18, 20, 16, 14, 12 },
					{ 8, 24, 18, 24, 20, 24, 18, 24, 8 },
					{ 6, 16, 14, 18, 16, 18, 14, 16, 6 },
					{ 4, 12, 16, 14, 12, 14, 16, 12, 4 },
					{ 2, 6, 8, 6, 10, 6, 8, 6, 2 },
					{ 4, 2, 8, 8, 4, 8, 8, 2, 4 },
					{ 0, 2, 4, 4, -2, 4, 4, 2, 0 },
					{ 0, -4, 0, 0, 0, 0, 0, -4, 0 }, 
			},
			{ 		{ 6, 4, 0, -10, -12, -10, 0, 4, 6 },  //cannon
					{ 2, 2, 0, -4, -14, -4, 0, 2, 2 },
					{ 2, 2, 0, -10, -8, -10, 0, 2, 2 },
					{ 0, 0, -2, 4, 10, 4, -2, 0, 0 },
					{ 0, 0, 0, 2, 8, 2, 0, 0, 0 },
					{ -2, 0, 4, 2, 6, 2, 4, 0, -2 },
					{ 0, 0, 0, 2, 4, 2, 0, 0, 0 },
					{ 4, 0, 8, 6, 10, 6, 8, 0, 4 },
					{ 0, 2, 4, 6, 6, 6, 4, 2, 0 },
					{ 0, 0, 2, 6, 6, 6, 2, 0, 0 }, 
			},
			{ 		{ 14, 14, 12, 18, 16, 18, 12, 14, 14 },  //rook
					{ 16, 20, 18, 24, 26, 24, 18, 20, 16 },
					{ 12, 12, 12, 18, 18, 18, 12, 12, 12 },
					{ 12, 18, 16, 22, 22, 22, 16, 18, 12 },
					{ 12, 14, 12, 18, 18, 18, 12, 14, 12 },
					{ 12, 16, 14, 20, 20, 20, 14, 16, 12 },
					{ 6, 10, 8, 14, 14, 14, 8, 10, 06 },
					{ 4, 8, 6, 14, 12, 14, 6, 8, 4, 8 },
					{ 8, 4, 8, 16, 8, 16, 8, 4, 8 },
					{ -2, 10, 6, 14, 12, 14, 6, 10, -2 }, 
			},
			{ 		{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },  //king 
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, -5, -4, -5, 0, 0, 0 },
					{ 0, 0, 0, -4, -3, -4, 0, 0, 0 },
					{ 0, 0, 0, -3, 2, -3, 0, 0, 0 } 
			} 
	  };

	private boolean endGame;
	private int BaseValue[] = { 0, 30, 120, 120, 270, 285, 600, 6000 };
	private int CurValue[] = new int[9];

	public void PieceValue(Match match) {
		for (int i = 1; i <= match.getCount()[0]; i++)
			if (match.getChess()[0][i].getIsAlive())
				match.getChess()[0][i].setValue(CurValue[match.getChess()[0][i].getPiece()]);
		
		for (int i = 1; i <= match.getCount()[1]; i++)
			if (match.getChess()[1][i].getIsAlive())
				match.getChess()[1][i].setValue(CurValue[match.getChess()[1][i].getPiece()]);
	}
	public void PositionValue(Match match) {
		for (int i = 1; i <= match.getCount()[0]; i++)
			if (match.getChess()[0][i].getIsAlive()){
				int x = match.getChess()[0][i].getX();
				int y = 9-match.getChess()[0][i].getY();
				int piece = match.getChess()[0][i].getPiece();
				int value = PosValue[piece][y][x];
				int temp = match.getChess()[0][i].getValue();
				temp = temp + value;
				match.getChess()[0][i].setValue(temp);
			}
				
		for (int i = 1; i <= match.getCount()[1]; i++)
			if (match.getChess()[1][i].getIsAlive()){
				int x = match.getChess()[1][i].getX();
				int y = match.getChess()[1][i].getY();
				int piece = match.getChess()[1][i].getPiece();
				int value = PosValue[piece][y][x];
				int temp = match.getChess()[1][i].getValue();
				temp = temp + value;
				match.getChess()[1][i].setValue(temp);
			}
	}
	public void RelationValue(Match match) {
		// just apply for Attack and Guard between two pieces
		
		for (int i = 1; i <= match.getCount()[0]; i++)
			if (match.getChess()[0][i].getIsAlive()){
				int x = match.getChess()[0][i].getX();
				int y = match.getChess()[0][i].getY();
				int piece = match.getChess()[0][i].getPiece();
				int temp = match.getChess()[0][i].getValue();
				List <ChessPosition> posCanMove = new ArrayList<ChessPosition>();
				ChessPosition current = new ChessPosition(x,y,false);
				posCanMove = match.getPieceChess()[0][piece].getTargetPos(match, current, 0);
				for (ChessPosition pos : posCanMove){
					int xx = pos.getCol();
					int yy = pos.getRow();
					int p  = Math.abs(match.getTablePos()[yy][xx]);
					int ltemp = temp + CurValue[p]/16;
					match.getChess()[0][i].setValue(ltemp);
				}
			}
		for (int i = 1; i <= match.getCount()[1]; i++)
			if (match.getChess()[1][i].getIsAlive()){
				int x = match.getChess()[1][i].getX();
				int y = match.getChess()[1][i].getY();
				int piece = match.getChess()[1][i].getPiece();
				int temp = match.getChess()[1][i].getValue();
				List <ChessPosition> posCanMove = new ArrayList<ChessPosition>();
				ChessPosition current = new ChessPosition(x,y,false);
				posCanMove = match.getPieceChess()[1][piece].getTargetPos(match, current, 0);
				for (ChessPosition pos : posCanMove){
					int xx = pos.getCol();
					int yy = pos.getRow();
					int p  = Math.abs(match.getTablePos()[yy][xx]);
					int ltemp = temp + CurValue[p]/16;
					match.getChess()[1][i].setValue(ltemp);
				}
			}
	}
	/*
	public int FlexValue() {
		res0 = 0;
		res1 = 0;
		for (int i = 1; i <= match.getCount()[0]; i++)
			if (match.getChess()[0][i].getIsAlive())
				res0 = res0 + CurValue[match.getChess()[0][i].getPiece()];
		for (int i = 1; i <= match.getCount()[1]; i++)
			if (match.getChess()[1][i].getIsAlive())
				res1 = res1 + CurValue[match.getChess()[1][i].getPiece()];
		return res0 - res1;
	}
   */
	public void IsEndGame(Match match) {
		int count = 0;
		for (int i = 0; i <= 1; i++)
			for (int j = 1; j <= match.getCount()[i]; j++) {
				if (match.getChess()[i][j].getIsAlive())
					count++;
			}
		endGame = false;
		if (count <= 12)
			endGame = true;
	}

	public void InitValue() {
		for (int i = 1; i <= 7; i++)
			CurValue[i] = BaseValue[i];
		if (endGame) {
			CurValue[1] = 60;
			CurValue[4] = 285;
			CurValue[5] = 270;
		}
	}

	public int Eval(Match match) {
		IsEndGame(match);
		InitValue();
		PieceValue(match);
		PositionValue(match);
		//RelationValue();
		int res0 = 0;
		int res1 = 0;
		for (int i=1 ; i<=match.getCount()[0] ; i++){
			if (match.getChess()[0][i].getIsAlive())
			res0 = res0 + match.getChess()[0][i].getValue();
		}
		for (int i=1 ; i<=match.getCount()[1] ; i++){
			if (match.getChess()[1][i].getIsAlive())
			res1 = res1 + match.getChess()[1][i].getValue();
		}
		return res0 - res1;
	}
}

package control;

import java.util.ArrayList;
import java.util.List;

import model.Constant;
import model.Match;
import model.MoveInfo;

public class Computer {
	//Match match = null;
	Evaluate master = new Evaluate();
	MoveInfo lastMove = new MoveInfo();
	
	public int alphaBeta(Match match, int alpha, int beta ,int depth , int type){
		int value = 0;
		if (depth <=0) {
			if (lastMove.getpiece() == 0) return master.Eval(match);
			return quiescentSearch(match, alpha,beta,1- type);
		}
		int best = -Constant.INFINITY-1;
		List <MoveInfo> arr = new ArrayList<MoveInfo>();
		arr = match.GetOrderedMoves(match, type);
		for (MoveInfo move : arr){
				match.tryMove(move);
				value = -alphaBeta(match, -beta,-alpha,depth - 1, 1 - type);
				match.undoMove(move);
				if (value > best) {
					best = value;
					if (depth == match.getLevel()){
						match.setNewMove(move);
					} 
				}
				if (best > alpha) alpha = best;
				if (best >= beta) break;
			}
		return best;
	}
	
	public List <MoveInfo> FilterMoves(Match match, int type){
		List <MoveInfo> arr  = match.GetOrderedMoves(match, type);
		List <MoveInfo> list = new ArrayList<MoveInfo>();
		for (MoveInfo move : arr){
			if (move.getpiece() != 0) list.add(move);
		}
		return list;
	}
	
	public int quiescentSearch(Match match, int alpha,int beta, int type){
		/*
		 * Phương pháp tìm kiếm chỉ thực hiện tìm kiếm với các nước ăn quân, hoặc
		 * chiếu tướng. Thuật toán chỉ dừng lại để đánh giá khi tướng bị ăn,hoặc không còn nước
		 * nào để đi.
		 * This makes the difference :D
		 */
		int best = -Constant.INFINITY-1;
		int value = 0;
		if (match.isFinish()){
			return Constant.INFINITY;
		}
		List <MoveInfo> listMoves = FilterMoves(match, type);
		if (listMoves.isEmpty()) return master.Eval(match);
		for (MoveInfo move : listMoves){
			match.tryMove(move);
			value = -quiescentSearch(match, -beta,-alpha,1 - type);
			match.undoMove(move);
			if (value > best)  best = value;
			if (best  > alpha) alpha = best;
			if (best  >= beta) break;
		}
		return best;
		
	}
	public int Search(Match match, int alpha ,int beta , int depth , int ply ,boolean nullOk, int type){
		/*
		 * Hàm xử lý chính cho việc tìm kiếm 
		 * Ở đây sẽ tùy vào nước đi, mà chọn phương pháp tìm kiếm thích hợp
		 */
		boolean failHigh;
		if (depth <=0) {
			if (lastMove.getpiece() == 0) return master.Eval(match);
			return quiescentSearch(match, alpha,beta,1- type);
		}
		//if (!Match.isCheck(type)&&)
		return 0;
	}
	public int negaScout(int alpha,int beta, int depth, int ply , int type){
		return 0;
	}
	public void thinking(Match match, int type) {
		match.initChess();
		alphaBeta(match, -Constant.INFINITY,Constant.INFINITY, match.getLevel(),type);
		
	}
}

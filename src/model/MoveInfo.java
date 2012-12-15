/**
 * 
 */
package model;

/**
 * @author hoangnv
 *
 */
public class MoveInfo {
	private	int x;
	private int y;
	private int xx;
	private int yy;
	private int from_id;
	private int to_id;
	private int piece;
	private int cost;
	public MoveInfo(){
		
	}
	public MoveInfo(int x,int y,int xx, int yy,int piece,int from_id,int to_id){
		this.x = x;
		this.y = y;
		this.xx  = xx;
		this.yy = yy;
		this.piece = piece;
		this.from_id = from_id;
		this.to_id = to_id;
	}
	public void setCost(int X){
		this.cost = X;
	}
	public int getCost(){
		return this.cost;
	}
	public int getfromId(){
		return this.from_id;
	}
	public int gettoId(){
		return this.to_id;
	}
	public int getxx(){
		return this.xx;
	}
	public int getyy(){
		return this.yy;
	}
	public int getx(){
		return this.x;
	}
	public int gety(){
		return this.y;
	}
	public int getpiece(){
		return this.piece;
	}
}

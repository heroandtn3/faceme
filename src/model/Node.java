package model;

public class Node {
	private int xdau=0;
	private int ydau=0;
	private int gtdau=0;
	private int gtcuoi=0;
	private int xcuoi=0;
	private int ycuoi=0;
	public Node(int xd,int yd,int xc,int yc,int gtdau,int gtcuoi){
		this.xdau=xd;
		this.ydau=yd;
		this.xcuoi=xc;
		this.ycuoi=yc;
		this.gtdau=gtdau;
		this.gtcuoi=gtcuoi;
	}
	public void setXdau(int x){
		this.xdau=x;
	}
	public int getXdau(){
		return xdau;
	}
	public void setYdau(int y){
		this.ydau=y;
	}
	public int getYdau(){
		return ydau;
	}
	public void setXcuoi(int x){
		this.xcuoi=x;
	}
	public int getXcuoi(){
		return xcuoi;
	}
	public void setYcuoi(int y){
		this.ycuoi=y;
	}
	public int getYcuoi(){
		return ycuoi;
	}
	public void setGtDau(int gt){
		this.gtdau=gt;
	}
	public int getGtDau(){
		return gtdau;
	}
	public void setGtCuoi(int gt){
		this.gtcuoi=gt;
	}
	public int getGtCuoi(){
		return gtcuoi;
	}
}

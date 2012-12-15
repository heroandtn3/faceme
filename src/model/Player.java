package model;

import control.Computer;

public class Player {
	Computer com;
	Match match;
	public boolean kiemtraquandochieutuong(int a[][]){
		int i,j;
		int x = 0,y = 0,dem,li;
		for (i=0;i<=9;i++)
			for (j=0;j<=8;j++)
				if (a[i][j]==-7){
					x=i;
					y=j;
					break;
				}
		if (x==0 && y==0) return true;
//-----------------------------------------------------------------
		//kiem tra xem tuong co bi ma  do chieu khong
		int Tdx[] = {0,-1,1,2,2,1,-1,-2,-2} ;
		int Tdy[] = {0,-2,-2,-1,1,2,2,1,-1} ;
		int dx[] = {0,0,1,0,-1};
		int dy[] = {0,-1,0,1,0};
		for (int k=1;k<=8;k++){
			int x1=x+Tdx[k];
			int y1=y+Tdy[k];
			if (x1>=0 && y1>=0 &&x1<=9 &&y1<=8)
			if (a[x1][y1]==4){
				if (x1<x && y1>y && a[x-1][y+1]==0)
					return false; 
				if (x1<x && y1<y && a[x-1][y-1]==0)
					return false;
				if (x1>x && y1>y && a[x+1][y+1]==0)
					return false;
				if (x1>x && y1<y && a[x+1][y-1]==0)
					return false;
				
			}
		}
//-----------------------------------------------------------------------------------------
		// kiem tra xem tuong co bi xe do chieu khong
		//len tren
		for (i=x-1;i>=0;i--){
			if (a[i][y]==6)return false;
			if (a[i][y]!=0) break;
		}
		//xuong duoi
		for (i=x+1;i<10;i++){
			if (a[i][y]==6)return false;
			if (a[i][y]!=0)break;
		}
		//sang phai
		for (i=y+1;i<9;i++){
			if (a[x][i]==6) return false;
			if (a[x][i]!=0) break;
		}
		//sang trai
		for (i=y-1;i>=0;i--){
			if (a[x][i]==6) return false;
			if (a[x][i]!=0) break;
		}
//---------------------------------------------------------------------------
		//kiem tra xem tuong co bi phao chieu khong
		//len tren
		dem=0;
		for (i=x-1;i>=0;i--){
			if (a[i][y]==5 && dem==1) return false;
			if (a[i][y]!=0) dem++;
			if (dem>1) break;
		}
		//xuong duoi
		dem=0;
		for (i=x+1;i<10;i++){
			if (a[i][y]==5 && dem==1)return false;
			if (a[i][y]!=0)dem++;
			if (dem>1)break;
		}
		//sang phai
		dem=0;
		for (i=y+1;i<9;i++){
			if (a[x][i]==5 && dem==1) return false;
			if (a[x][i]!=0)dem++;
			if (dem>1)break;
			
		}
		//sang trai
		dem=0;
		for (i=y-1;i>=0;i--){
			if (a[x][i]==5 && dem==1) return false;
			if (a[x][i]!=0)dem++;
			if (dem>1)break;
			
		}
//---------------------------------------------------------------------------------
		//kiem tra xem co bi tot chieu khong
			if (a[x-1][y]==1||a[x][y-1]==1||a[x][y+1]==1){
				return false;
			}
		return true;
	}
	public boolean kiemtraquandenchieutuong(int a[][]){
		int i,j;
		int x = 0,y = 0,dem,li;
		for (i=0;i<=9;i++)
			for (j=0;j<=8;j++)
				if (a[i][j]==7){
					x=i;
					y=j;
					break;
				}
		if (x==0 && y==0) return true;
//-----------------------------------------------------------------
		//kiem tra xem tuong co bi ma  do chieu khong
		int Tdx[] = {0,-1,1,2,2,1,-1,-2,-2} ;
		int Tdy[] = {0,-2,-2,-1,1,2,2,1,-1} ;
		int dx[] = {0,0,1,0,-1};
		int dy[] = {0,-1,0,1,0};
		for (int k=1;k<=8;k++){
			int x1=x+Tdx[k];
			int y1=y+Tdy[k];
			if (x1>=0 && y1>=0 &&x1<=9 &&y1<=8)
			if (a[x1][y1]==-4){
				if (x1<x && y1>y && a[x-1][y+1]==0)
					return false; 
				if (x1<x && y1<y && a[x-1][y-1]==0)
					return false;
				if (x1>x && y1>y && a[x+1][y+1]==0)
					return false;
				if (x1>x && y1<y && a[x+1][y-1]==0)
					return false;
				
			}
		}
//-----------------------------------------------------------------------------------------
		// kiem tra xem tuong co bi xe do chieu khong
		//len tren
		for (i=x-1;i>=0;i--){
			if (a[i][y]==-6)return false;
			if (a[i][y]!=0) break;
		}
		//xuong duoi
		for (i=x+1;i<10;i++){
			if (a[i][y]==-6)return false;
			if (a[i][y]!=0)break;
		}
		//sang phai
		for (i=y+1;i<9;i++){
			if (a[x][i]==-6) return false;
			if (a[x][i]!=0) break;
		}
		//sang trai
		for (i=y-1;i>=0;i--){
			if (a[x][i]==-6) return false;
			if (a[x][i]!=0) break;
		}
//---------------------------------------------------------------------------
		//kiem tra xem tuong co bi phao chieu khong
		//len tren
		dem=0;
		for (i=x-1;i>=0;i--){
			if (a[i][y]==-5 && dem==1) return false;
			if (a[i][y]!=0) dem++;
			if (dem>1) break;
		}
		//xuong duoi
		dem=0;
		for (i=x+1;i<10;i++){
			if (a[i][y]==-5 && dem==1)return false;
			if (a[i][y]!=0)dem++;
			if (dem>1)break;
		}
		//sang phai
		dem=0;
		for (i=y+1;i<9;i++){
			if (a[x][i]==-5 && dem==1) return false;
			if (a[x][i]!=0)dem++;
			if (dem>1)break;
			
		}
		//sang trai
		dem=0;
		for (i=y-1;i>=0;i--){
			if (a[x][i]==-5 && dem==1) return false;
			if (a[x][i]!=0)dem++;
			if (dem>1)break;
			
		}
//---------------------------------------------------------------------------------
		//kiem tra xem co bi tot chieu khong
			if (a[x+1][y]==-1||a[x][y+1]==-1||a[x][y-1]==-1){
				return false;
			}
		return true;
	}

}
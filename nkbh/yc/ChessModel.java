package nkbh.yc;

import java.util.LinkedList;

public class ChessModel {
	private ChessModel(){}
		private static ChessModel instance = new ChessModel();
			public static ChessModel getInstance(){
				return instance;
			}
		
	
	public static final int WIDTH = 19;
	public static final int WHITE = 1;
	public static final int BLACK = -1;
	public static final int SPACE = 0;
	 int[][] data = new int[WIDTH][WIDTH];
	
	@SuppressWarnings("unused")
	private int lastRow,lastColumn,lastColor;
	public boolean putChess(int row,int column,int color){
		//如果行列都是合法值
		if(row>=0&&row<WIDTH&&column>=0&&column<WIDTH){
			//如果颜色是BLACK或WHITE
			if(color==BLACK||color==WHITE){
				//如果当前点没有棋子
				if(data[row][column]==SPACE){
					data[row][column] = color;
					lastRow = row;
					lastColumn = column;
					lastColor = color;
					return true;
					
				}
			}
		}
		return false;
	}
	public boolean backChess(int row,int column,int color){
		//如果行列都是合法值
		if(row>=0&&row<WIDTH&&column>=0&&column<WIDTH){
			//如果颜色是BLACK或WHITE
			if(color==BLACK||color==WHITE){
				data[row][column]=SPACE;
				//如果当前点没有棋子
				if(data[row][column]==SPACE){
					return true;
					}
				}
			}
				return false;
	}
	
	public int getLastColor() {
		return lastColor;
	}
	
	public int getLastRow() {
		return lastRow;
	}

	public int getLastColumn() {
		return lastColumn;
	}

	public int getChess(int row,int column){
//		如果行列都是合法值
		if(row>=0&&row<WIDTH&&column>=0&&column<WIDTH){
			return data[row][column];
		}
		
		return SPACE;
	}
	 private int count(int lastRow,int lastColumn,int dx,int dy,int lastColor){
		  int i=0;
		  this.lastRow=lastRow;
		  this.lastColumn=lastColumn;
		  this.lastColor=lastColor;
		  for(;data[lastRow+(i+1)*dx][lastColumn+(i+1)*dy]==lastColor;i++){ }
		  return i;
	  }
	  public int whoWin(){
		  if(count(lastRow,lastColumn,1,0,lastColor)+count(lastRow,lastColumn,-1,0,lastColor)==4)
		  {return lastColor;}
		  else if(count(lastRow,lastColumn,0,1,lastColor)+count(lastRow,lastColumn,0,-1,lastColor)==4)
		  {return lastColor;}
		  else if(count(lastRow,lastColumn,-1,-1,lastColor)+count(lastRow,lastColumn,1,1,lastColor)==4)
		  {return lastColor;}
		  else if(count(lastRow,lastColumn,1,-1,lastColor)+count(lastRow,lastColumn,-1,1,lastColor)==4)
		  {return lastColor;}
		  else lastColor=0;
		  return SPACE;	  
	  }	
}

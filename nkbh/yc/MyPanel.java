package nkbh.yc;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class MyPanel extends JPanel {
	int sx=20,sy=20;
	int unit=30;
	private int c;
	 ImageIcon icon; 
	    Image image;

	
	private static MyPanel instance = new MyPanel();
	public static MyPanel getInstance(){
		return instance;
	}

	private MyPanel(){
		addMouseListener(new MouseAdapter(){
			@Override
			public void mouseReleased(MouseEvent e) {
				int x=e.getX();
				int y =e.getY();
				int column = (x-sx)/unit;
				if((x-sx)%unit>(0.5*unit)){
					column++;
				}
				int row = (y-sy)/unit;
				if((y-sy)%unit>(0.5*unit)){
					row++;
				}
				System.out.println(row+":"+column);	
				ChessControl.getInstance().putChess(row,column);
			}
		});
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		icon = new ImageIcon("±³¾°.jpg");
		 g.drawImage(icon.getImage(), -50, -50, null);
		drawPanel(g);
		drawChess(g);
		
	}
	
	private void drawChess(Graphics g) {
		for(int row =0 ;row<ChessModel.WIDTH;row++){
			for(int column = 0;column<ChessModel.WIDTH;column++){
				int color =ChessModel.getInstance().getChess(row,column);
				if(color== ChessModel.BLACK){
					paintChess(row,column,ChessModel.BLACK,g);
					
				}else if (color==ChessModel.WHITE){
					paintChess(row,column,ChessModel.WHITE,g);
				}
			}
		}
		
	}

	private void paintChess(int row, int column, int color, Graphics g) {
		Color old =g.getColor();
		
		int y = sy + row * unit - unit/2;
		int x = sx + column*unit - unit/2;
		g.setColor(Color.darkGray);
		g.fillOval(x+3, y+3, unit, unit);
		if (color==ChessModel.BLACK){
			g.setColor(Color.BLACK);
		}else if(color == ChessModel.WHITE){
			g.setColor(Color.WHITE);
		}
		g.fillOval(x, y, unit, unit);
		g.setColor(Color.lightGray);
		g.fillOval(x+10, y+10, 3, 3);
		g.setColor(old);
		
	}

	private void drawPanel(Graphics g) {
		for (int i = 1; i <ChessModel.WIDTH; i++) 
			g.drawString("" + (i + 1), ChessModel.WIDTH -15 , ChessModel.WIDTH + unit * i+5 );
		for (int i = 0; i <ChessModel.WIDTH; i++) 
			g.drawString("" + (i + 1), ChessModel.WIDTH + unit * i-5, ChessModel.WIDTH );	
		for(int i =0;i<ChessModel.WIDTH;i++){
			g.drawLine(sx, sy+i*unit, sx+(ChessModel.WIDTH-1)*unit, sy+i*unit);
			g.drawLine(sx+i*unit, sy, sx+i*unit, sy+(ChessModel.WIDTH-1)*unit);			
	}
	}
}

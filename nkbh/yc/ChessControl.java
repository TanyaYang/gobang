package nkbh.yc;

import javax.swing.JOptionPane;

public class ChessControl {
    private static ChessControl instance = new ChessControl();
    public static ChessControl getInstance(){
    	return instance;
    	
    }
	private ChessControl() {
		
	}
	private int localColor = ChessModel.BLACK;
	private int netColor;
	private Network net  ;
	private boolean allow = true;
	public void putChess(int row,int column ){
		if(!allow) return;
		ChessModel m = ChessModel.getInstance();
		MyPanel v = MyPanel.getInstance();
		boolean success = m.putChess(row, column, localColor);
		if(success){
//			localColor = -localColor;
			allow = false;
			v.repaint();
			net.send("ÏÂÆå:"+row+","+column);
			int winner = m.whoWin();
			if(winner == ChessModel.BLACK){
				JOptionPane.showMessageDialog(null, "ºÚÆåÊ¤Àû£¡ÓÎÏ·½áÊø£¡");
			}else if(winner== ChessModel.WHITE){
				JOptionPane.showMessageDialog(null, "°×ÆåÊ¤Àû£¡ÓÎÏ·½áÊø£¡");
				System.exit(0);
			}
		}
	}
	public void backChess(){
		if(allow) return;
		int row=ChessModel.getInstance().getLastRow();
		int column=ChessModel.getInstance().getLastColumn();
//		int lc=ChessModel.getInstance().getLastColor();	
		ChessModel m = ChessModel.getInstance();
		MyPanel v = MyPanel.getInstance();
		boolean success = m.backChess(row, column,localColor);	
		if(success){
//			localColor = -localColor;
			allow = true;
			v.repaint();
			net.send("»ÚÆå:"+row+","+column);
		}
	}
	
	
	public void receiveMsg(String msg) {
		if(msg.startsWith("ÏÂÆå:")){
			networkPutChess(msg);
		}else if(msg.startsWith("»ÚÆå:")){
			networkBackChess(msg);
		}else if(msg.startsWith("ÈÏÊä:")){
//			pushGiveUp(msg);
		}
		
	}
	
	public void pushServerButton() {
		localColor = ChessModel.BLACK;
		netColor = ChessModel.WHITE;
		allow = true;
		net = new Network();
		net.startServer();
	}
	public void pushClientButton(String ip) {
		localColor = ChessModel.WHITE;
		netColor = ChessModel.BLACK;
		allow = false;
		net = new Network();
		net.connectServer(ip);
	}

	private void networkPutChess(String msg) {
		String s = msg.substring("ÏÂÆå:".length());//3,5
		int index = s.indexOf(',');
		String first = s.substring(0, index);
		String second = s.substring(index+1);
		int row = Integer.parseInt(first);
		int column = Integer.parseInt(second);
		ChessModel m = ChessModel.getInstance();
		MyPanel v = MyPanel.getInstance();
		boolean success = m.putChess(row, column, netColor);
		if(success){
			v.repaint();
			allow = true;
			int winner = m.whoWin();
			if(winner == ChessModel.BLACK){
				JOptionPane.showMessageDialog(null, "ºÚÆå»ñÊ¤£¡ÓÎÏ·½áÊø£¡");
			}else if(winner== ChessModel.WHITE){
				JOptionPane.showMessageDialog(null, "°×Æå»ñÊ¤£¡ÓÎÏ·½áÊø£¡");
				System.exit(0);
			}
		}	
	}
	private void networkBackChess(String msg) {
		String s = msg.substring("»ÚÆå:".length());//3,5
		int index = s.indexOf(',');
		String first = s.substring(0, index);
		String second = s.substring(index+1);
		ChessModel m = ChessModel.getInstance();
		MyPanel v = MyPanel.getInstance();
		int row = Integer.parseInt(first);
		int column = Integer.parseInt(second);
		boolean success = m.backChess(row, column,netColor);		
		if(success){
			v.repaint();
			allow = false;
		}	
	}
}

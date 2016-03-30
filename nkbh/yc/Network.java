package nkbh.yc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

public class Network {

	public final static int PORT = 7777;
	protected Socket socket;
	protected BufferedReader in;
	protected PrintWriter out;
	private boolean allow = true;
	//服务器从这个函数开始
	public void startServer(){
		try {
			System.out.println("开始监听");
			ServerSocket server = new ServerSocket(PORT);
			socket = server.accept();
			System.out.println("连接建立");
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(),true);
			startReadThread();
		} catch (IOException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}

	}
	//客户端从这个函数开始
	public void connectServer(String ip){
		try {
			socket = new Socket(ip,PORT);
			System.out.println("连接到服务");
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(),true);
			startReadThread();
		} catch (UnknownHostException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
	}
	
	protected void startReadThread() {
		Thread t = new Thread(){
			@Override
			public void run() {
				while(true){
					try {
						String  line = in.readLine();
						System.out.println(line);
						ChessControl.getInstance().receiveMsg(line);
					} catch (IOException e) {
						// TODO 自动生成 catch 块
						e.printStackTrace();
						return;
					}
				}
			}
		};
		t.setDaemon(true);
		t.start();
		
	}
	
	
	public void send(String msg){
		out.println(msg);
	}
}

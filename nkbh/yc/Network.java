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
	//�����������������ʼ
	public void startServer(){
		try {
			System.out.println("��ʼ����");
			ServerSocket server = new ServerSocket(PORT);
			socket = server.accept();
			System.out.println("���ӽ���");
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(),true);
			startReadThread();
		} catch (IOException e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}

	}
	//�ͻ��˴����������ʼ
	public void connectServer(String ip){
		try {
			socket = new Socket(ip,PORT);
			System.out.println("���ӵ�����");
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(),true);
			startReadThread();
		} catch (UnknownHostException e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		} catch (IOException e) {
			// TODO �Զ����� catch ��
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
						// TODO �Զ����� catch ��
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

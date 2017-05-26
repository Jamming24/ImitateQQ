package com.NetServer;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import com.UI.WinUtil;

public class WhatsAppClient {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	private Socket socket;
	public Socket getSocket() {
		return socket;
	}
	
	
	public WhatsAppClient(String Username,String ServerIP){
		Socket s;
		try {
			s = new Socket(ServerIP,10000);
			OutputStream out=s.getOutputStream();
			PrintWriter pw=new PrintWriter(out,true);
			String HostName=WinUtil.getInstance().getHostName();
			String IP=WinUtil.getInstance().getIP();
			pw.println(Username+"@"+HostName+"@"+IP);
			this.socket=s;
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	public void SendMessage(Socket socket ,String Message){
//		OutputStream send;
//		try {
//			send = socket.getOutputStream();
//			PrintWriter pw=new PrintWriter(send,true);
//			pw.println(Message);
//			
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
	public static void main(String[] args) {
		new WhatsAppClient("gaojiaming", "127.0.0.1");
	}

}

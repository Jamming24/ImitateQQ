package com.NetServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.TreeMap;

import com.ServerLog.WriterContactList;


public class ServerThread extends Thread {

	/**
	 * @param args
	 */
	private Socket socket;
	private String userName;
	
	public ServerThread(String userName,Socket socket){
		this.socket=socket;
		this.userName=userName;
	}
	
	public void run(){
		try {
			while(true){
				InputStream receiveData= socket.getInputStream();
				BufferedReader br=new BufferedReader(new InputStreamReader(receiveData));
				String receiveMessage=br.readLine();
				System.out.println("来自"+userName+"的消息:"+receiveMessage);
				socket.getOutputStream();
				
				
				
				String[] Messages=receiveMessage.split("@");
				UserInformation usi=WhatsAppServer.information.get(Messages[1]);
				
				Socket friendSocket=usi.getSocket();
				OutputStream respon=friendSocket.getOutputStream();
				PrintWriter pw=new PrintWriter(respon,true);
				pw.println(userName+":"+Messages[0]);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			TreeMap<String,String> refreshList=WhatsAppServer.Contact;
			refreshList.remove(userName);
			WriterContactList w=new WriterContactList();
			w.saveList(refreshList);
			w.saveProperties(refreshList);
			System.out.println(userName+"掉线了");
		}
		
	}

}

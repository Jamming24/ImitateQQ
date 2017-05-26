package com.NetServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.TreeMap;

import com.ServerLog.WriterContactList;


public class WhatsAppServer extends Thread{

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static HashMap<String, UserInformation> information=new HashMap<String, UserInformation>();;
	public static TreeMap<String,String> Contact=new TreeMap<String, String>();
	private ServerSocket server;
	private int port;
	public WhatsAppServer(int port) {
		this.port=port;
	}
	public void run(){
		try {
			server = new ServerSocket(port);
			System.out.println("������ѿ���!");
			WriterContactList w=new WriterContactList();
			while (true){
				System.out.println("����˵ȴ�����");
				Socket s=server.accept();
				InputStream in=s.getInputStream();
				BufferedReader br=new BufferedReader(new InputStreamReader(in));
				String Receiveinfomtions =br.readLine();
				String[] infos=Receiveinfomtions.split("@");
				System.out.println(infos[0] +"���ӳɹ�!!!!");//infos[0]:�û���,infos[1]:������; infos[2]:IP��ַ
				Contact.put(infos[0], infos[2]+"  "+infos[1]);
				w.saveList(Contact);
				w.saveProperties(Contact);
				UserInformation Information=new UserInformation(infos[1],s, infos[2]);
				information.put(infos[0], Information);
				ServerThread sThread= new ServerThread(infos[0],s);
				sThread.start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//			e.printStackTrace();
			System.out.println("�ͻ��˶Ͽ�����");
		}


	}
	public void closeServer(){
		try {
			server.close();
			System.out.println("������ѹر�");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new WhatsAppServer(10000);
		//����˿ں�
	}

}

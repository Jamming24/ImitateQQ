package com.UI;


import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class RadioUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * @param args
	 */

	private DatagramSocket socket;  
	private DatagramPacket packet;
	private JPanel buttonArea;
	private JTextArea Outext;
	private JTextArea Intext;
	private JButton close,send;
	private JLabel titel;
	private JScrollPane scrollPane ;
	private String Username ;
	private receiveRadio r;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new RadioUI("gaojiaming");


	}
	public RadioUI(String userName){
		r=new receiveRadio(10004);
		r.start();
		this.Username=userName;
		titel=new JLabel("Ⱥ�Ự");
		titel.setBounds(5, 5, 600, 30);
		titel.setFont(new Font("΢���ź�",Font.BOLD,20) );

		buttonArea=new JPanel();
		buttonArea.setBounds(300, 500, 100, 100);
		Outext=new JTextArea("");

		Outext.setEditable(false);
		Outext.setFont(new Font("΢���ź�",Font.BOLD,20));
		scrollPane = new JScrollPane(Outext);
		scrollPane.setBounds(0, 40, 600, 310);
		Intext=new JTextArea(10, 10);
		Intext.setFont(new Font("΢���ź�",Font.BOLD,20));
		Intext.setBounds(0, 360, 600, 160);

		close=new JButton("�ر�");
		close.setBounds(500, 525, 70, 30);
		close.setFont(new Font("΢���ź�", Font.BOLD, 18));
		send=new JButton("����");
		send.setBounds(380, 525, 70, 30);
		send.setFont(new Font("΢���ź�", Font.BOLD, 18));

		add(titel);
		add(scrollPane);
		add(Intext);
		add(close);
		add(send);
		setIconImage(Toolkit.getDefaultToolkit().createImage("data\\icon2.jpg"));
		setLayout(null);
		setSize(600,600);
		Point p=WinUtil.getInstance().getScreenCenterPoint(600, 600);
		setLocation((int)p.getX(), (int)p.getY());
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ImageIcon bg = new ImageIcon("data//bg6.jpg");
		JLabel imgLabel = new JLabel(bg);
		getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));// ע�������ǹؼ�����������ǩ��ӵ�jfram��LayeredPane����
		imgLabel.setBounds(0, 0, bg.getIconWidth(), bg.getIconHeight());// ���ñ�����ǩ��λ��
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());
		((JPanel) cp).setOpaque(false);
		ActionListen();
	}
	public void ActionListen(){
		send.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					String Info=Username+":"+Intext.getText();
					byte[] data=Info.getBytes();  
					socket = new DatagramSocket();  
					socket.setBroadcast(true); //��û��ûɶ��ͬ  
					//send��ָ�����ܶ˵Ķ˿ڣ��Լ��Ķ˿��������  
					packet = new DatagramPacket(data,data.length,InetAddress.getByName("255.255.255.255"),10004);  
					socket.send(packet);  
					Intext.setText("");
				} catch (UnknownHostException e1) {
					System.out.println("δ֪�����쳣");
				} catch (IOException e1) {
					System.out.println("�㲥��Ϣ����ʧ��!!!!!!");
				}

			}
		});
		Intext.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e){
				if(e.isControlDown()&&e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					try {
						String Info=Username+":"+Intext.getText();
						byte[] data=Info.getBytes();  
						socket = new DatagramSocket();  
						socket.setBroadcast(true); //��û��ûɶ��ͬ  
						//send��ָ�����ܶ˵Ķ˿ڣ��Լ��Ķ˿��������  
						packet = new DatagramPacket(data,data.length,InetAddress.getByName("255.255.255.255"),10004);  
						socket.send(packet);  
						Intext.setText("");
					} catch (UnknownHostException e1) {
						System.out.println("δ֪�����쳣");
					} catch (IOException e1) {
						System.out.println("�㲥��Ϣ����ʧ��!!!!!!");
					}

				}
			}
		});
		close.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				r.CloseRadio();
				r.interrupt();
				dispose();
				System.out.println("�㲥����ر�");
			}
		});
	}



	public class receiveRadio extends Thread {

		private int port;
		private DatagramSocket server; 
		public receiveRadio(int port){
			System.out.println("�㲥��������");
			this.port=port; 
		}
		public void CloseRadio(){
			server.close();
		}

		public void run() {
			byte[] buffer=new byte[65507]; 
			try {
				server = new DatagramSocket(port);  
				DatagramPacket packet = new DatagramPacket(buffer , buffer.length);  
				while(true){
					server.receive(packet);  
					String information = new String(packet.getData( ), 0, packet.getLength( ));  
					System.out.println("������յ���Ϣ:"+information);
					Outext.append(WinUtil.getInstance().getCurrentime());
					Outext.append(information+"\n");
				}
			}
			catch (IOException e) {
				System.out.println("�����쳣");
			}
		}
	}
}



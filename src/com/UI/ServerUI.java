package com.UI;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.NetServer.WhatsAppServer;


public class ServerUI  extends MyFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * @param args
	 */
	private JPanel panel;
	private JMenuBar mb;
	private JMenu startMenu;
	private JMenuItem setPort,start,close,Exit;
	private JButton refresh,radio;
	private Vector<String> data;
	private WhatsAppServer s;
	private JList list;
	private JTextArea text;
	private DatagramSocket socket;  
	private DatagramPacket packet;
	private JScrollPane scrollPane;
	public ServerUI(){
		setLayout(null);
		Init(400, 600);
		MyInit();

	}
	public void MyInit(){
		try {
			new FileWriter("ContactList.txt");
			new FileWriter("pass.properties");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mb=new JMenuBar();
		startMenu=new JMenu("开始");
		startMenu.setFont(new Font("微软雅黑",Font.BOLD,16));
		setPort=new JMenuItem("设置端口号");
		start=new JMenuItem("启动服务器");
		close=new JMenuItem("关闭服务器");
		Exit=new JMenuItem("退出程序");
		startMenu.add(setPort);
		startMenu.add(start);
		startMenu.add(close);
		startMenu.add(Exit);
		mb.add(startMenu);
		setJMenuBar(mb);

		panel=new JPanel();
		panel.setBounds(0, 6, 400, 600);
		panel.setOpaque(false);
		panel.setLayout(null);

		JLabel p=new JLabel("在线成员");
		String m=WinUtil.getInstance().getIP();
		p.setText("在线成员       服务器IP:"+m);
		p.setBounds(4, 0, 385,30);
		p.setFont(new Font("微软雅黑",Font.BOLD,18));

		data=new Vector<String>();

		list=new JList();
//		list.setLocation(4, 30);
//		list.setSize(385, 160);
		list.setFont(new Font("微软雅黑",Font.BOLD,18));
		list.setListData(data);
		scrollPane = new JScrollPane(list);
		scrollPane.setBounds(4, 30, 385, 160);
		


		text=new JTextArea("请输入广播消息.....");
		text.setBounds(4, 210, 385,270);
		text.setFont(new Font("微软雅黑",Font.BOLD,15));

		refresh=new JButton("刷新列表");
		refresh.setLocation(150, 490);
		refresh.setSize(100, 35);
		refresh.setFont(new Font("微软雅黑",Font.BOLD,15));


		radio=new JButton("发送广播");
		radio.setLocation(270, 490);
		radio.setSize(100, 35);
		radio.setFont(new Font("微软雅黑",Font.BOLD,15));

		panel.add(p);
		panel.add(text);
		panel.add(scrollPane);
		panel.add(refresh);
		panel.add(radio);
		add(panel);
		ButtonListener();
		ImageIcon bg=new ImageIcon("data\\bg6_small.jpg");
		JLabel imgLabel = new JLabel(bg);
		getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));//注意这里是关键，将背景标签添加到jfram的LayeredPane面板里。
		imgLabel.setBounds(0,0,bg.getIconWidth(), bg.getIconHeight());//设置背景标签的位置
		Container cp=getContentPane();
		cp.setLayout(new BorderLayout());
		((JPanel)cp).setOpaque(false); //注意这里，将内容面板设为透明。这样LayeredPane面板中的背景才能显示出来。

	}


	public void ButtonListener(){
		close.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				s.closeServer();
			}
		});
		start.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				s=new WhatsAppServer(10000);
				s.start();
			}

		});

		Exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		refresh.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//				HashMap<String ,UserInformation> temp=WhatsAppServer.information;
				//				int num=temp.size();
				//				if(currrntNum!=num){
				//					data.clear();
				//					Set<String> keys=temp.keySet();
				//					for(String k:keys){
				//						data.addElement(k+"  用户上线");
				//					}
				//					list.updateUI();
				//					currrntNum=num;
				//				}

				try {
					Scanner sc=new Scanner(new FileReader("ContactList.txt"));
					data.clear();
					while(true){
						String[] temps=sc.nextLine().split("@");
						data.addElement(temps[0]+"  用户上线");
						list.updateUI();
					}

				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					//					e1.printStackTrace();
					System.out.println("配置文件丢失");
				}
			}
		});

		radio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					String Info="服务器:"+text.getText();
					byte[] data=Info.getBytes();  
					socket = new DatagramSocket();  
					socket.setBroadcast(true); //有没有没啥不同  
					//send端指定接受端的端口，自己的端口是随机的  
					packet = new DatagramPacket(data,data.length,InetAddress.getByName("255.255.255.255"),10004);  
					socket.send(packet);  
					text.setText("");
				} catch (UnknownHostException e1) {
					System.out.println("未知主机异常");
				} catch (IOException e1) {
					System.out.println("广播消息发送失败!!!!!!");
				}

			}
		});


}




public static void main(String[] args) {
	// TODO Auto-generated method stub
	new ServerUI();

}

}

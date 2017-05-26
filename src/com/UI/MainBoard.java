package com.UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.NetServer.WhatsAppClient;


public class MainBoard extends JFrame {

	private static final long serialVersionUID = 1L;

	public  JPanel topanel,choosePanel,listPanel,blankPanle; 
	private int FrameWidth=320;
	private JButton refresh;
	private JButton title;
	private JScrollPane scrollPane;
	private JList list;
	private String MyName,myIP;
	private Socket mysocket;
	private static Vector<String> Contactlist;
	private static ArrayList<String> ContactIpHost;
	public MainBoard(int width,int heigh,String userName,String ServerIP){
		mysocket=new WhatsAppClient(userName, ServerIP).getSocket();
		setIconImage(Toolkit.getDefaultToolkit().createImage("data\\icon2.jpg"));
		setLayout(null);
		setSize(width,heigh);
		setLocation(500, 200);
		setVisible(true);
//		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.MyName=userName;
		setTitle("WhatsApp即时通讯");
		MyInit(MyName);
		ClickListener();
	}
	
	private void MyInit(String MyName){
		//初始化位于顶部的Panel 
		topanel=new JPanel();
		topanel.setLayout(null);
		topanel.setLocation(0,0);
		topanel.setBackground(Color.cyan);
		topanel.setSize(FrameWidth,120);
		add(topanel);
		ImageIcon picture=new ImageIcon("data//2294369245.jpg");
		picture.setDescription("头像");
		JLabel photo=new JLabel(picture);
		photo.setLocation(5, 5);
		photo.setSize(100, 100);
		topanel.add(photo);

		JLabel name =new JLabel();
		name.setText(MyName);
		name.setLocation(120, 15);
		name.setSize(120,25);
		name.setFont(new Font("微软雅黑",Font.BOLD,20));
		topanel.add(name);

		myIP=WinUtil.getInstance().getIP();
		JLabel sign=new JLabel();
		sign.setText("IP:"+myIP);
		sign.setLocation(118, 55);
		sign.setSize(180,25);
		sign.setFont(new Font("微软雅黑",Font.BOLD,16));
		topanel.add(sign);
		
		choosePanel=new JPanel();
		choosePanel.setLayout(null);
		choosePanel.setLocation(0,120);
		choosePanel.setSize(FrameWidth,60);
		JLabel jp=new JLabel();
		jp.setIcon(new   ImageIcon("data/icon3.jpg"));
		jp.setBounds(0, 0, 120, 60);
		title=new JButton("接收群消息");
		title.setFont(new Font("微软雅黑",Font.BOLD,15));
		title.setBounds(150, 10,120, 40);
		title.setOpaque(false);
		choosePanel.add(title);
		choosePanel.add(jp);
		add(choosePanel);
		listPanel=new JPanel();
		Contactlist=new Vector<String>();
		ContactIpHost=new ArrayList<String>();
		list=new JList();
		list.setListData(Contactlist);
		list.setCellRenderer(new CellRenderer(ContactIpHost));
		list.setFont(new Font("微软雅黑",Font.BOLD,20));
		list.setBounds(0, 0, FrameWidth, 385);
		scrollPane = new JScrollPane(list);
		scrollPane.setBounds(0, 0, FrameWidth-15, 385);
		listPanel.add(scrollPane);
		listPanel.setLayout(null);
		listPanel.setLocation(0,180);
		listPanel.setSize(FrameWidth-10,365);
		add(listPanel);
		
		blankPanle=new JPanel();
		blankPanle.setLayout(null);
		blankPanle.setLocation(0,545);
		blankPanle.setBackground(Color.gray);
		blankPanle.setSize(FrameWidth,70);

		refresh=new JButton("刷新在线联系人");
		refresh.setFont(new Font("微软雅黑",Font.BOLD,18));
		refresh.setBounds(50, 10, 180, 30);
		blankPanle.add(refresh);

		add(blankPanle);
	}
	
	private void ClickListener(){
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2){
					JList list = (JList)e.getSource();
					int Selectindex=list.getSelectedIndex();
//					System.out.println(Selectindex);
					new DialogFrame(600, 540, Contactlist.get(Selectindex), mysocket,MyName);
					
				}
			}
			
		});
		title.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new RadioUI(MyName);
				
			}
		});
		
		refresh.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//				new MainBoard(FrameWidth, 640,uName,Sign);
//				dispose();
				try {
					Scanner sc=new Scanner(new FileReader("ContactList.txt"));
					Contactlist.clear();
					while(true){
						String[] temps=sc.nextLine().split("@");
						if(temps[0].equals(MyName)){
							continue;
						}
						Contactlist.add(temps[0]);
						ContactIpHost.add(temps[1]);
						list.updateUI();
					}
					
				} catch (FileNotFoundException e1) {
//					e1.printStackTrace();
					System.out.println("配置文件丢失");
				}
				
				
//				HashMap<String ,UserInformation> temps=WhatsAppServer.information;
//				int num=Contactlist.size();
//				System.out.println(Contactlist.size());
//				if(currrntNum!=num){
//					Contactlist.clear();
//					Set<String> keys=temps.keySet();
//					for(String k:keys){
//						Contactlist.addElement(k);
//						}
//					list.updateUI();
//					currrntNum=num;
//				}
			}
		});
		
	}

}

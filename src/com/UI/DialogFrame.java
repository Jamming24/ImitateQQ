package com.UI;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class DialogFrame extends MyFrame{

	private static final long serialVersionUID = 1L;
	private static int width = 600;
	private static int higth = 600;
	private JTextArea Intext, Outext;
	private JPanel panel, MenuPanel;
	private JScrollPane jsp;
	private JButton send, close, reset, sendFile, setfont;
	private Socket mysocket;
	private String friendName, myName;
	public static void main(String[] args) {
		new DialogFrame(600, 540, "hahha", null,"as");
	}


	public DialogFrame(int width, int heigh, String userName, Socket socket,
			String myname){
		super.Init(width, heigh);
		this.mysocket = socket;
		this.friendName = userName;
		this.myName = myname;

		setTitle("与" + friendName + "对话框");
		MyInitUI();// 初始化对话框
		cilckListener();// 添加监听事件
		Thread receive = new receiveMessage(mysocket);// 开启接受消息的线程
		receive.start();

	}


	public void MyInitUI() {

		// 加载并设置控件位置
		setLayout(null);
		panel = new JPanel();
		panel.setOpaque(false);
		panel.setLayout(null);
		panel.setBounds(0, 70, width - 10, higth - 80);
		// 接收消息的文本域
		Outext = new JTextArea(4, 5);
		Outext.setLineWrap(true);
		Outext.setFont(new Font("微软雅黑", Font.BOLD, 20));
		Outext.setEditable(false);
		jsp = new JScrollPane(Outext);
		jsp.setBounds(5, 1, 585, 280);

		sendFile = new JButton("发送文件");
		sendFile.setBounds(0, 0, 100, 30);
		sendFile.setFont(new Font("微软雅黑", Font.BOLD, 15));
		sendFile.setOpaque(false);

		setfont = new JButton("保存聊天记录");
		setfont.setBounds(120, 0, 130, 30);
		setfont.setFont(new Font("微软雅黑", Font.BOLD, 15));
		setfont.setOpaque(false);

		MenuPanel = new JPanel();
		MenuPanel.setOpaque(false);
		MenuPanel.setLayout(null);
		MenuPanel.setBounds(5, 285, 585, 40);
		MenuPanel.add(sendFile);
		MenuPanel.add(setfont);
		// 输入要发送消息的文本域
		Intext = new JTextArea("请输入要发送的信息.....", 5, 10);
		Intext.setFont(new Font("微软雅黑", Font.BOLD, 20));
		Intext.setBounds(5, 320, 585, 120);
		Intext.setLineWrap(true);
		// 加载功能按钮
		send = new JButton("发送");
		send.setFont(new Font("微软雅黑", Font.BOLD, 18));
		send.setBounds(380, 450, 70, 30);
		close = new JButton("关闭");
		close.setFont(new Font("微软雅黑", Font.BOLD, 18));
		close.setBounds(500, 450, 70, 30);
		reset = new JButton("清空");
		reset.setFont(new Font("微软雅黑", Font.BOLD, 18));
		reset.setBounds(260, 450, 70, 30);

		panel.add(jsp);
		panel.add(MenuPanel);
		panel.add(Intext);
		panel.add(send);
		panel.add(close);
		panel.add(reset);
		// 设置背景图片
		ImageIcon bg = new ImageIcon("data//bg6.jpg");
		JLabel imgLabel = new JLabel(bg);
		getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));// 注意这里是关键，将背景标签添加到jfram的LayeredPane面板里。
		imgLabel.setBounds(0, 0, bg.getIconWidth(), bg.getIconHeight());// 设置背景标签的位置
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());
		((JPanel) cp).setOpaque(false);
		add(panel);
	}

	private void cilckListener() {

		send.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					String Message = Intext.getText();
					if (Message.equals("")) {
						JOptionPane.showMessageDialog(null, "发送信息不能为空");
					} else {
						OutputStream sendMessage = mysocket.getOutputStream();
						PrintWriter pw = new PrintWriter(sendMessage);
						pw.println(Message + "@" + friendName);
						pw.flush();
						Intext.setText("");
						Outext.append(WinUtil.getInstance().getCurrentime()
								+ "\n");
						Outext.append(myName + ":" + Message + "\n");
						Outext.setCaretPosition(Outext.getText().length());
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		close.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		reset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Intext.setText("");
			}
		});
		setfont.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String name = myName + "_" + friendName;
					System.out.println(name);
					FileWriter fw = new FileWriter(name + ".txt");
					fw.write(Outext.getText());
					fw.close();
					JOptionPane.showMessageDialog(null, "保存成功");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		Intext.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_ENTER) {
					try {
						String Message = Intext.getText();
						if (Message.equals("")) {
							JOptionPane.showMessageDialog(null, "发送信息不能为空");
						} else {
							OutputStream sendMessage = mysocket.getOutputStream();
							PrintWriter pw = new PrintWriter(sendMessage);
							pw.println(Message + "@" + friendName);
							pw.flush();
							Intext.setText("");
							Outext.append(WinUtil.getInstance().getCurrentime()
									+ "\n");
							Outext.append(myName + ":" + Message + "\n");
							Outext.setCaretPosition(Outext.getText().length());
						}
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}


				}
			}
		});
	}

	public class receiveMessage extends Thread {
		public Socket socket;

		public receiveMessage(Socket sock) {
			this.socket = sock;
		}

		public void run() {
			try {
				InputStream is = socket.getInputStream();
				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader br = new BufferedReader(isr);
				while (true) {
					String m = br.readLine();
					Outext.append(WinUtil.getInstance().getCurrentime() + "\n");
					Outext.append(m + "\n");
					Outext.setCaretPosition(Outext.getText().length());
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}


}

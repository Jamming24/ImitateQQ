package com.UI;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LoginFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int Loghight=400;
	public static final int Logwidth=300;


	public LoginFrame(){
		Init();
	}

	private void Init(){
		//��ʼ������
		Toolkit t=Toolkit.getDefaultToolkit();
		int w=(t.getScreenSize().width);
		int h=(t.getScreenSize().height);
		setSize(Loghight,Logwidth);
		setTitle("��¼����");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		ImageIcon bg=new ImageIcon("data//bg3.jpg");
		JLabel imgLabel = new JLabel(bg);

		getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));//ע�������ǹؼ�����������ǩ��ӵ�jfram��LayeredPane����
		imgLabel.setBounds(0,0,bg.getIconWidth(), bg.getIconHeight());//���ñ�����ǩ��λ��
		Container cp=getContentPane();
		cp.setLayout(new BorderLayout());

		((JPanel)cp).setOpaque(false); //ע����������������Ϊ͸��������LayeredPane����еı���������ʾ������


		setLocation((w-Loghight)/2, (h-Logwidth)/2);
		setLayout(null);
		setResizable(false);

		final JLabel title=new JLabel("��ӭʹ��WhatApps");
		title.setBounds(90, 10, 220, 60);
		title.setFont(new Font("΢���ź�",Font.BOLD,22));
		add(title);

		final JLabel luser=new JLabel("�û���");
		luser.setBounds(60, 80, 70, 40);
		luser.setFont(new Font("΢���ź�",Font.BOLD,20));
		add(luser);

		final JLabel lpass=new JLabel("������IP");
		lpass.setBounds(55, 120, 120, 40);
		lpass.setFont(new Font("΢���ź�",Font.BOLD,20));
		add(lpass);

		final JTextField user=new JTextField(10);
		user.setBounds(150, 80, 200, 35);
		user.setFont(new Font("΢���ź�",Font.BOLD,15));
		add(user);


		final JTextField IPtext=new JTextField(10);
		add(IPtext);
		IPtext.setLocation(150, 120);
		IPtext.setSize(200, 35);
		IPtext.setFont(new Font("΢���ź�",Font.BOLD,15));
		add(IPtext);


		JButton bt=new JButton("��¼");
		bt.setLocation(110, 190);
		bt.setSize(80, 40);
		bt.setFont(new Font("΢���ź�",Font.BOLD,19));
		bt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String usertext=user.getText();
				String IP=IPtext.getText();
				if(usertext.equals("")||IP.equals("")){
					JOptionPane.showMessageDialog(null, "�������û�����IP��ַ");
				}else{
					Properties p=WinUtil.getInstance().getInformation("pass.properties");
					if(p.containsKey(usertext)){
						JOptionPane.showMessageDialog(null, "�û����Ѿ�����,����������!!!");
						user.setText("");
					}else{
						String res=WinUtil.getInstance().ipCheck(IP);
						if(res.equals("pass")){
							JOptionPane.showMessageDialog(null, "��ϲ��,��½�ɹ�!!!!!");
							dispose();
							new MainBoard(320, 640, usertext,IP);
						}else if(res.equals("fail")){
							JOptionPane.showMessageDialog(null, "�������IP��ַ���Ϸ�,����������!!!");
							IPtext.setText("");
						}else{
							JOptionPane.showMessageDialog(null, "������IP��ַ!!!!");
						}
					}
				}}
		});

		
		add(bt);

		JButton reset=new JButton("����");
		reset.setLocation(230, 190);
		reset.setSize(80, 40);
		reset.setFont(new Font("΢���ź�",Font.BOLD,19));
		reset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				user.setText("");
				IPtext.setText("");
			}
		});
		add(reset);
		setVisible(true);

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new LoginFrame();

	}



}

package com.UI;

import java.awt.Point;
import java.awt.Toolkit;
import javax.swing.JFrame;

public class MyFrame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void Init(int width,int heigh){
		setIconImage(Toolkit.getDefaultToolkit().createImage("data\\icon2.jpg"));
		setLayout(null);
		setSize(width,heigh);
		Point p=WinUtil.getInstance().getScreenCenterPoint(width, heigh);
		setLocation((int)p.getX(), (int)p.getY());
		setVisible(true);
//		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

}

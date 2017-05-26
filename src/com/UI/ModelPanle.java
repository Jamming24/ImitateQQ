package com.UI;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ModelPanle extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param args
	 */
	public ModelPanle(String userName,String personSign){
		setLayout(null);
		JLabel name=new JLabel(userName);
		setBounds(0, 0, 315, 60);
		setBackground(Color.red);
		name.setLocation(5, 5);
		name.setSize(120,20);
		name.setText(userName);
		name.setFont(new Font("Î¢ÈíÑÅºÚ",Font.BOLD,18));
		
		JLabel sign=new JLabel();
		sign.setLocation(5, 30);
		sign.setSize(180,20);
		sign.setText(personSign);
		sign.setFont(new Font("Î¢ÈíÑÅºÚ",Font.BOLD,18));
		add(name);
		add(sign);
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame f=new JFrame();
		JPanel j=new ModelPanle("ºúºàç÷", "I am a doubi");
		f.setLayout(null);
		f.add(j);
		f.setBounds(100, 500, 500, 500);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);

	}

}

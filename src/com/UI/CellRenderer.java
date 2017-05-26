package com.UI;
import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

class   CellRenderer   extends   JLabel   implements   ListCellRenderer   
{   
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/*类CellRenderer继承JLabel并实作ListCellRenderer.由于我们利用JLabel易于插图的特性
	 * ，因此CellRenderer继承了JLabel   
	 *可让JList中的每个项目都视为是一个JLabel.   
	 */   
	private JLabel IPhost;
	private JLabel Username;
	private ArrayList<String> IPhostInformation;
	
	CellRenderer(ArrayList<String> IPhostArrayList)   
	{   
		setOpaque(true); 
		setLayout(null);
		this.IPhostInformation=IPhostArrayList;
		Username=new JLabel();
		IPhost=new JLabel();
		add(Username); 
		add(IPhost);
	}   
	/*从这里到结束：实作getListCellRendererComponent()方法*/   
//	public void LoadInformation(){
//		IPhostInformation=new ArrayList<String>();
//		IPhostInformation.add("192.168.1.1  Administration");
//		IPhostInformation.add("192.168.1.2  Administration");
//		IPhostInformation.add("192.168.1.3  Administration");
//		IPhostInformation.add("192.168.1.4  Administration");
//		IPhostInformation.add("192.168.1.5  Administration");
//		IPhostInformation.add("192.168.1.6  Administration");
//		IPhostInformation.add("192.168.1.7  Administration");
//	}
	
	
	public   Component   getListCellRendererComponent(JList   list,   
			Object   value,   
			int   index,   
			boolean   isSelected,   
			boolean   cellHasFocus)   
	{         
		/*我们判断list.getModel().getElementAt(index)所返回的值是否为null,例如上个例子中，若JList的标题是"你玩过哪   
		 *些数据库软件"，则index>=4的项目值我们全都设为null.而在这个例子中，因为不会有null值，因此有没有加上这个判   
		 *断并没有关系.   
		 */   
		if   (value   !=   null)   
		{   
//			setText(value.toString());  
//			setIconTextGap(10);
			setIcon(new   ImageIcon("data/"+(index+1)+".jpg"));
			
			Username.setText(value.toString());
			Username.setFont(new Font("微软雅黑",Font.BOLD,18));
			Username.setBounds(75, 0, 150, 35);
			
			IPhost.setBounds(75, 25, 300, 50);
			IPhost.setFont(new Font("微软雅黑",Font.BOLD,12));
			IPhost.setText(IPhostInformation.get(index));
			
		}   
		if   (isSelected)   {   
			setBackground(list.getSelectionBackground());   
			setForeground(list.getSelectionForeground());
			
		}   
		else   {   
			//设置选取与取消选取的前景与背景颜色.   
			setBackground(list.getBackground());   
			setForeground(list.getForeground());   
		}   
		return   this;   
	}           
}
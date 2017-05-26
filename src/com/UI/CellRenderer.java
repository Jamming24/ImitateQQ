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
	
	/*��CellRenderer�̳�JLabel��ʵ��ListCellRenderer.������������JLabel���ڲ�ͼ������
	 * �����CellRenderer�̳���JLabel   
	 *����JList�е�ÿ����Ŀ����Ϊ��һ��JLabel.   
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
	/*�����ﵽ������ʵ��getListCellRendererComponent()����*/   
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
		/*�����ж�list.getModel().getElementAt(index)�����ص�ֵ�Ƿ�Ϊnull,�����ϸ������У���JList�ı�����"�������   
		 *Щ���ݿ����"����index>=4����Ŀֵ����ȫ����Ϊnull.������������У���Ϊ������nullֵ�������û�м��������   
		 *�ϲ�û�й�ϵ.   
		 */   
		if   (value   !=   null)   
		{   
//			setText(value.toString());  
//			setIconTextGap(10);
			setIcon(new   ImageIcon("data/"+(index+1)+".jpg"));
			
			Username.setText(value.toString());
			Username.setFont(new Font("΢���ź�",Font.BOLD,18));
			Username.setBounds(75, 0, 150, 35);
			
			IPhost.setBounds(75, 25, 300, 50);
			IPhost.setFont(new Font("΢���ź�",Font.BOLD,12));
			IPhost.setText(IPhostInformation.get(index));
			
		}   
		if   (isSelected)   {   
			setBackground(list.getSelectionBackground());   
			setForeground(list.getSelectionForeground());
			
		}   
		else   {   
			//����ѡȡ��ȡ��ѡȡ��ǰ���뱳����ɫ.   
			setBackground(list.getBackground());   
			setForeground(list.getForeground());   
		}   
		return   this;   
	}           
}
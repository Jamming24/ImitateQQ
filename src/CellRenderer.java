import java.awt.Color;
import java.awt.Component;
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
	JLabel sign;
	/*��CellRenderer�̳�JLabel��ʵ��ListCellRenderer.������������JLabel���ڲ�ͼ������
	 * �����CellRenderer�̳���JLabel   
	 *����JList�е�ÿ����Ŀ����Ϊ��һ��JLabel.   
	 */   
	private ArrayList<String> IPhostInformation;
	CellRenderer()   
	{   
		setOpaque(true);
		sign=new JLabel();
		LoadInformation();
		setLayout(null);
		add(sign);
	}   
	
	public void LoadInformation(){
		IPhostInformation=new ArrayList<String>();
		IPhostInformation.add("192.168.1.1  Administration1");
		IPhostInformation.add("192.168.1.2  Administration2");
		IPhostInformation.add("192.168.1.3  Administration3");
		IPhostInformation.add("192.168.1.4  Administration4");
		IPhostInformation.add("192.168.1.5  Administration5");
//		IPhostInformation.add("192.168.1.3  Administration6");
	}
	/*�����ﵽ������ʵ��getListCellRendererComponent()����*/   
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
			setText(value.toString());  
			setIconTextGap(30);
			setIcon(new   ImageIcon("data/"+(index+1)+".jpg"));
			
			sign.setText(IPhostInformation.get(index));
			sign.setBounds(90, 40, 200, 50);
			
			
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
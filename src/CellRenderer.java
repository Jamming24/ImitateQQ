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
	/*类CellRenderer继承JLabel并实作ListCellRenderer.由于我们利用JLabel易于插图的特性
	 * ，因此CellRenderer继承了JLabel   
	 *可让JList中的每个项目都视为是一个JLabel.   
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
	/*从这里到结束：实作getListCellRendererComponent()方法*/   
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
			//设置选取与取消选取的前景与背景颜色.   
			setBackground(list.getBackground());   
			setForeground(list.getForeground());   
		}   
		return   this;   
	}           
}
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;

public   class   TestJList   
{   
	public   TestJList()   
	{   
		String[]   s   =   {"����","ƻ��","��ݮ","�㽶","����"};
		final Vector<String> data=new Vector<String>();
		data.addElement("С��");
		data.addElement("С��");
		JFrame   f   =   new   JFrame("JList");  
		f.setBounds(100, 100, 500, 500);
//		f.setLayout(null);
		Container   contentPane   =   f.getContentPane();   
		final JList   list1   =   new   JList(data);   
		list1.setBorder(BorderFactory.createTitledBorder("��ϲ������Щˮ����"));   
		/*������JList�л���ͼ���ڴ˲����У����ǲ���һ��CellRenderer���󣬴˶���ʵ����ListCellRenderer   interface,   
		 *��˿��Է���һ��ListCellRenderer������setCellRenderer()�����Ĳ���.   
		 */   
		list1.setCellRenderer(new   CellRenderer());   
		JLabel jla=new   CellRenderer();
		jla.setBackground(Color.red);
		JButton addE=new JButton();
		addE.setBounds(90, 90, 40, 40);
		addE.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				data.addElement("����С��");
				list1.updateUI();
			}
		});
		f.add(addE);

		contentPane.add(new   JScrollPane(list1));   
		f.setVisible(true); 
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}   

	public   static   void   main(String   args[])   
	{   
		new   TestJList();   
	}   
}   


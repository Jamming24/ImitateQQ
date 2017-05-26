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
		String[]   s   =   {"西瓜","苹果","草莓","香蕉","葡萄"};
		final Vector<String> data=new Vector<String>();
		data.addElement("小明");
		data.addElement("小红");
		JFrame   f   =   new   JFrame("JList");  
		f.setBounds(100, 100, 500, 500);
//		f.setLayout(null);
		Container   contentPane   =   f.getContentPane();   
		final JList   list1   =   new   JList(data);   
		list1.setBorder(BorderFactory.createTitledBorder("您喜欢吃哪些水果？"));   
		/*设置在JList中画上图像。在此参数中，我们产生一个CellRenderer对象，此对象实作了ListCellRenderer   interface,   
		 *因此可以返回一个ListCellRenderer对象当作setCellRenderer()方法的参数.   
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
				data.addElement("我是小红");
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


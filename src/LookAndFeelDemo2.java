
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class LookAndFeelDemo2 extends JFrame {

	private JRadioButton radio1 = new JRadioButton("Metal");
	private JRadioButton radio2 = new JRadioButton("Windows");
	private JRadioButton radio3 = new JRadioButton("Motif");
	private JPanel panelNorth = new JPanel();
	private JPanel panelSouth = new JPanel();
	private JTextArea area = new JTextArea(6, 20);
	private JButton btn = new JButton("button");
	private JCheckBox chk = new JCheckBox("checkBox");
	private JComboBox cmbLookAndFeel = new JComboBox(new String[] { "Metal",
			"Widows", "Motif", "GTK" });

	public LookAndFeelDemo2(String title) {
		super(title);
		Container contentPane = this.getContentPane();
		ButtonGroup group = new ButtonGroup();
		group.add(radio1);
		group.add(radio2);
		group.add(radio3);

		panelNorth.add(radio1);
		panelNorth.add(radio2);
		panelNorth.add(radio3);

		panelSouth.add(btn);
		panelSouth.add(chk);
		panelSouth.add(cmbLookAndFeel);

		contentPane.add(panelNorth, BorderLayout.NORTH);
		contentPane.add(area, BorderLayout.CENTER);
		contentPane.add(panelSouth, BorderLayout.SOUTH);
		pack();

		setVisible(true);
		setSize(300, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 监听Item事件
		radio1.addItemListener(new MyItemListener());
		radio2.addItemListener(new MyItemListener());
		radio3.addItemListener(new MyItemListener());

	}

	private class MyItemListener implements ItemListener {

		public void itemStateChanged(ItemEvent e) {
			// 取得点击按钮的名字
			String itemName = ((JRadioButton) e.getSource()).getText();
			changeLookAndFeel(itemName);
		}
	}

	// 设置外观的private类型方法

	private void changeLookAndFeel(String name) {

		String lookAndFeel = "";
		if (name.equals("Metal")) {
			lookAndFeel = "javax.swing.plaf.metal.MetalLookAndFeel";

		} else if (name.equals("Windows")) {
			lookAndFeel = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
		} else if (name.equals("Motif")) {
			lookAndFeel = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
		}else{
			//取得默认的metal外观
			lookAndFeel = "javax.swing.plaf.metal.MetalLookAndFeel";
		}

		try {
			UIManager.setLookAndFeel(lookAndFeel);
			// 运行时指定LookAndFeel，需要SwingUtilities.updateComponentTreeUI（Component
			// c)实现动态的更新
			SwingUtilities.updateComponentTreeUI(this);
			area.setText("当前外观类名：\n" + lookAndFeel);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public static void main(String[] args) {
		new LookAndFeelDemo2("LookAndFeel");
	}
}
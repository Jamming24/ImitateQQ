package com.UI;

import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

public class ContentModel implements ListModel {

//	String[] data={"�й�","Ӣ��","����","�Ĵ�����","�ձ�","����"};
	ArrayList<JPanel> data =new ArrayList<JPanel >();
	public ContentModel(){
		new  ModelPanle("������", "I am a doubi").setVisible(true);
		data.add(new ModelPanle("������", "I am a doubi"));
		data.add(new ModelPanle("������", "I am a doubi"));
		data.add(new ModelPanle("������", "I am a doubi"));
	}
	
	
	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getElementAt(int index) {
		// TODO Auto-generated method stub
		return data.get(index);
	}

	@Override
	public void addListDataListener(ListDataListener l) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeListDataListener(ListDataListener l) {
		// TODO Auto-generated method stub

	}

}

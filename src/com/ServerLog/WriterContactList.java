package com.ServerLog;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;
import java.util.TreeMap;

public class WriterContactList {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public void saveList(TreeMap<String,String> lists){
		//��������ϵ�� ����  ���浽�ı��ĵ���
		//key:�û���; Value: IP��ַ��������
		try {
			PrintWriter pw=new PrintWriter(new FileWriter("ContactList.txt"));
			Set<String> keys=lists.keySet();
			for(String k:keys){
				pw.println(k+"@"+lists.get(k));
			}
			pw.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("�ļ�д��ʧ��");
		}
		
	}
	public void saveProperties(TreeMap<String,String> lists){
		//��������ϵ�� ����  ���浽�ı��ĵ���
		//key:�û���; Value: IP��ַ��������
		try {
			PrintWriter pw=new PrintWriter(new FileWriter("pass.properties"));
			Set<String> keys=lists.keySet();
			for(String k:keys){
				pw.println(k+"="+lists.get(k));
			}
			pw.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("�ļ�д��ʧ��");
		}
		
	}
	

}

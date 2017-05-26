package com.UI;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;


public class WinUtil {
	
	
	private static WinUtil instance =new WinUtil();
	private WinUtil(){}
	
	public static WinUtil getInstance(){
		return instance;
	}
//	@Deprecated
	public Point getScreenCenterPoint(int w,int h){
		Dimension screen=Toolkit.getDefaultToolkit().getScreenSize();
		int x=0;
		int y=0;
		x=(int)(screen.getWidth()-w)/2;
		y=(int)(screen.getHeight()-h)/2;
		return new Point(x,y);
		
	}

	public Properties getInformation(String properPath){
		//��ȡ�����ļ� ���ҷ��������ļ�����
		Properties p=new Properties();
//		Class cl=WinUtil.class;
//		InputStream is=cl.getResourceAsStream("pass.properties");
		try {
			p.load(new FileReader(properPath));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return p;
	}
	public InetAddress getAddress(){ 
		try {
			return InetAddress.getLocalHost();
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			System.out.println("δ֪����");
		}
		return null;
	}
	
	public String getHostName(){
		InetAddress i=getAddress();
		if(null == i){  
            return null;  
        }  
		String hostName=i.getHostName();
		return hostName;
	}
	public String ipCheck(String text) {
        if (text != null && !text.isEmpty()) {
            // ����������ʽ
            String regex = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\."
                    + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
                    + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
                    + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";
            // �ж�ip��ַ�Ƿ���������ʽƥ��
            if (text.matches(regex)) {
                // �����ж���Ϣ
//                return text + "\n��һ���Ϸ���IP��ַ��";
            	return "pass";
            } else {
                // �����ж���Ϣ
//                return text + "\n����һ���Ϸ���IP��ַ��";
            	return "fail";
            }
        }
        // �����ж���Ϣ
        return "blank";
//        return "������Ҫ��֤��IP��ַ��";
    }


	
	public String getIP(){
		InetAddress i=getAddress();
		if(null == i){  
            return null;  
        } 
		String ip=i.getHostAddress();
		return ip;
	}
	
	public String getCurrentime(){
		//���ص�ǰϵͳʱ��
		Date datatime=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time=sdf.format(datatime);
		System.out.println(time);
		return time;
	}
	
}

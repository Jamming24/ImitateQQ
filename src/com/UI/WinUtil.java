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
		//读取配置文件 并且返回配置文件对象
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
			System.out.println("未知主机");
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
            // 定义正则表达式
            String regex = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\."
                    + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
                    + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
                    + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";
            // 判断ip地址是否与正则表达式匹配
            if (text.matches(regex)) {
                // 返回判断信息
//                return text + "\n是一个合法的IP地址！";
            	return "pass";
            } else {
                // 返回判断信息
//                return text + "\n不是一个合法的IP地址！";
            	return "fail";
            }
        }
        // 返回判断信息
        return "blank";
//        return "请输入要验证的IP地址！";
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
		//返回当前系统时间
		Date datatime=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time=sdf.format(datatime);
		System.out.println(time);
		return time;
	}
	
}

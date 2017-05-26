package com.NetServer;

import java.net.Socket;

public class UserInformation {
	
	private String Hostname;
	private String IP;
	private Socket socket;
	
	public UserInformation (String Hname,Socket st,String IP){
		this.Hostname=Hname;
		this.socket=st;
		this.IP=IP;
	}
	public String getUsername() {
		return Hostname;
	}
	
	public Socket getSocket() {
		return socket;
	}
	
	public String getIP() {
		return IP;
	}

	

}



import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import com.UI.WinUtil;


public class Test4 {

	/**
	 * @param args
	 * 
	 * 
	 * 实现群发消息功能 ，添加群发消息对话框 ,消息闪动功能 
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String time=WinUtil.getInstance().getCurrentime();
		//		System.out.println(time);
		Test4 t=new Test4();
		try {
			BroadCastTest.sendBroadcast();
		} catch (Exception e) {
			// TODO Auto-generated catch  block
			e.printStackTrace();
		}
//		t.TestUDP();
//		t.UDPsent();
	}

	public void TestUDP(){
		byte[] buffer=new byte[1024]; 
		DatagramSocket listen;
		try {
			listen = new DatagramSocket(10001);
			DatagramPacket receiveData=new DatagramPacket(buffer, buffer.length);
			listen.receive(receiveData);
			String information=new String(receiveData.getData(),0,receiveData.getLength());
			System.out.println("服务端收到信息:"+information);

		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void UDPsent(){
		try {
			DatagramSocket sendSocket=new DatagramSocket();
			byte[] buffer="我是信息》》》》》".getBytes();
			DatagramPacket sendMessage=new DatagramPacket(buffer, buffer.length,InetAddress.getByName("255.255.255.255"),10001);
			sendSocket.send(sendMessage);
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}

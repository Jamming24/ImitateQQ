

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
	 * ʵ��Ⱥ����Ϣ���� �����Ⱥ����Ϣ�Ի��� ,��Ϣ�������� 
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
			System.out.println("������յ���Ϣ:"+information);

		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void UDPsent(){
		try {
			DatagramSocket sendSocket=new DatagramSocket();
			byte[] buffer="������Ϣ����������".getBytes();
			DatagramPacket sendMessage=new DatagramPacket(buffer, buffer.length,InetAddress.getByName("255.255.255.255"),10001);
			sendSocket.send(sendMessage);
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}

package ComMulticast;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Date;

public class MulticastSender {
	
	InetAddress grupo;
	MulticastSocket multicast;
	DatagramPacket pacote;
	
	public MulticastSender(MulticastSocket multicast, InetAddress grupo) {
		this.multicast = multicast;
		this.grupo = grupo;
	}
	
	public void sendMsg(String msg, String nick) {
		try {
			Date today = new Date();
			String allMsg = nick+" - Hora: "+today.getHours()+":"+today.getMinutes()+" >>> "+msg;
			byte[] buffer = allMsg.getBytes();
			pacote = new DatagramPacket(buffer, buffer.length, grupo, multicast.getLocalPort());
			multicast.send(pacote);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}

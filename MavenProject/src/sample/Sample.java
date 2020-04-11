package sample;

import java.io.IOException;

import io.pkts.Pcap;
import io.pkts.packet.Packet;

public class Sample {
	
	public static void main(String[] args) {
		
		try {
			Pcap pcap = Pcap.openStream("C:\\Users\\jaehwan\\Desktop\\Project_UPPL\\16-09-23.pcap");
			pcap.loop((final Packet packet) -> {
			
				// Here we can handle ecah packet
				return true;
			});
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}

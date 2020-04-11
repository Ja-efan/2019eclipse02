package com.javahelps.pcapparse;

import java.io.IOException;

import io.pkts.PacketHandler;
import io.pkts.Pcap;
import io.pkts.buffer.Buffer;
import io.pkts.packet.Packet;
import io.pkts.packet.TCPPacket;
import io.pkts.packet.UDPPacket;
import io.pkts.protocol.Protocol;

public class Main {
	
	public static void main(String[] args) throws IOException {
		
		final Pcap pcap = Pcap.openStream("‪C:\\Users\\jaehwan\\sample.csv");
		
		pcap.loop(new PacketHandler() {
			public boolean nextPacket(Packet packet) throws IOException {
				
				if(packet.hasProtocol(Protocol.TCP)) {
					
					TCPPacket tcpPacket = (TCPPacket) packet.getPacket(Protocol.TCP);
					Buffer buffer = tcpPacket.getPayload();
					if(buffer != null) {
						System.out.println("TCP: "+ buffer);
					}
				}else if (packet.hasProtocol(Protocol.UDP)) {
					UDPPacket udpPacket = (UDPPacket) packet.getPacket(Protocol.UDP);
					Buffer buffer = udpPacket.getPayload();
					if (buffer != null) {
						System.out.println("UDP: "+ buffer);
					}
				}
				return true;
			}
			
		});
	}

}

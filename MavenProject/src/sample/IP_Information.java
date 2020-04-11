package sample;

import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;


import io.pkts.Pcap;
import io.pkts.buffer.Buffer;
import io.pkts.packet.IPPacket;
import io.pkts.packet.Packet;
import io.pkts.protocol.Protocol;
import io.pkts.packet.TransportPacket;



public class IP_Information {
	
	public static void main(String[] args) {
		
		
		//Map<String, PacketData> PacketMap = new HashMap<String, PacketData>();
		
		try {
			
			int i = 0;
			
			Pcap pcap = Pcap.openStream("C:\\Users\\jaehwan\\Desktop\\1130.pcap");
			
			
			pcap.loop((final Packet packet) -> { //람다함수 변경 바람
				
				//PacketData pd = new PacketData();
				TransportPacket transport = null;
				
				if(packet.hasProtocol(Protocol.IPv4)) {
					
					IPPacket ip = (IPPacket)packet.getPacket(Protocol.IPv4);
					
					if(packet.hasProtocol(Protocol.UDP)) {

						 transport = (TransportPacket)packet.getPacket(Protocol.UDP);
					
					
					}else if (packet.hasProtocol(Protocol.TCP)) {
						
						 transport = (TransportPacket)packet.getPacket(Protocol.TCP);
						
					}else {
			
					}
					
					
					
					
					
					
					
					System.out.println("---------------------------------------------------");
					String dstip = ip.getDestinationIP();
					//pd.setDstIp(dstip);
					System.out.println("dstIP: "+ dstip);
					
					String srcip = ip.getSourceIP();
					//pd.setSrcIp(srcip);
					System.out.println("srcIP: "+ srcip);
					
					int dstport = transport.getDestinationPort();
					//pd.setDstPort(dstport);
					System.out.println("dstPort: " + dstport);
					
					int srcport = transport.getSourcePort();
					//pd.setSrcPort(srcport);
					System.out.println("srcPort: " + srcport);
					
					
					
					
					Buffer payload = ip.getPayload();
					//pd.setPayLoad(payload);
					System.out.println("PayLoad: "+ payload);
					
					long packetTime = ip.getArrivalTime();
					//pd.setPacketTime(packetTime);
					System.out.println("Time: "+ packetTime);
					
					boolean isFragment = ip.isFragmented();
					//pd.setIsFragment(isFragment);
					System.out.println("isFragment: "+ isFragment);
					System.out.println("---------------------------------------------------");
					
			
					
					
					
				}	
				
			
				
				
				
				return true;
				
	
			});
			
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

}

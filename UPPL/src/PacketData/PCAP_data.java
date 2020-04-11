package PacketData;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;

import org.jnetpcap.Pcap;
import org.jnetpcap.PcapIf;
import org.jnetpcap.packet.PcapPacket;
import org.jnetpcap.packet.PcapPacketHandler;

public class PCAP_data {
	
	static {
		try {
			//native Library Load
			System.load(new File("jnetpcap.dll").getAbsolutePath());
			System.out.println(new File("jnetpcap.dll").getAbsolutePath());
	
		} catch (UnsatisfiedLinkError e) {
			System.out.println("Native code library failed to load.\n"+e);
			System.exit(1);
		}
	}
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		
		ArrayList<PcapIf> allDevs = new ArrayList<PcapIf>();
		// ����̽��� ���� ������ ArrayList�� ����
		StringBuilder errbuf = new StringBuilder();
		// ���� ó��
		
		int r = Pcap.findAllDevs(allDevs, errbuf);
		// ���ٰ����� ����̽��� ù��° ���ڿ� ��´�. �ι�° ���ڴ�  ����ó��
		
		if (r==Pcap.NOT_OK || allDevs.isEmpty()) {
			System.out.println("��Ʈ��ũ ��ġ ã�� ����."+ errbuf.toString());
			return;
		}
		
		System.out.println("<Ž���� ��Ʈ��ũ Device>");
		int i =0;
		
		
		for (PcapIf device : allDevs) { // Ž���� ��� ���
			String description = (device.getDescription() != null) ?
					device.getDescription() : "��� ���� ������ �����ϴ�.";
					
			System.out.printf("[%d��]: %s [%s]\n", ++i, device.getName(),description);
		}
		
		PcapIf device = allDevs.get(0);
		System.out.printf("���õ� ��ġ: %s\n", (device.getDescription()!=null) ?
				device.getDescription() : device.getName());
			
		int snaplen = 64*1024; // 655533bytes ��ŭ ��Ŷ�� ĸ��
		int flags = Pcap.MODE_PROMISCUOUS; // ���������
		int timeout = 10*1000; // time out 10second
		Pcap pcap = Pcap.openLive(device.getName(),snaplen,flags,timeout,errbuf);
		
		if(pcap==null) {
			System.out.printf("Network Device Access Failed. Error: " + errbuf.toString());
			return;
		}
		
	PcapPacketHandler<String> jPacketHandler = new PcapPacketHandler<String>() {
		public void nextPacket(PcapPacket packet, String user) {
			System.out.printf("capture time : %s\ncapture length: %d\n", new Date(packet.getCaptureHeader().timestampInMillis()),
					packet.getCaptureHeader().caplen());
		}
	};
	pcap.loop(5,jPacketHandler, "jNetPcap");
	pcap.close();
				
	}
}

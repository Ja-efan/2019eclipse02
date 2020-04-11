package sample;

import io.pkts.buffer.Buffer;

public class PacketData {
	
	private String srcIp;
	private String dstIp;
	private int srcPort;
	private int dstPort;
	private Buffer payLoad;
	private long packetTime;
	private boolean isFragment;
	
	public String getSrcIp() {
		return srcIp;
	}
	
	public String getDstIp() {
		return dstIp;
	}
	
	public int getSrcPort() {
		return srcPort;
	}
	
	public int getDstPort() {
		return dstPort;
	}
	
	public Buffer getPayload() {
		return payLoad;
	}
	
	public long getPacketTime() {
		return packetTime;
	}
	
	public boolean getIsFragment() {
		return isFragment;
	}
	// ------------------------¼³Á¤(set)--------------------------------
	
	public void setSrcIp(String srcIp) {
		this.srcIp = srcIp;
	}
	
	public void setDstIp(String dstIp) {
		this.dstIp = dstIp;
	}
	
	public void setSrcPort(int srcPort) {
		this.srcPort = srcPort;
	}
	
	public void setDstPort(int dstPort) {
		this.dstPort = dstPort;
	}
	public void setPayLoad(Buffer payLoad) {
		this.payLoad = payLoad;
	}
	
	public void setPacketTime(long time) {
		this.packetTime = time;
	}
	
	public void setIsFragment(boolean isFragment) {
		this.isFragment = isFragment;
	}
	
	

}

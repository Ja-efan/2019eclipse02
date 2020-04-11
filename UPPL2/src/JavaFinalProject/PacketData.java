package JavaFinalProject;


public class PacketData {
	
	private String pNum;
	private String srcIp;
	private String dstIp;
	private String payLoad;
	private long packetTime;
	private String protocol;
	private String conn;
	
	// ------------------반환(return)-------------------------------------
	
	public String getpNum() {
		return pNum;
	}
	
	public String getSrcIp() {
		return srcIp;
	}
	
	public String getDstIp() {
		return dstIp;
	}
	
	
	public String getPayload() {
		return payLoad;
	}
	
	public long getPacketTime() {
		return packetTime;
	}
	
	public String getProtocol() {
		return protocol;
	}
	
	public String getConn() {
		return conn;
	}

	// ------------------------설정(set)--------------------------------
	
	
	public void setpNum (String pNum) {
		this.pNum = pNum;
	}
	
	public void setSrcIp(String srcIp) {
		this.srcIp = srcIp;
	}
	
	public void setDstIp(String dstIp) {
		this.dstIp = dstIp;
	}
	
	
	public void setPayLoad(String payLoad) {
		this.payLoad = payLoad;
	}
	
	public void setPacketTime(long time) {
		this.packetTime = time;
	}
	
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
	
	public void setConn(String conn) {
		this.conn = conn;
	}

	
	

}

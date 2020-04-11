package IPpacket;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;

import javax.swing.JOptionPane;

public class PacketMain {
	
	String id, password;
	
	public PacketMain(String id, String password) { 
		this.id =id;
		this.password = password;
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");  // 드라이버 로드
			System.out.println("드라이버 로드 성공");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void Insert(String table, PacketData pd) {
		
		String url = "jdbc:mysql://localhost:3306/uppl?characterEncoding=UTF-8&serverTimezone=UTC";
		try {
			Connection con = DriverManager.getConnection(url,this.id, this.password);
			
			Statement stmt = con.createStatement();
			String sql ="INSERT INTO "+table+"(pNum, srcIP, dstIP, data, conn)"
					+"VALUES('"+pd.getpNum()+"','"+pd.getSrcIp()+"','"+pd.getDstIp()+"','"+pd.getPayload()+"','"+pd.getConn()+"')";
			stmt.executeUpdate(sql);
			System.out.println(table+"테이블에 데이터 입력 완료");
			
			stmt.close();
			con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return;
	}
	
	public void Select(String table, String pNum) {
		
		String url = "jdbc:mysql://localhost:3306/uppl?characterEncoding=UTF-8&serverTimezone=UTC";
		try {
			Connection con = DriverManager.getConnection(url,this.id, this.password);
			
			
			Statement stmt = con.createStatement();
			String sql = "SELECT * FROM " + table + " WHERE PNUM = '" + pNum + "'";
			
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				String tmpPnum=rs.getString(1);
				String tmpsrcIP = rs.getString(2);
				String tmpdstIP = rs.getString(3);
				String tmpdata = rs.getString(4);
				String tmpconn = rs.getString(5);
				JOptionPane.showMessageDialog(null, "pNum :"+tmpPnum+", srcIP: "+tmpsrcIP+", dstIP: "+tmpdstIP+", data: "+tmpdata+", conn: "+tmpconn);
			}
			stmt.close();
			con.close();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return;
	}
	
	public void Update(String table, String pNum, String attr, String value) {
		
		String url =  "jdbc:mysql://localhost:3306/uppl?characterEncoding=UTF-8&serverTimezone=UTC";
		try {
			Connection con = DriverManager.getConnection(url,this.id, this.password);
			
			
			Statement stmt = con.createStatement();
			String sql ="UPDATE "+table+" SET "+attr+"='"+value+"' WHERE pNum =" + pNum;
			stmt.executeUpdate(sql);
			JOptionPane.showMessageDialog(null, "해당 패킷 수정 완료");
			
			// 수정된 패킷 정보 출력
			String sql2 ="SELECT * FROM "+table+" WHERE PNUM ='" + pNum+"'";
			
			ResultSet rs = stmt.executeQuery(sql2);
			while(rs.next()) {
				String tmpPnum=rs.getString(1);
				String tmpsrcIP = rs.getString(2);
				String tmpdstIP = rs.getString(3);
				String tmpdata = rs.getString(4);
				String tmpconn = rs.getString(5);
				JOptionPane.showMessageDialog(null, "pNum :"+tmpPnum+", srcIP: "+tmpsrcIP+", dstIP: "+tmpdstIP+", data: "+tmpdata+", conn: "+tmpconn);
			}
			
			stmt.close();
			con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return;
		
	}
	
	public void Delete(String table, String pNum) {
		
		String url =  "jdbc:mysql://localhost:3306/uppl?characterEncoding=UTF-8&serverTimezone=UTC";
		try {
			Connection con = DriverManager.getConnection(url,this.id, this.password);
			
			
			Statement stmt = con.createStatement();
			String sql = "DELETE FROM "+table+" WHERE PNUM = '"+pNum+"'";
			stmt.executeUpdate(sql);
			
			JOptionPane.showMessageDialog(null, "해당 패킷 삭제 완료");
			
			stmt.close();
			con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return;
		
	}
//-----------------------------------------------------------------------------------------------------------------
	
	public static void main(String[] args) {
		
		HashMap<String, String> defaultMap= new HashMap<String, String>(); //dstip와 통신방법 맵핑 (default 통신 방법을 가지는 packet)
		HashMap<String, String> pnumMap = new HashMap<String,String>(); // pnum과 통신방법 맵핑 (데이터 입력 시 맵에 추가, 데이터 삭제시 맵에서 삭제)
		System.out.println(pnumMap.size());
	
		// default설정이 되어있는 IP주소
		defaultMap.put("192.168.102.130", "Direct");
		defaultMap.put("192.168.102.131", "Direct");

		defaultMap.put("192.168.102.132", "Proxy");
		defaultMap.put("192.168.102.133", "Proxy");

		defaultMap.put("192.168.102.134", "VPN");
		defaultMap.put("192.168.102.135", "VPN");

		defaultMap.put("192.168.102.136", "Tor");
		defaultMap.put("192.168.102.137", "Tor");

		
		/*
		ArrayList Direct = new ArrayList();
		ArrayList Proxy = new ArrayList();
		ArrayList VPN = new ArrayList();
		ArrayList Tor = new ArrayList();
		*/
		     
		while (true) {
			
			PacketMain manager = new PacketMain("root","1111");
			
			String menu = JOptionPane.showInputDialog("패킷 관리 시스템 \n 메뉴입럭 \n"
					+" 1:데이터 삽입, 2:데이터 삭제, 3:데이터 조회, 4:데이터 수정, 0:종료");
			
			int menui = Integer.parseInt(menu);
			
			if(menui == 0) { // 프로그램 종료
				JOptionPane.showMessageDialog(null, "프로그램을 종료합니다.");
				break;
			}
			
			
			else if(menui == 1) { // 패킷 데이터 입력
				PacketData pd = new PacketData();
				
				String conn; // 통신방법 변수
				String table;
				
				String pNum = JOptionPane.showInputDialog("등록할 패킷의 번호입력");
				// 입력받은 패킷 번호가  이미 리스트에 존재할시 경고문구  
				while(pnumMap.containsKey(pNum)) { 
					
					pNum = JOptionPane.showInputDialog("이미 존재하는 패킷 번호 입니다.\n 재입력: ");
					
				}
				pd.setpNum(pNum);
				
				String srcIP = JOptionPane.showInputDialog("등록할 패킷의 송신지 IP입력");
				pd.setSrcIp(srcIP);
				
				String dstIP = JOptionPane.showInputDialog("등록할 패킷의 수신지 IP입력");
				pd.setDstIp(dstIP);
				
				String data = JOptionPane.showInputDialog("전송할 데이터 입력");
				pd.setPayLoad(data);
				
				
				if (defaultMap.containsKey(dstIP)) { //default값이 설정되어있는 ip주소
					conn = defaultMap.get(dstIP); // 해당 ip주소가 갖는 통신방법을 저장
					pd.setConn(conn);
					pnumMap.put(pNum, conn); // 패킷번호와 통신방법을 저장
					table = "defaultTable";
				}
				
				else { // default값이 설정되어 있지 않은 ip주소
					conn = JOptionPane.showInputDialog("default통신 방법이 없습니다.\n통신방법을 입력하세요\n"
							+"Direct, Proxy, VPN, Tor 중 1개 선택");
					pd.setConn(conn);
					pnumMap.put(pNum, conn); // 패킷번호와 통신방법을 저장(default에 없는 ip)
					table = "ExceptionTable";
					
				}
				
				manager.Insert(table, pd);
				
				
			}
			else if(menui == 2) { // 패킷 데이터 삭제
				String table;
				String pNum = JOptionPane.showInputDialog("삭제할 패킷의 번호입력");
				// 입력받은 pNum이 존재 하지 않는다면 재입력요구
				if(pnumMap.containsKey(pNum)) { // 삭제할 패킷의 번호가 pnumMap에 존재 하는경우
					table = "DefaultTable"; 
				} else { // 
					table = "ExceptionTable";
				} 
				
				manager.Delete(table, pNum);
				pnumMap.remove(pNum);
				
			}
			
			
			else if(menui == 3) { // 패킷 데이터 조회
				String table;
				String pNum = JOptionPane.showInputDialog("조회할 패킷의 번호입력");
				// 입력받은 pNum이 존재 하지 않는다면 재입력요구
				if(pnumMap.containsKey(pNum)) {
					table = "DefaultTable";
				}else {
					table = "ExceptionTable";
				}
				manager.Select(table, pNum);
			}
			
			
			else if(menui == 4) { //패킷 데이터 수정
				
				String table;
				String pNum = JOptionPane.showInputDialog("수정할 패킷의 번호입력");
				// 입력받은 pNum이 존재 하지 않는다면 재입력요구
				if(pnumMap.containsKey(pNum)) {
					table = "DefaultTable";
				}else {
					table = "ExceptionTable";
				}
				
				String attr = JOptionPane.showInputDialog("수정할 속성 선택. \n(pNum 변경 불가)");
				String value = JOptionPane.showInputDialog("변경할 값 입력");
				manager.Update(table, pNum, attr, value);
				
			}
		}
		
	}

}

package JavaFinalProject;


//import java.awt.JobAttributes;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import javax.swing.JOptionPane;

public class PacketMain2_2 {
	
	String id, password;
	
	public PacketMain2_2(String id, String password) { 
		this.id =id;
		this.password = password;
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");  // 드라이버 로드
			System.out.println("<드라이버 로드 성공>");
			
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
			
			System.out.println(table+"테이블에 "+pd.getpNum()+"번 패킷 입력 ");
			JOptionPane.showMessageDialog(null, table+"에 " + pd.getpNum() + "번 패킷 데이터 입력 완료");
			
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
				JOptionPane.showMessageDialog(null, "pNum :"+tmpPnum+", srcIP: "+tmpsrcIP+", dstIP: "+tmpdstIP+
						", data: "+tmpdata+", conn: "+tmpconn);
				
			}
			System.out.println(pNum+"번 패킷 조회 완료");
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
			
			String sql2 ="SELECT * FROM "+table+" WHERE PNUM ='" + pNum+"'";
			
			ResultSet rs = stmt.executeQuery(sql2);
			while(rs.next()) {
				String tmpPnum=rs.getString(1);
				String tmpsrcIP = rs.getString(2);
				String tmpdstIP = rs.getString(3);
				String tmpdata = rs.getString(4);
				String tmpconn = rs.getString(5);
				JOptionPane.showMessageDialog(null, table + " 테이블의 "+pNum+" 번 패킷 수정");
				JOptionPane.showMessageDialog(null, "pNum :"+tmpPnum+", srcIP: "+tmpsrcIP+", dstIP: "+tmpdstIP+", data: "+tmpdata+", conn: "+tmpconn);
			}
			System.out.println("데이터 수정 완료");
			
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
			String sql = "DELETE FROM " + table + " WHERE PNUM = '" + pNum + "'";
			stmt.executeUpdate(sql);
			
			System.out.println(table + " 테이블에서 " + pNum + "번 패킷 삭제");
			JOptionPane.showMessageDialog(null, pNum+"번 패킷 삭제 완료");
			
			
			stmt.close();
			con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return;
		
	}
//-----------------------------------------------------------------------------------------------------------------
	
	public static void main(String[] args) {
		
		HashMap<String, String> connDefaultMap= new HashMap<String, String>(); //dstip와 통신방법 맵핑 (default 통신 방법을 가지는 packet)
		
		// 프로그램이 실행 될 때 마다 DB에서 데이터 로드
		HashMap<String, String> defaultMap = new HashMap<String,String>(); // defaultTable에 들어갈 pnum과 conn을 mapping
		HashMap<String, String> exceptionMap = new HashMap<String, String>(); // exceptionTable에 들어갈 pnum과 conn을 mapping
	
		
		String url =  "jdbc:mysql://localhost:3306/uppl?characterEncoding=UTF-8&serverTimezone=UTC";
		
		try {
			Connection con = DriverManager.getConnection(url,"root","1111");
			Statement stmt1 = con.createStatement();
			Statement stmt2 = con.createStatement();
			ResultSet rs1 = stmt1.executeQuery("SELECT * FROM defaultTable");
			ResultSet rs2 = stmt2.executeQuery("SELECT * FROM exceptionTable");
			
			System.out.println("기존 데이터 출력");
			System.out.println("defaultTable 데이터---------------------------------\npNum \t srcIP \t\t\t dstIP \t\t\tdata \tconn");
			
			while (rs1.next()) {
				String pNum = rs1.getString(1);
				String srcIP = rs1.getString(2);
				String dstIP = rs1.getString(3);
				String data = rs1.getString(4);
				String conn = rs1.getString(5);
				
				System.out.println(pNum +"\t"+srcIP+"\t\t"+dstIP+"\t\t"+data+"\t"+conn);
				defaultMap.put(pNum,conn); // 프로그램 실행시 DB에서 defaultTable에 들어있는 데이터의 pNum과 conn을 Map에 저장
			}
			System.out.println("프로그램 실행 시각에 테이블에 존재하는 패킷 수: " +defaultMap.size());
			
			System.out.println("exceptionTable 데이터-------------------------------\npNum \t srcIP \t\t\t dstIP \t\t\tdata \tconn");
			while (rs2.next()) {
				String pNum = rs2.getString(1);
				String srcIP = rs2.getString(2);
				String dstIP = rs2.getString(3);
				String data = rs2.getString(4);
				String conn = rs2.getString(5);
				
				System.out.println(pNum +"\t"+srcIP+"\t\t"+dstIP+"\t\t"+data+"\t"+conn);
				exceptionMap.put(pNum,conn); // 프로그램 실행시 DB에서 exceptionTable에 들어있는 데이터의 pNum과 conn을 Map에 저장
			}
			System.out.println("프로그램 실행 시각에 테이블에 존재하는 패킷 수: " +exceptionMap.size());
					
			
			stmt1.close();
			stmt2.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}


		// default설정이 되어있는 IP주소. 추가가능
		connDefaultMap.put("192.168.102.130", "Direct");
		connDefaultMap.put("192.168.102.131", "Direct");

		connDefaultMap.put("192.168.102.132", "Proxy");
		connDefaultMap.put("192.168.102.133", "Proxy");

		connDefaultMap.put("192.168.102.134", "VPN");
		connDefaultMap.put("192.168.102.135", "VPN");

		connDefaultMap.put("192.168.102.136", "Tor");
		connDefaultMap.put("192.168.102.137", "Tor");

		
		/*
		ArrayList Direct = new ArrayList();
		ArrayList Proxy = new ArrayList();
		ArrayList VPN = new ArrayList();
		ArrayList Tor = new ArrayList();
		*/
		     
		while (true) {
			
			PacketMain2_2 manager = new PacketMain2_2("root","1111");
			
			
			String menu = JOptionPane.showInputDialog("패킷 관리 시스템 \n 메뉴입럭 \n"
					+" 1:데이터 삽입, 2:데이터 삭제, 3:데이터 조회, 4:데이터 수정, 5:defaultTable보기, 6:exceptionTable보기, 0:종료");
			
			int menui = Integer.parseInt(menu);
			
			switch (menui) {
			case 0:
				JOptionPane.showMessageDialog(null, "프로그램을 종료합니다.");
				break;
				
			case 1:
				PacketData pd = new PacketData();
				
				String conn; // 통신방법 변수
				String table1;
				
				String pNum1 = JOptionPane.showInputDialog("등록할 패킷의 번호입력");
				/*
				while(pNum == "\n") {
					pNum = JOptionPane.showInputDialog("패킷 번호는 공백이 불가합니다.\n 재입력");
				}
				*/
				// 입력받은 패킷 번호가  이미 리스트에 존재할시 경고문구  
				while(defaultMap.containsKey(pNum1) | exceptionMap.containsKey(pNum1)) { 
					
					pNum1 = JOptionPane.showInputDialog("이미 존재하는 패킷 번호 입니다.\n 재입력");
					
					
				}
				pd.setpNum(pNum1);
				
				String srcIP = JOptionPane.showInputDialog("등록할 패킷의 송신지 IP입력");
				pd.setSrcIp(srcIP);
				
				String dstIP = JOptionPane.showInputDialog("등록할 패킷의 수신지 IP입력");
				pd.setDstIp(dstIP);
				
				String data = JOptionPane.showInputDialog("전송할 데이터 입력");
				pd.setPayLoad(data);
				
				
				if (connDefaultMap.containsKey(dstIP)) { //default값이 설정되어있는 ip주소
					conn = connDefaultMap.get(dstIP); // 해당 ip주소가 갖는 통신방법을 저장
					pd.setConn(conn);
					defaultMap.put(pNum1, conn); // 패킷번호와 통신방법을 저장
					table1 = "defaultTable";
				}
				
				else { // default값이 설정되어 있지 않은 ip주소
					conn = JOptionPane.showInputDialog("default통신 방법이 없습니다.\n아래 통신방법 중 하나을 입력하세요.\n"
							+"Direct, Proxy, VPN, Tor (정확히 입력)");
					pd.setConn(conn);
					exceptionMap.put(pNum1, conn); // 패킷번호와 통신방법을 저장(connMap에 없는 ip)
					table1 = "ExceptionTable";
					
				}
				
				manager.Insert(table1, pd);
				break;
				
			case 2:
				String table2;
				String pNum2 = JOptionPane.showInputDialog("삭제할 패킷의 번호입력");
				
				while(defaultMap.containsKey(pNum2) == false && exceptionMap.containsKey(pNum2) == false) {
					pNum2 = JOptionPane.showInputDialog("존재하지 않는 패킷 번호입니다.\n다시 입력하세요.");
				}
				// 입력받은 pNum이 존재 하지 않는다면 재입력요구
				if(defaultMap.containsKey(pNum2)) { // 삭제할 패킷의 번호가 defaultMap(Table)에 존재하는 경우
					table2 = "DefaultTable"; 
					defaultMap.remove(pNum2); // defaultMap에서 삭제
				} else { // 삭제할 패킷의 번호가 exceptionMap(Table)에 존재하는 경우
					table2 = "ExceptionTable";
					exceptionMap.remove(pNum2); // defaultMap에서 삭제
				} 
				
				manager.Delete(table2, pNum2); 
				break;
				
			case 3:
				String table3;
				String pNum3 = JOptionPane.showInputDialog("조회할 패킷의 번호입력");
				while(!defaultMap.containsKey(pNum3) && !exceptionMap.containsKey(pNum3)) {
					pNum3 = JOptionPane.showInputDialog("존재하지 않는 패킷 번호입니다.\n다시 입력하세요.");
				}
				// 입력받은 pNum이 존재 하지 않는다면 재입력요구
				if(defaultMap.containsKey(pNum3)) {
					table3 = "DefaultTable";
				}else {
					table3 = "ExceptionTable";
				}
				manager.Select(table3, pNum3);
				break;
			
			case 4:
				String table4;
				String pNum4 = JOptionPane.showInputDialog("수정할 패킷의 번호입력");
				while(!defaultMap.containsKey(pNum4) && !exceptionMap.containsKey(pNum4)){
					pNum4 = JOptionPane.showInputDialog("존재하지 않는 패킷번호 입니다.\n다시 입력하세요.");
					
				}
				// 입력받은 pNum이 존재 하지 않는다면 재입력요구
				if(defaultMap.containsKey(pNum4)) {
					table4 = "DefaultTable";
				}else {
					table4 = "ExceptionTable";
				}
				
				String attr = JOptionPane.showInputDialog("수정할 속성 선택.(srcIP, dstIP, data, conn) \n(pNum 변경 불가)");
				while(attr.equals("pNum")) {
					attr = JOptionPane.showInputDialog("pNum은 변경이 불가 합니다.\n수정할 속성을 다시 입력하세요.\n(srcIP, dstIP, data, conn)");
				}
				
				String value = null;
				if(attr.equals("conn")) {
					value = JOptionPane.showInputDialog("변경할 conn입력(Direct, Proxy, VPN, Tor");
				}else {
					value = JOptionPane.showInputDialog("변경할 속성값 입력");
				}
				
				manager.Update(table4, pNum4, attr, value);
				break;
			
			case 5:
				try {
					Connection con5 = DriverManager.getConnection(url,"root","1111");
					Statement stmt5 = con5.createStatement();
					
					ResultSet rs5 = stmt5.executeQuery("SELECT * FROM defaultTable");
					
					System.out.println("defaultTable 데이터---------------------------------\npNum \t srcIP \t\t\t dstIP \t\t\tdata \tconn");
					
					while (rs5.next()) {
						String pNum5 = rs5.getString(1);
						String srcIP5 = rs5.getString(2);
						String dstIP5 = rs5.getString(3);
						String data5 = rs5.getString(4);
						String conn5 = rs5.getString(5);
						
						System.out.println(pNum5 +"\t"+srcIP5+"\t\t"+dstIP5+"\t\t"+data5+"\t"+conn5);
						defaultMap.put(pNum5,conn5);
					}
					System.out.println("현재 'defaultTable' 테이블에 존재하는 패킷 수: " +defaultMap.size());
					
			
					stmt5.close();
					con5.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				break;
			
			case 6:
				try {
					Connection con6 = DriverManager.getConnection(url,"root","1111");
					Statement stmt6 = con6.createStatement();
					
					ResultSet rs6 = stmt6.executeQuery("SELECT * FROM exceptionTable");
					
					System.out.println("exceptionTable 데이터---------------------------------\npNum \t srcIP \t\t\t dstIP \t\t\tdata \tconn");
					
					while (rs6.next()) {
						String pNum6 = rs6.getString(1);
						String srcIP6 = rs6.getString(2);
						String dstIP6 = rs6.getString(3);
						String data6 = rs6.getString(4);
						String conn6 = rs6.getString(5);
						
						System.out.println(pNum6 +"\t"+srcIP6+"\t\t"+dstIP6+"\t\t"+data6+"\t"+conn6);
						defaultMap.put(pNum6,conn6);
					}
					System.out.println("현재 'exceptionTable' 테이블에 존재하는 패킷 수: " +exceptionMap.size());
					
			
					stmt6.close();
					con6.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				break;
				
			default:
				JOptionPane.showMessageDialog(null, "없는 메뉴번호 입니다. 다시 입력하세요.");
				break;
			
			}
		}
		
	}

}
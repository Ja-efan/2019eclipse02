package JavaFinalProject;


//import java.awt.JobAttributes;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import javax.swing.JOptionPane;

public class PacketMain2 {
	
	String id, password;
	
	public PacketMain2(String id, String password) { 
		this.id =id;
		this.password = password;
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");  // ����̹� �ε�
			System.out.println("<����̹� �ε� ����>");
			
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
			
			System.out.println(table+"���̺� "+pd.getpNum()+"�� ��Ŷ �Է� "); // console ���
			JOptionPane.showMessageDialog(null, table+"�� " + pd.getpNum() + "�� ��Ŷ ������ �Է� �Ϸ�"); // gui���
			
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
						", data: "+tmpdata+", conn: "+tmpconn); // gui ���
				
			}
			System.out.println(pNum+"�� ��Ŷ ��ȸ �Ϸ�"); // console ���
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
				JOptionPane.showMessageDialog(null, table + " ���̺��� "+pNum+" �� ��Ŷ ����"); // �����Ϸ� �޽��� gui���
				JOptionPane.showMessageDialog(null, "pNum :"+tmpPnum+", srcIP: "+tmpsrcIP+", dstIP: "+tmpdstIP+
						", data: "+tmpdata+", conn: "+tmpconn); // ������ ��Ŷ������ ��� gui
			}
			System.out.println("������ ���� �Ϸ�"); // console ���
			
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
			
			System.out.println(table + " ���̺��� " + pNum + "�� ��Ŷ ����"); // console ���
			JOptionPane.showMessageDialog(null, pNum+"�� ��Ŷ ���� �Ϸ�"); // gui ���
			
			
			stmt.close();
			con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return;
		
	}
//-----------------------------------------------------------------------------------------------------------------
	
	public static void main(String[] args) {
		
		HashMap<String, String> connDefaultMap= new HashMap<String, String>(); //dstip�� ��Ź�� ���� (default ��� ����� ������ packet)
		
		// ���α׷��� ���� �� �� ���� DB���� ������ �ε�
		HashMap<String, String> defaultMap = new HashMap<String,String>(); // defaultTable�� pnum�� conn�� mapping
		HashMap<String, String> exceptionMap = new HashMap<String, String>(); // exceptionTable�� pnum�� conn�� mapping
	
		
		String url =  "jdbc:mysql://localhost:3306/uppl?characterEncoding=UTF-8&serverTimezone=UTC";
		
		try {
			Connection con = DriverManager.getConnection(url,"root","1111");
			Statement stmt1 = con.createStatement();
			Statement stmt2 = con.createStatement();
			ResultSet rs1 = stmt1.executeQuery("SELECT * FROM defaultTable"); 
			ResultSet rs2 = stmt2.executeQuery("SELECT * FROM exceptionTable");
			
			System.out.println("���� ������ ���");
			System.out.println("defaultTable ������---------------------------------\npNum \t srcIP \t\t\t dstIP \t\t\tdata \tconn");
			
			while (rs1.next()) {
				String pNum = rs1.getString(1);
				String srcIP = rs1.getString(2);
				String dstIP = rs1.getString(3);
				String data = rs1.getString(4);
				String conn = rs1.getString(5);
				
				System.out.println(pNum +"\t"+srcIP+"\t\t"+dstIP+"\t\t"+data+"\t"+conn);
				defaultMap.put(pNum,conn); // ���α׷� ����� DB���� defaultTable�� ����ִ� �������� pNum�� conn�� Map�� ����
			}
			System.out.println("���α׷� ���� �ð��� defaultTable�� �����ϴ� ��Ŷ ��: " +defaultMap.size());
			
			System.out.println("exceptionTable ������-------------------------------\npNum \t srcIP \t\t\t dstIP \t\t\tdata \tconn");
			while (rs2.next()) {
				String pNum = rs2.getString(1);
				String srcIP = rs2.getString(2);
				String dstIP = rs2.getString(3);
				String data = rs2.getString(4);
				String conn = rs2.getString(5);
				
				System.out.println(pNum +"\t"+srcIP+"\t\t"+dstIP+"\t\t"+data+"\t"+conn);
				exceptionMap.put(pNum,conn); // ���α׷� ����� DB���� exceptionTable�� ����ִ� �������� pNum�� conn�� Map�� ����
			}
			System.out.println("���α׷� ���� �ð��� exceptionTable�� �����ϴ� ��Ŷ ��: " +exceptionMap.size());
					
			
			stmt1.close();
			stmt2.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}


		// default������ �Ǿ��ִ� IP�ּ�. �߰�����
		connDefaultMap.put("192.168.102.130", "Direct");
		connDefaultMap.put("192.168.102.131", "Direct");

		connDefaultMap.put("192.168.102.132", "Proxy");
		connDefaultMap.put("192.168.102.133", "Proxy");

		connDefaultMap.put("192.168.102.134", "VPN");
		connDefaultMap.put("192.168.102.135", "VPN");

		connDefaultMap.put("192.168.102.136", "Tor");
		connDefaultMap.put("192.168.102.137", "Tor");

		     
		while (true) {
			
			PacketMain2 manager = new PacketMain2("root","1111");
			
			
			String menu = JOptionPane.showInputDialog("��Ŷ ���� �ý��� \n �޴��Է� \n"
					+" 1:������ ����, 2:������ ����, 3:������ ��ȸ, 4:������ ����, 5:defaultTable ��ü����, 6:exceptionTable ��ü����, 0:����");
			
			int menui = Integer.parseInt(menu);
			
			
			
			if(menui == 0) { // ���α׷� ����
				JOptionPane.showMessageDialog(null, "���α׷��� �����մϴ�.");
				break;
			}
			
			
			else if(menui == 1) { // ��Ŷ ������ �Է�
				PacketData pd = new PacketData();
				
				String conn; // ��Ź�� ����
				String table;
				
				String pNum = JOptionPane.showInputDialog("����� ��Ŷ�� ��ȣ�Է�");
				/*
				while(pNum == "\n") {
					pNum = JOptionPane.showInputDialog("��Ŷ ��ȣ�� ������ �Ұ��մϴ�.\n ���Է�");
				}
				*/
				// �Է¹��� ��Ŷ ��ȣ��  �̹� ����Ʈ�� �����ҽ� �����  
				while(defaultMap.containsKey(pNum) | exceptionMap.containsKey(pNum)) { 
					
					pNum = JOptionPane.showInputDialog("�̹� �����ϴ� ��Ŷ ��ȣ �Դϴ�.\n ���Է�");
					
					
				}
				pd.setpNum(pNum);
				
				String srcIP = JOptionPane.showInputDialog("����� ��Ŷ�� �۽��� IP�Է�");
				pd.setSrcIp(srcIP);
				
				String dstIP = JOptionPane.showInputDialog("����� ��Ŷ�� ������ IP�Է�");
				pd.setDstIp(dstIP);
				
				String data = JOptionPane.showInputDialog("������ ������ �Է�");
				pd.setPayLoad(data);
				
				
				if (connDefaultMap.containsKey(dstIP)) { //default���� �����Ǿ��ִ� ip�ּ�
					conn = connDefaultMap.get(dstIP); // �ش� ip�ּҰ� ���� ��Ź���� ����
					pd.setConn(conn);
					defaultMap.put(pNum, conn); // ��Ŷ��ȣ�� ��Ź���� ����
					table = "defaultTable";
				}
				
				else { // default���� �����Ǿ� ���� ���� ip�ּ�
					conn = JOptionPane.showInputDialog("default��� ����� �����ϴ�.\n�Ʒ� ��Ź�� �� �ϳ��� �Է��ϼ���.\n"
							+"Direct, Proxy, VPN, Tor (��Ȯ�� �Է�)");
					pd.setConn(conn);
					exceptionMap.put(pNum, conn); // ��Ŷ��ȣ�� ��Ź���� ����(connMap�� ���� ip)
					table = "ExceptionTable";
					
				}
				
				manager.Insert(table, pd);
				
				
			}
			else if(menui == 2) { // ��Ŷ ������ ����
				String table;
				String pNum = JOptionPane.showInputDialog("������ ��Ŷ�� ��ȣ�Է�");
				
				while(defaultMap.containsKey(pNum) == false && exceptionMap.containsKey(pNum) == false) {
					pNum = JOptionPane.showInputDialog("�������� �ʴ� ��Ŷ ��ȣ�Դϴ�.\n�ٽ� �Է��ϼ���.");
				}
				// �Է¹��� pNum�� ���� ���� �ʴ´ٸ� ���Է¿䱸
				if(defaultMap.containsKey(pNum)) { // ������ ��Ŷ�� ��ȣ�� defaultMap(Table)�� �����ϴ� ���
					table = "DefaultTable"; 
					defaultMap.remove(pNum); // defaultMap���� ����
				} else { // ������ ��Ŷ�� ��ȣ�� exceptionMap(Table)�� �����ϴ� ���
					table = "ExceptionTable";
					exceptionMap.remove(pNum); // exceptionMap���� ����
				} 
				
				manager.Delete(table, pNum); 
				
				
			}
			
			
			else if(menui == 3) { // ��Ŷ ������ ��ȸ
				String table;
				String pNum = JOptionPane.showInputDialog("��ȸ�� ��Ŷ�� ��ȣ�Է�");
				while(!defaultMap.containsKey(pNum) && !exceptionMap.containsKey(pNum)) {
					pNum = JOptionPane.showInputDialog("�������� �ʴ� ��Ŷ ��ȣ�Դϴ�.\n�ٽ� �Է��ϼ���.");
				}
				// �Է¹��� pNum�� ���� ���� �ʴ´ٸ� ���Է¿䱸
				if(defaultMap.containsKey(pNum)) {
					table = "DefaultTable";
				}else {
					table = "ExceptionTable";
				}
				manager.Select(table, pNum);
			}
			
			
			else if(menui == 4) { //��Ŷ ������ ����
				
				String table;
				String pNum = JOptionPane.showInputDialog("������ ��Ŷ�� ��ȣ�Է�");
				
				while(!defaultMap.containsKey(pNum) && !exceptionMap.containsKey(pNum)){ // ��ȿ�� ��Ŷ��ȣ�� �Էµ� �� ���� �ݺ�
					pNum = JOptionPane.showInputDialog("�������� �ʴ� ��Ŷ��ȣ �Դϴ�.\n�ٽ� �Է��ϼ���.");
					
				}
				
				if(defaultMap.containsKey(pNum)) {
					table = "DefaultTable";
				}else {
					table = "ExceptionTable";
				}
				
				String attr = JOptionPane.showInputDialog("������ �Ӽ� ����.(srcIP, dstIP, data, conn) \n(pNum ���� �Ұ�)");
				while(attr.equals("pNum")) {
					attr = JOptionPane.showInputDialog("pNum�� ������ �Ұ� �մϴ�.\n������ �Ӽ��� �ٽ� �Է��ϼ���.\n(srcIP, dstIP, data, conn)");
				}
				 
				String value = null;
				if(attr.equals("conn")) {
					value = JOptionPane.showInputDialog("������ conn�Է�(Direct, Proxy, VPN, Tor");
				}else {
					value = JOptionPane.showInputDialog("������ �Ӽ� �� �Է�");
				}
				
				manager.Update(table, pNum, attr, value);
				
			}
			
			else if(menui == 5) { // defaultTable ��� (���α׷� ���� ���ۺ��� ���ݱ��� �Է�, ����, ��ȸ �ѵ����� ��� Ȯ�ΰ���)
				
				try {
					Connection con = DriverManager.getConnection(url,"root","1111");
					Statement stmt1 = con.createStatement();
					
					ResultSet rs1 = stmt1.executeQuery("SELECT * FROM defaultTable");
					
					System.out.println("defaultTable ������---------------------------------\npNum \t srcIP \t\t\t dstIP \t\t\tdata \tconn");
					
					while (rs1.next()) {
						String pNum = rs1.getString(1);
						String srcIP = rs1.getString(2);
						String dstIP = rs1.getString(3);
						String data = rs1.getString(4);
						String conn = rs1.getString(5);
						
						System.out.println(pNum +"\t"+srcIP+"\t\t"+dstIP+"\t\t"+data+"\t"+conn);
						defaultMap.put(pNum,conn);
					}
					System.out.println("���� defaultTable�� �����ϴ� ��Ŷ ��: " +defaultMap.size());
					
			
					stmt1.close();
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
			
			else if(menui == 6) { // exceptionTable ���
				
				try {
					Connection con = DriverManager.getConnection(url,"root","1111");
					Statement stmt2 = con.createStatement();
					
					ResultSet rs2 = stmt2.executeQuery("SELECT * FROM exceptionTable");
					
					System.out.println("exceptionTable ������---------------------------------\npNum \t srcIP \t\t\t dstIP \t\t\tdata \tconn");
					
					while (rs2.next()) {
						String pNum = rs2.getString(1);
						String srcIP = rs2.getString(2);
						String dstIP = rs2.getString(3);
						String data = rs2.getString(4);
						String conn = rs2.getString(5);
						
						System.out.println(pNum +"\t"+srcIP+"\t\t"+dstIP+"\t\t"+data+"\t"+conn);
						defaultMap.put(pNum,conn);
					}
					System.out.println("���� exceptionTable�� �����ϴ� ��Ŷ ��: " +exceptionMap.size());
					
			
					stmt2.close();
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
			
			else {
				JOptionPane.showMessageDialog(null, "�������� �ʴ� �޴���ȣ �Դϴ�. �ٽ� �Է��ϼ���.");
			}
		}
		
	}

}

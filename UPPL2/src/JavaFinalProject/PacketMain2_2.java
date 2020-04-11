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
			
			System.out.println(table+"���̺� "+pd.getpNum()+"�� ��Ŷ �Է� ");
			JOptionPane.showMessageDialog(null, table+"�� " + pd.getpNum() + "�� ��Ŷ ������ �Է� �Ϸ�");
			
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
			System.out.println(pNum+"�� ��Ŷ ��ȸ �Ϸ�");
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
				JOptionPane.showMessageDialog(null, table + " ���̺��� "+pNum+" �� ��Ŷ ����");
				JOptionPane.showMessageDialog(null, "pNum :"+tmpPnum+", srcIP: "+tmpsrcIP+", dstIP: "+tmpdstIP+", data: "+tmpdata+", conn: "+tmpconn);
			}
			System.out.println("������ ���� �Ϸ�");
			
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
			
			System.out.println(table + " ���̺��� " + pNum + "�� ��Ŷ ����");
			JOptionPane.showMessageDialog(null, pNum+"�� ��Ŷ ���� �Ϸ�");
			
			
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
		HashMap<String, String> defaultMap = new HashMap<String,String>(); // defaultTable�� �� pnum�� conn�� mapping
		HashMap<String, String> exceptionMap = new HashMap<String, String>(); // exceptionTable�� �� pnum�� conn�� mapping
	
		
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
			System.out.println("���α׷� ���� �ð��� ���̺� �����ϴ� ��Ŷ ��: " +defaultMap.size());
			
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
			System.out.println("���α׷� ���� �ð��� ���̺� �����ϴ� ��Ŷ ��: " +exceptionMap.size());
					
			
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

		
		/*
		ArrayList Direct = new ArrayList();
		ArrayList Proxy = new ArrayList();
		ArrayList VPN = new ArrayList();
		ArrayList Tor = new ArrayList();
		*/
		     
		while (true) {
			
			PacketMain2_2 manager = new PacketMain2_2("root","1111");
			
			
			String menu = JOptionPane.showInputDialog("��Ŷ ���� �ý��� \n �޴��Է� \n"
					+" 1:������ ����, 2:������ ����, 3:������ ��ȸ, 4:������ ����, 5:defaultTable����, 6:exceptionTable����, 0:����");
			
			int menui = Integer.parseInt(menu);
			
			switch (menui) {
			case 0:
				JOptionPane.showMessageDialog(null, "���α׷��� �����մϴ�.");
				break;
				
			case 1:
				PacketData pd = new PacketData();
				
				String conn; // ��Ź�� ����
				String table1;
				
				String pNum1 = JOptionPane.showInputDialog("����� ��Ŷ�� ��ȣ�Է�");
				/*
				while(pNum == "\n") {
					pNum = JOptionPane.showInputDialog("��Ŷ ��ȣ�� ������ �Ұ��մϴ�.\n ���Է�");
				}
				*/
				// �Է¹��� ��Ŷ ��ȣ��  �̹� ����Ʈ�� �����ҽ� �����  
				while(defaultMap.containsKey(pNum1) | exceptionMap.containsKey(pNum1)) { 
					
					pNum1 = JOptionPane.showInputDialog("�̹� �����ϴ� ��Ŷ ��ȣ �Դϴ�.\n ���Է�");
					
					
				}
				pd.setpNum(pNum1);
				
				String srcIP = JOptionPane.showInputDialog("����� ��Ŷ�� �۽��� IP�Է�");
				pd.setSrcIp(srcIP);
				
				String dstIP = JOptionPane.showInputDialog("����� ��Ŷ�� ������ IP�Է�");
				pd.setDstIp(dstIP);
				
				String data = JOptionPane.showInputDialog("������ ������ �Է�");
				pd.setPayLoad(data);
				
				
				if (connDefaultMap.containsKey(dstIP)) { //default���� �����Ǿ��ִ� ip�ּ�
					conn = connDefaultMap.get(dstIP); // �ش� ip�ּҰ� ���� ��Ź���� ����
					pd.setConn(conn);
					defaultMap.put(pNum1, conn); // ��Ŷ��ȣ�� ��Ź���� ����
					table1 = "defaultTable";
				}
				
				else { // default���� �����Ǿ� ���� ���� ip�ּ�
					conn = JOptionPane.showInputDialog("default��� ����� �����ϴ�.\n�Ʒ� ��Ź�� �� �ϳ��� �Է��ϼ���.\n"
							+"Direct, Proxy, VPN, Tor (��Ȯ�� �Է�)");
					pd.setConn(conn);
					exceptionMap.put(pNum1, conn); // ��Ŷ��ȣ�� ��Ź���� ����(connMap�� ���� ip)
					table1 = "ExceptionTable";
					
				}
				
				manager.Insert(table1, pd);
				break;
				
			case 2:
				String table2;
				String pNum2 = JOptionPane.showInputDialog("������ ��Ŷ�� ��ȣ�Է�");
				
				while(defaultMap.containsKey(pNum2) == false && exceptionMap.containsKey(pNum2) == false) {
					pNum2 = JOptionPane.showInputDialog("�������� �ʴ� ��Ŷ ��ȣ�Դϴ�.\n�ٽ� �Է��ϼ���.");
				}
				// �Է¹��� pNum�� ���� ���� �ʴ´ٸ� ���Է¿䱸
				if(defaultMap.containsKey(pNum2)) { // ������ ��Ŷ�� ��ȣ�� defaultMap(Table)�� �����ϴ� ���
					table2 = "DefaultTable"; 
					defaultMap.remove(pNum2); // defaultMap���� ����
				} else { // ������ ��Ŷ�� ��ȣ�� exceptionMap(Table)�� �����ϴ� ���
					table2 = "ExceptionTable";
					exceptionMap.remove(pNum2); // defaultMap���� ����
				} 
				
				manager.Delete(table2, pNum2); 
				break;
				
			case 3:
				String table3;
				String pNum3 = JOptionPane.showInputDialog("��ȸ�� ��Ŷ�� ��ȣ�Է�");
				while(!defaultMap.containsKey(pNum3) && !exceptionMap.containsKey(pNum3)) {
					pNum3 = JOptionPane.showInputDialog("�������� �ʴ� ��Ŷ ��ȣ�Դϴ�.\n�ٽ� �Է��ϼ���.");
				}
				// �Է¹��� pNum�� ���� ���� �ʴ´ٸ� ���Է¿䱸
				if(defaultMap.containsKey(pNum3)) {
					table3 = "DefaultTable";
				}else {
					table3 = "ExceptionTable";
				}
				manager.Select(table3, pNum3);
				break;
			
			case 4:
				String table4;
				String pNum4 = JOptionPane.showInputDialog("������ ��Ŷ�� ��ȣ�Է�");
				while(!defaultMap.containsKey(pNum4) && !exceptionMap.containsKey(pNum4)){
					pNum4 = JOptionPane.showInputDialog("�������� �ʴ� ��Ŷ��ȣ �Դϴ�.\n�ٽ� �Է��ϼ���.");
					
				}
				// �Է¹��� pNum�� ���� ���� �ʴ´ٸ� ���Է¿䱸
				if(defaultMap.containsKey(pNum4)) {
					table4 = "DefaultTable";
				}else {
					table4 = "ExceptionTable";
				}
				
				String attr = JOptionPane.showInputDialog("������ �Ӽ� ����.(srcIP, dstIP, data, conn) \n(pNum ���� �Ұ�)");
				while(attr.equals("pNum")) {
					attr = JOptionPane.showInputDialog("pNum�� ������ �Ұ� �մϴ�.\n������ �Ӽ��� �ٽ� �Է��ϼ���.\n(srcIP, dstIP, data, conn)");
				}
				
				String value = null;
				if(attr.equals("conn")) {
					value = JOptionPane.showInputDialog("������ conn�Է�(Direct, Proxy, VPN, Tor");
				}else {
					value = JOptionPane.showInputDialog("������ �Ӽ��� �Է�");
				}
				
				manager.Update(table4, pNum4, attr, value);
				break;
			
			case 5:
				try {
					Connection con5 = DriverManager.getConnection(url,"root","1111");
					Statement stmt5 = con5.createStatement();
					
					ResultSet rs5 = stmt5.executeQuery("SELECT * FROM defaultTable");
					
					System.out.println("defaultTable ������---------------------------------\npNum \t srcIP \t\t\t dstIP \t\t\tdata \tconn");
					
					while (rs5.next()) {
						String pNum5 = rs5.getString(1);
						String srcIP5 = rs5.getString(2);
						String dstIP5 = rs5.getString(3);
						String data5 = rs5.getString(4);
						String conn5 = rs5.getString(5);
						
						System.out.println(pNum5 +"\t"+srcIP5+"\t\t"+dstIP5+"\t\t"+data5+"\t"+conn5);
						defaultMap.put(pNum5,conn5);
					}
					System.out.println("���� 'defaultTable' ���̺� �����ϴ� ��Ŷ ��: " +defaultMap.size());
					
			
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
					
					System.out.println("exceptionTable ������---------------------------------\npNum \t srcIP \t\t\t dstIP \t\t\tdata \tconn");
					
					while (rs6.next()) {
						String pNum6 = rs6.getString(1);
						String srcIP6 = rs6.getString(2);
						String dstIP6 = rs6.getString(3);
						String data6 = rs6.getString(4);
						String conn6 = rs6.getString(5);
						
						System.out.println(pNum6 +"\t"+srcIP6+"\t\t"+dstIP6+"\t\t"+data6+"\t"+conn6);
						defaultMap.put(pNum6,conn6);
					}
					System.out.println("���� 'exceptionTable' ���̺� �����ϴ� ��Ŷ ��: " +exceptionMap.size());
					
			
					stmt6.close();
					con6.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				break;
				
			default:
				JOptionPane.showMessageDialog(null, "���� �޴���ȣ �Դϴ�. �ٽ� �Է��ϼ���.");
				break;
			
			}
		}
		
	}

}
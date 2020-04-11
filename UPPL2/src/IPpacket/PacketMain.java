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
			
			Class.forName("com.mysql.cj.jdbc.Driver");  // ����̹� �ε�
			System.out.println("����̹� �ε� ����");
			
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
			System.out.println(table+"���̺� ������ �Է� �Ϸ�");
			
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
			JOptionPane.showMessageDialog(null, "�ش� ��Ŷ ���� �Ϸ�");
			
			// ������ ��Ŷ ���� ���
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
			
			JOptionPane.showMessageDialog(null, "�ش� ��Ŷ ���� �Ϸ�");
			
			stmt.close();
			con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return;
		
	}
//-----------------------------------------------------------------------------------------------------------------
	
	public static void main(String[] args) {
		
		HashMap<String, String> defaultMap= new HashMap<String, String>(); //dstip�� ��Ź�� ���� (default ��� ����� ������ packet)
		HashMap<String, String> pnumMap = new HashMap<String,String>(); // pnum�� ��Ź�� ���� (������ �Է� �� �ʿ� �߰�, ������ ������ �ʿ��� ����)
		System.out.println(pnumMap.size());
	
		// default������ �Ǿ��ִ� IP�ּ�
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
			
			String menu = JOptionPane.showInputDialog("��Ŷ ���� �ý��� \n �޴��Է� \n"
					+" 1:������ ����, 2:������ ����, 3:������ ��ȸ, 4:������ ����, 0:����");
			
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
				// �Է¹��� ��Ŷ ��ȣ��  �̹� ����Ʈ�� �����ҽ� �����  
				while(pnumMap.containsKey(pNum)) { 
					
					pNum = JOptionPane.showInputDialog("�̹� �����ϴ� ��Ŷ ��ȣ �Դϴ�.\n ���Է�: ");
					
				}
				pd.setpNum(pNum);
				
				String srcIP = JOptionPane.showInputDialog("����� ��Ŷ�� �۽��� IP�Է�");
				pd.setSrcIp(srcIP);
				
				String dstIP = JOptionPane.showInputDialog("����� ��Ŷ�� ������ IP�Է�");
				pd.setDstIp(dstIP);
				
				String data = JOptionPane.showInputDialog("������ ������ �Է�");
				pd.setPayLoad(data);
				
				
				if (defaultMap.containsKey(dstIP)) { //default���� �����Ǿ��ִ� ip�ּ�
					conn = defaultMap.get(dstIP); // �ش� ip�ּҰ� ���� ��Ź���� ����
					pd.setConn(conn);
					pnumMap.put(pNum, conn); // ��Ŷ��ȣ�� ��Ź���� ����
					table = "defaultTable";
				}
				
				else { // default���� �����Ǿ� ���� ���� ip�ּ�
					conn = JOptionPane.showInputDialog("default��� ����� �����ϴ�.\n��Ź���� �Է��ϼ���\n"
							+"Direct, Proxy, VPN, Tor �� 1�� ����");
					pd.setConn(conn);
					pnumMap.put(pNum, conn); // ��Ŷ��ȣ�� ��Ź���� ����(default�� ���� ip)
					table = "ExceptionTable";
					
				}
				
				manager.Insert(table, pd);
				
				
			}
			else if(menui == 2) { // ��Ŷ ������ ����
				String table;
				String pNum = JOptionPane.showInputDialog("������ ��Ŷ�� ��ȣ�Է�");
				// �Է¹��� pNum�� ���� ���� �ʴ´ٸ� ���Է¿䱸
				if(pnumMap.containsKey(pNum)) { // ������ ��Ŷ�� ��ȣ�� pnumMap�� ���� �ϴ°��
					table = "DefaultTable"; 
				} else { // 
					table = "ExceptionTable";
				} 
				
				manager.Delete(table, pNum);
				pnumMap.remove(pNum);
				
			}
			
			
			else if(menui == 3) { // ��Ŷ ������ ��ȸ
				String table;
				String pNum = JOptionPane.showInputDialog("��ȸ�� ��Ŷ�� ��ȣ�Է�");
				// �Է¹��� pNum�� ���� ���� �ʴ´ٸ� ���Է¿䱸
				if(pnumMap.containsKey(pNum)) {
					table = "DefaultTable";
				}else {
					table = "ExceptionTable";
				}
				manager.Select(table, pNum);
			}
			
			
			else if(menui == 4) { //��Ŷ ������ ����
				
				String table;
				String pNum = JOptionPane.showInputDialog("������ ��Ŷ�� ��ȣ�Է�");
				// �Է¹��� pNum�� ���� ���� �ʴ´ٸ� ���Է¿䱸
				if(pnumMap.containsKey(pNum)) {
					table = "DefaultTable";
				}else {
					table = "ExceptionTable";
				}
				
				String attr = JOptionPane.showInputDialog("������ �Ӽ� ����. \n(pNum ���� �Ұ�)");
				String value = JOptionPane.showInputDialog("������ �� �Է�");
				manager.Update(table, pNum, attr, value);
				
			}
		}
		
	}

}

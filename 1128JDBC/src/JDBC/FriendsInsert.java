package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;


public class FriendsInsert {
	
	String id, password;
	public FriendsInsert(String id, String password) { // mariadb���� (����̹� �ε�)
		this.id =id;
		this.password = password;
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver"); // 
			System.out.println("����̹� �ε� ����");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int Insert() {
		int insertCount = 0;
		String url = "jdbc:mysql://localhost:3306/cju?characterEncoding=UTF-8&serverTimezone=UTC";
		try {
			Connection conn = DriverManager.getConnection(url,this.id, this.password);
			
			String name = JOptionPane.showInputDialog(null,"�̸��Է�");
			String phone = JOptionPane.showInputDialog(null,"��ȣ�Է�");
			String address = JOptionPane.showInputDialog(null,"�ּ��Է�");
			String email = JOptionPane.showInputDialog(null,"�̸����Է�");
			
			Statement stmt = conn.createStatement();
			String sql ="INSERT INTO FRIENDS(NAME,PHONE,ADDRESS,EMAIL)"
					+"VALUES('"+name+"','"+phone+"','"+address+"','"+email+"')";
			insertCount = stmt.executeUpdate(sql);
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return insertCount;
	}
	
	public static void main(String[] args) {
		FriendsInsert ins = new FriendsInsert("root","1111");
		int cnt = ins.Insert();
		System.out.println(cnt+ "�� ������ �Է�");
		
	}

}

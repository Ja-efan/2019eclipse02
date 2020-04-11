package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;


public class FriendsInsert {
	
	String id, password;
	public FriendsInsert(String id, String password) { // mariadb연결 (드라이버 로드)
		this.id =id;
		this.password = password;
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver"); // 
			System.out.println("드라이버 로드 성공");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int Insert() {
		int insertCount = 0;
		String url = "jdbc:mysql://localhost:3306/cju?characterEncoding=UTF-8&serverTimezone=UTC";
		try {
			Connection conn = DriverManager.getConnection(url,this.id, this.password);
			
			String name = JOptionPane.showInputDialog(null,"이름입력");
			String phone = JOptionPane.showInputDialog(null,"번호입력");
			String address = JOptionPane.showInputDialog(null,"주소입력");
			String email = JOptionPane.showInputDialog(null,"이메일입력");
			
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
		System.out.println(cnt+ "개 데이터 입력");
		
	}

}

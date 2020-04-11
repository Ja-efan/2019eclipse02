package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FriendsSelect {

	String id, password;
	
	public FriendsSelect( String id, String password) {
		this.id = id;
		this.password = password;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("드라이버 로드 성공");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getData() {
		String url = "jdbc:mysql://localhost:3306/cju?characterEncoding=UTF-8&serverTimezone=UTC";
		try {
			Connection con = DriverManager.getConnection(url,this.id,this.password);
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from friends");
			
			while (rs.next()) {
				String name = rs.getString(1);
				String phone = rs.getString(2);
				String address = rs.getString(3);
				String email = rs.getString(4);
				System.out.println(name +"\t"+phone+"\t"+address+"\t"+email);
			}
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		FriendsSelect sel = new FriendsSelect("root","1111");
		sel.getData();
	}

}

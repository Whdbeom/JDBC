package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws SQLException {

		System.out.println("로그인");
		
		
		// 연결 준비
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String userName = "C##KH_JDBC"; 
		String userPwd = "1234";
		Scanner sc = new Scanner(System.in);
		
		
		// 디비 연결 == CONN 얻기
		Connection conn = DriverManager.getConnection(url, userName, userPwd);
		
		// 쿼리 실행

		
			System.out.print("아이디 : ");
			String id = sc.nextLine();
			System.out.print("비밀번호 : ");
			String pwd = sc.nextLine();	
//			String sql = "SELECT * FROM MEMBER WHERE ID ='" +id+"' AND PWD = '" + pwd + "'";
//			
//			Statement stmt = conn.createStatement();
//			ResultSet rs = stmt.executeQuery(sql);
			
			String sql = "SELECT * FROM MEMBER WHERE ID = ? AND PWD = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			ResultSet rs = pstmt.executeQuery();

			if(rs.next()) {
				String x = rs.getString("ID");
				String y = rs.getString("PWD");
				System.out.println("로그인 성공!   ");
			} else {
				System.out.println("로그인 실패,,,,,,,,,,,,,,,,,,,");
			}
	}
}

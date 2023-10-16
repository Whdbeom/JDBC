package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Login {

	public static void main(String[] args) throws SQLException {
		
		System.out.println("로그인");
		//연결준비
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String userName = "C##KH_JDBC";
		String userPwd = "1234";
		
		// 디비연결
		Connection conn = DriverManager.getConnection(url, userName, userPwd);
		
		// 입력받기
		Scanner sc = new Scanner(System.in);
		System.out.print("아이디 : ");
		String id = sc.nextLine();
		System.out.print("비밀번호 : ");
		String pwd = sc.nextLine();
		
		
		// 쿼리 실행
		String sql  = "SELECT * FROM MEMBER WHERE ID = ? AND PWD = ?";
		
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, id);
		ps.setString(2, pwd);
		
		ResultSet rs = ps.executeQuery();
		
		// 결과 처리
		if(rs.next()) {
			System.out.println("로그인 성공");
			System.out.println(rs.getString(3) + "님 ㅎㅇ");
		} else {
			System.out.println("로그인 실패");
		}
		
		
	}

}

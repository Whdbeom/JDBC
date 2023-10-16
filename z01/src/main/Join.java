package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Join {

	public static void join() throws Exception {
		
		System.out.println("=====회원가입======");

		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String userName = "C##KH_JDBC";
		String userPwd = "1234";
		
		Connection conn = DriverManager.getConnection(url, userName, userPwd);
		
		String sql = "INSERT INTO MEMBER(ID, PWD, NICK) VALUES (?, ?, ?)";
		Scanner sc = new Scanner(System.in);
		System.out.print("아이디 : ");
		String id = sc.nextLine();
		System.out.print("비밀번호 : ");
		String pwd = sc.nextLine();
		System.out.print("닉네임  : ");
		String nick = sc.nextLine();
		
		
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, id);
		ps.setString(2, pwd);
		ps.setString(3, nick);
	
		int result = ps.executeUpdate();
		
		if(result > 0) {
			System.out.println("회원가입 성공");
		} else {
			System.out.println("회원가입 실패");
		}
		
	}

}

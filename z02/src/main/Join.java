package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Join {

	public static void main(String[] args) throws Exception {

		System.out.println("회원가입");
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
		System.out.print("닉네임  : ");
		String nick = sc.nextLine();
		
		// 쿼리 실행
		String sql = "INSERT INTO MEMBER(ID, PWD, NICK) VALUES (?, ?, ?)";
		
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
		
		
		// 결과 처리
		
		
	}

}

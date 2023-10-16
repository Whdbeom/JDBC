package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws Exception {

		System.out.println("----회원가입----");
		
		Scanner sc = new Scanner(System.in);
		
		// 연결 준비
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String userName = "C##KH_JDBC";
		String pwd = "1234";
		// 디비 연결
		Connection conn = DriverManager.getConnection(url, userName, pwd);
		
		// 쿼리 실행
		Statement stmt = conn.createStatement();
		int count = 1;
	
		while(true) {
			System.out.print("아이디 : ");
			String id = sc.nextLine();
			System.out.print("비밀번호 : ");
			String pwd2 = sc.nextLine();
//			int x = (int)(Math.random()*10000)+1;
//			String str = String.valueOf(x);
//			String pwd2 = str;
			
			String sql = "INSERT INTO MEMBER(NO, ID, PWD) VALUES ('"+count+"','" + id +"','" + pwd2 +"')"; 
			
			int result = stmt.executeUpdate(sql);
			
			System.out.println("result : " + result);
			count++;
		}
	}
}

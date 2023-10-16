package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

	public static void main(String[] args) throws SQLException {

		System.out.println("JDBC ~~~~");
		
		// 연결 정보 준비
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String username = "C##KH";
		String pwd = "1234";
		
		
		// DB 연결해서 Connection 얻기
		Connection conn = DriverManager.getConnection(url, username, pwd);
		
		// 쿼리문 실행 및 결과 받기
		Statement stmt = conn.createStatement();
		
		String sql ="INSERT INTO MEMBER(ID, PWD) VALUES ('자바1', '7777')";

		String x = "UPDATE MEMBER SET ID = '손흥민', PWD = '123123' WHERE ID = '자바ㅇ'";
		String y = "DELETE MEMBER WHERE ID = '손흥민'";
		int result = stmt.executeUpdate(sql);
		System.out.println("result : " + result);
		
		
	}

}

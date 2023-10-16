package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

	public static void main(String[] args) throws Exception {

		System.out.println("JDBC");
		
		// 연결 정보 준비
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String userName = "C##KH";
		String pwd = "1234";
		
		// DB 연결해서 CONNECTION 얻기
		Connection conn = DriverManager.getConnection(url, userName, pwd);
		
		// 쿼리문 실행 및 결과 받기
		Statement stmt = conn.createStatement();
		String sql = "INSERT INTO MEMBER(ID, PWD, NICK) "
				+ "VALUES ('JDBC', '1234', '손흥민')";
		
		String s = "SELECT *"
				+ "FROM MEMBER"
				+ "WHERE NICK = '손흥민';";
		
//		System.out.println(stmt.executeQuery(s));
//		int result = stmt.executeUpdate(s);
//		ResultSet result = stmt.executeQuery(s);
//		System.out.println("result : " + result);
		stmt.executeQuery("select * from MEMBER");
		
		
	}

}

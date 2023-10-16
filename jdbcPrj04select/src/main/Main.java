package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws Exception {

		
		
		System.out.println("-----select-----");
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String userName = "C##KH_JDBC";
		String pwd = "1234";
		
		
		// 디비 연결
		Connection conn = DriverManager.getConnection(url, userName, pwd);
		
		// 쿼리 실행
		System.out.print("아이디 : ");
		Scanner sc = new Scanner(System.in);
		String id = sc.nextLine();
//		PreparedStatement stmt = conn.prepareStatement(sql);
		
		String sql = "SELECT * FROM MEMBER WHERE ID LIKE'"+id+"%'";
		Statement stmt =conn.createStatement();
		
		ResultSet rs = stmt.executeQuery(sql);
		

		while(rs.next()) {
			
//			boolean isExist = rs.next();					// 커서 한칸 내리기
//			
//			if(!rs.next()) {
//				break;
//			} 
			String x =rs.getString("ID");   		// ID 칼럼에 해당하는 데이터 꺼내기
			String y =rs.getString("PWD");	   // PWD 칼럼에 해당하는 데이터 꺼내기
			System.out.println("ID : " + x + "  /  PWD : " + y);
			
			
		}
		
	}

}

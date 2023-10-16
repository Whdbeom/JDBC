package member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import db.util.JDBCtemplate;

public class MemberController {


	private Scanner sc;
	
	
	public MemberController() throws Exception {
		sc = new Scanner(System.in);
	}
	
	public void selectMenu() throws Exception {
		
		System.out.println("1.회원가입");
		System.out.println("2.로그인");
		String select = sc.nextLine();
		
		switch(select) {
		case "1" : join(); break;
		case "2" : login(); break;
		default : System.out.println("잘못입력");
		}
	}

	public void join() {
		System.out.println("회원가입");

		// 연결 준비

		//입력 받기
		System.out.print("아이디 : ");
		String id = sc.nextLine();
		System.out.print("비밀번호 : ");
		String pwd = sc.nextLine();
		System.out.print("닉네임 : ");
		String nick = sc.nextLine();
		
		
		// 쿼리 실행
		PreparedStatement ps = null;
		Connection conn = null;
		try {
			conn = JDBCtemplate.getConnection();
			String sql = "INSERT INTO MEMBER(ID, PWD, NICK) VALUES (?, ?, ?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, pwd);
			ps.setString(3, nick);
			
			//결과 처리
			int result = ps.executeUpdate();
			
			if(result == 1) {
				conn.commit();
				System.out.println("회원가입 성공");
			} else {
				throw new Exception();
			}
		} catch (Exception e) {
			
			try {	if(conn != null) { conn.rollback(); } } catch (Exception e1) {}
			System.out.println("회원가입 실패");
			
		} finally {
			// 자원 반납
			try { if(ps != null && !ps.isClosed()) { ps.close(); } } catch (Exception e) {}
			try { if(conn != null && conn.isClosed()) { conn.close(); } }  catch (Exception e) {}
		}
	}
	
	public void login() {
		System.out.println("로그인");
		
		// 입력받기
		System.out.print("아이디 : ");
		String id = sc.nextLine();
		System.out.print("비밀번호 : ");
		String pwd = sc.nextLine();
		
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			// conn 얻기
			conn = JDBCtemplate.getConnection();
			// 쿼리 실행
			String sql = "SELECT * FROM MEMBER WHERE ID = ? AND PWD = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, pwd);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				System.out.println("로그인 성공");
				System.out.println(rs.getString(3) + "님 ㅎㅇ");
			} else {
				throw new Exception();
			}		
		} catch(Exception e) {
			System.out.println("로그인 실패");
		} finally {
			// 자원 반납		
			try { if(rs != null && !rs.isClosed()) {rs.close();} } catch (SQLException e) {}
			try { if(ps != null & !rs.isClosed()) { ps.close(); } } catch (SQLException e) {}
			try { if(conn != null && conn.isClosed()) {conn.close(); } } catch (SQLException e) {}
		}
	}
	
}

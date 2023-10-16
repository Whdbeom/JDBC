package member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import db.JDBCTemplate;

public class MemberController {

	private Scanner sc;
	
	public MemberController() {
		sc = new Scanner(System.in);
	}

	public void menu() {
		System.out.println("1. 회원가입");
		System.out.println("2. 로그인");
		System.out.println("3. 유저보기");
		System.out.println("4. 유저 삭제");
		String select = sc.nextLine();
		
		switch(select) {
		case "1" : join(); break;
		case "2" : login(); break;
		case "3" : showUser(); break;
		case "4" : deleteUser(); break;
		default : System.out.println("....");
		}
		
	}
	
	//회원 가입
	public void join() {
		// 데이터 입력 받기
		System.out.print("아이디 : ");
		String id = sc.nextLine();
		System.out.print("비밀번호 : ");
		String pwd = sc.nextLine();
		System.out.print("닉네임 : ");
		String nick = sc.nextLine();
		
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			// conn 얻기
			conn = JDBCTemplate.getConnection();
			// 쿼리 실행
			String sql = "INSERT INTO MEMBER(ID, PWD, NICK) VALUES (?, ?, ?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, pwd);
			ps.setString(3, nick);
			
			// 결과 처리
			int result = ps.executeUpdate();
			
			if(result == 1) {
				JDBCTemplate.commit(conn);
				System.out.println("가입 성공");
			} else {
				throw new Exception();
			}
		} catch(Exception e) {
			JDBCTemplate.rollback(conn);
			System.out.println("가입 실패");
		} finally {
			// 자원 반납
			JDBCTemplate.close(conn);
			JDBCTemplate.close(ps);
			
		}
	}
	
	// 로그인
	public void login() {
		// 데이터 입력 받기
		System.out.print("아이디 : ");
		String id = sc.nextLine();
		System.out.print("비밀번호 : ");
		String pwd = sc.nextLine();

		Connection conn = null; 
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			// connection 
			conn = JDBCTemplate.getConnection();
			// 쿼리 실행
			String sql = "SELECT * FROM MEMBER WHERE ID = ? AND PWD = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, pwd);
			
			rs = ps.executeQuery();
			
			// 결과 처리
			if(rs.next()) {
				System.out.println("로그인성공");
			} else {
				throw new Exception();
			}
		} catch(Exception e) {
			System.out.println("로그인실패");
		} finally {
			// 자원 반납
			JDBCTemplate.close(conn);
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
			
		}
	}
	
	public void showUser() {
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = JDBCTemplate.getConnection();
			String sql = "SELECT * FROM MEMBER";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				System.out.println("아이디 : " + rs.getString(1) + "  || 비밀번호 : " + rs.getString(2) + "  || 닉네임 : " + rs.getString(3));
			}
		
			} catch(Exception e) {
				
			} finally {
				JDBCTemplate.close(conn);
				JDBCTemplate.close(ps);
				JDBCTemplate.close(rs);
			}
	}
	
	public void deleteUser() {
		// 데이터 입력 받기
		System.out.print("삭제할 아이디 : ");
		String id = sc.nextLine();
		System.out.print("비밀번호 : ");
		String pwd = sc.nextLine();
				
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = JDBCTemplate.getConnection();
			String sql = "DELETE FROM MEMBER WHERE ID = ? AND PWD = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, pwd);
			
			int result = ps.executeUpdate();
			
			if(result == 1) {
				JDBCTemplate.commit(conn);
				System.out.println("삭제했어요");
			} else {
				throw new Exception();
			}
			
		} catch (Exception e) {
			JDBCTemplate.rollback(conn);
			System.out.println("실패");
		} finally {
			JDBCTemplate.close(ps);
			JDBCTemplate.close(conn);
		}
	}
	
}
